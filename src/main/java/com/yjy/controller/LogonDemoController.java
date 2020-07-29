package com.yjy.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.yjy.api.account.AccountPermissionApi;
import com.yjy.common.Constant;
import com.yjy.common.Response;
import com.yjy.common.exception.JwtException;
import com.yjy.entity.SysAccount;
import com.yjy.service.ISysAccountService;
import com.yjy.service.base.RedisCacheInfoService;
import com.yjy.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zhangjl
 * @description
 * @date 2020-07-21 13:23
 */
@RequestMapping("auth")
@RestController
public class LogonDemoController {

    @Resource
    private ISysAccountService sysAccountService;

    @Resource
    private AccountPermissionApi accountPermissionApi;

    @Resource
    private RedisCacheInfoService redisCacheInfoService;
    /**
     * 登陆
     *
     * @param account 账号
     * @param password 密码
     * @return
     */
    @GetMapping("/login")
    public Response<String> login(String account, String password) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return Response.success("login success");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        subject.login(token);
        return Response.success("login success");
    }

    /**
     * 登陆
     *
     * @param account 账号
     * @param password 密码
     * @return
     */
    @GetMapping("/login/jwt")
    public Response<String> jwtLogin(String account, String password, HttpServletResponse response) {
        if (StrUtil.isEmpty(account) || StrUtil.isEmpty(password)) {
            throw new UnknownAccountException("账号或密码错误");
        }
        SysAccount sysAccount = sysAccountService.getWithAccount(account);
        if (ObjectUtil.isEmpty(account)) {
            throw new UnknownAccountException("账号或密码错误");
        }
        if (!password.equals(sysAccount.getPassword())) {
            throw new UnknownAccountException("账号或密码错误");
        }
        if (sysAccount.getState() != 1) {
            throw new LockedAccountException("账号信息已被锁定");
        }
        //从redis中获取人员信息，没有则初始化到redis中，只有账号信息
        accountPermissionApi.getCacheInfoWithAccount(account);
        //校验token开始
        try {
            //尝试从redis中获取token信息
            String token = redisCacheInfoService.getToken(account);
            if (StrUtil.isEmpty(token)) {
                token = redisCacheInfoService.addToken(account);
            } else {
                //校验token信息是否有效
                //jwt中account信息
                String jwtAccount = JwtUtil.getClaim(token, Constant.JWT_ACCOUNT);
                //这种概率很低，如果程序错误导致存入的redis的token信息有误
                if (StrUtil.isEmpty(jwtAccount) || !jwtAccount.equals(account)) {
                    //账号与token对应不上，清除token并重新生成
                    redisCacheInfoService.removeToken(account);
                    token = redisCacheInfoService.addToken(account);
                }
                //jwt过期时间
                String expire = JwtUtil.getClaim(token, Constant.JWT_EXPIRE_TIME);
                long jwtExpireTime = Long.parseLong(expire == null ? "0" : expire);
                //校验token是否过期，或接近失效
                if(jwtExpireTime <= (System.currentTimeMillis() - 10 * 60 * 1000L)  || StrUtil.isEmpty(expire)) {
                    //重新生成
                    token = redisCacheInfoService.addToken(account);
                }
            }
            //将token回写header
            // TODO: 2020/7/29 这里调用 subject.login 登陆。
            response.setHeader("token", token);
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return Response.success("login success");
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping("/logout")
    public Response<String> logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return Response.success("login out success");
    }



}
