package com.yjy.config.shiro;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;

import com.alibaba.fastjson.JSON;
import com.yjy.bean.dto.account.SysUserPermissionDto;
import com.yjy.common.Constant;
import com.yjy.entity.SysAccount;
import com.yjy.service.ISysAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author zhangjl
 * @description 自定义 realm
 * @date 2020-07-21 10:50
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Resource
    private ISysAccountService sysAccountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //校验用户的权限
        SysAccount userInfo = (SysAccount)SecurityUtils.getSubject().getSession().getAttribute(Constant.SESSION_USER_INFO);
        String idAccount = userInfo.getIdAccount();
        List<SysUserPermissionDto> permissionDtos = sysAccountService.queryUserPermissionList(idAccount);
        log.info("查询用户权限信息:{}", JSON.toJSONString(permissionDtos));
        if (CollUtil.isEmpty(permissionDtos)) {
            return new SimpleAuthorizationInfo();
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionDtos.stream().map(SysUserPermissionDto::getPermissionCd).collect(Collectors.toSet()));
        authorizationInfo.addRoles(permissionDtos.stream().map(SysUserPermissionDto::getRoleCd).collect(Collectors.toSet()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //校验用户的账号信息
        if (ObjectUtil.isEmpty(token.getPrincipal()) || ObjectUtil.isEmpty(token.getCredentials())) {
            throw new UnknownAccountException("账号或密码错误");
        }
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        //校验账号
        SysAccount account = sysAccountService.getWithAccount(username);

        log.info("查询用户信息:{}", JSON.toJSONString(account));
        if (ObjectUtil.isEmpty(account)) {
            throw new UnknownAccountException("账号或密码错误");
        }
        if (!password.equals(account.getPassword())) {
            throw new UnknownAccountException("账号或密码错误");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                username,
                password,
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
        //将用户信息放入session中
        //可以将用户信息写入redis
        SecurityUtils.getSubject().getSession().setAttribute(Constant.SESSION_USER_INFO, account);
        //设置redis中的用户信息
        return info;
    }
}
