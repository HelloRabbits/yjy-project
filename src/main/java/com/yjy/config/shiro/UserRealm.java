package com.yjy.config.shiro;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import java.util.stream.Collectors;


/**
 * @author zhangjl
 * @description 自定义 realm
 * @date 2020-07-21 10:50
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo((String)token.getPrincipal(), new String((char[]) token.getCredentials()), null, getName());
    }
}
