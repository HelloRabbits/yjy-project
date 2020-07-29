package com.yjy.config.shiro;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.yjy.api.account.AccountPermissionApi;
import com.yjy.bean.base.AccountInfoCache;
import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.common.Constant;
import com.yjy.common.Response;
import com.yjy.common.enums.ErrorCodeEnum;
import com.yjy.common.exception.JwtException;
import com.yjy.common.exception.QuestionException;
import com.yjy.common.exception.UnPermissionException;
import com.yjy.service.base.RedisCacheInfoService;
import com.yjy.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangjl
 * @description 添加jwt过滤器
 * <p>
 * 将token得校验和用户信息的查询都放在这里，realm中的doGetAuthenticationInfo只做简单的信息处理。
 * 1.校验token是否存在（可能存在redis中的已尽过期）
 * 否-》返回403，重洗登陆
 * 是-》验证剩余时间<2小时则刷新token（包括写入header的和redis中的）
 * 2.在token校验，进行用户信息的查询，优先从缓存中获取。
 * </p>
 * @date 2020-07-28 18:49
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {


    @Resource
    private LoginAccountInfo loginAccountInfo;

    @Resource
    private AccountPermissionApi accountPermissionApi;

    @Resource
    private RedisCacheInfoService redisCacheInfoService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //拦截请求，进行token的校验和用户信息的缓存
        try {
            this.executeLogin(request, response);
        } catch (Exception e) {
            log.error("系统异常:{}", e);
            if (e instanceof JwtException) {
                response(response, e.getMessage(), ((JwtException) e).getCode());
            } else if (e instanceof QuestionException) {
                response(response, ((QuestionException) e).getMsg(), ((QuestionException) e).getCode());
            } else if (e instanceof UnPermissionException) {
                response(response, e.getMessage(), ((UnPermissionException) e).getCode());
            } else {
                response(response, e.getMessage(), ErrorCodeEnum.ERROR_500.getCode());
            }
            return false;
        }
        return true;
    }

    /**
     * 这里我们详细说明下为什么重写
     * 可以对比父类方法，只是将executeLogin方法调用去除了
     * 如果没有去除将会循环调用doGetAuthenticationInfo方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.sendChallenge(request, response);
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = this.createToken(request, response);
        if (token == null) {
            throw new JwtException("token校验异常");
        }
        Subject subject = this.getSubject(request, response);
        //校验是否已经登陆 避免每次都进入realm校验  realm的信息会缓存起来。
        if (subject.isAuthenticated()) {
            return true;
        }
        subject.login(token);
        return true;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        if (null == httpRequest) {
            return null;
        }
        String token = httpRequest.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            throw new JwtException(ErrorCodeEnum.ERROR_403.getCode(), "token验证失败");
        }
        //校验token信息
        if (!JwtUtil.verify(token)) {
            throw new JwtException(ErrorCodeEnum.ERROR_403.getCode(), "token校验失败");
        }
        String account = JwtUtil.getClaim(token, Constant.JWT_ACCOUNT);
        //查询账号的基本信息
        if (StrUtil.isEmpty(account)) {
            return null;
        }
        //校验redis中是否有token
        String cacheToken = redisCacheInfoService.getToken(account);
        if (StrUtil.isEmpty(cacheToken)) {
            //token失效
            throw new JwtException(ErrorCodeEnum.ERROR_403.getCode(), "token校验失败");
        } else {
            //校验时间是否与redis中的相同
            if (!JwtUtil.getClaim(token, Constant.JWT_EXPIRE_TIME).equals(JwtUtil.getClaim(cacheToken, Constant.JWT_EXPIRE_TIME))) {
                //将token替换成redis里面的
                token = cacheToken;
            }
            //如果token已经过期了 则刷新token
            if (Long.parseLong(JwtUtil.getClaim(cacheToken, Constant.JWT_EXPIRE_TIME)) < System.currentTimeMillis()) {
                token = redisCacheInfoService.addToken(account);
            }
        }
        AccountInfoCache accountInfo = accountPermissionApi.getCacheInfoWithAccount(account);
        if (ObjectUtil.isEmpty(account)) {
            throw new UnknownAccountException("账号信息异常");
        }
        //设置线程变量, 单次请求有效
        BeanUtil.copyProperties(accountInfo, loginAccountInfo);
        httpResponse.setHeader("token", token);
        return new JwtShiroToken(token, accountInfo.getAccount(), accountInfo.getPassword());
    }


    /**
     * 无需转发，直接返回Response信息
     */
    private void response(ServletResponse response, String msg, int code) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        //httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
            out.append(JSON.toJSONString(Response.fail(code, msg)));
            out.flush();
        } catch (IOException e) {
            log.error("直接返回Response信息出现IOException异常:{}", e.getMessage());
            throw new QuestionException("直接返回Response信息出现IOException异常:" + e.getMessage());
        }
    }
}
