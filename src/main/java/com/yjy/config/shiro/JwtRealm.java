package com.yjy.config.shiro;



import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;



/**
 * @author zhangjl
 * @description 自定义 realm
 * @date 2020-07-21 10:50
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    /**
     * 大坑，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtShiroToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //权限校验
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //token和用户的校验都已经放在了登陆和请求的过滤器中 这里不再做多与的校验
        //参数为以后扩展使用
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
