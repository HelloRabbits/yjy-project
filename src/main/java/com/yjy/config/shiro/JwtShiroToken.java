package com.yjy.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author zhangjl
 * <p>
 *     这个类我们可以实现更高层的接口，AuthenticationToken
 *     但这里考虑将账号密码传入realm中，方便后续扩展，增加额外的校验
 * </p>
 * @date 2020-07-28 19:34
 */
public class JwtShiroToken extends UsernamePasswordToken {

    private String token;

    public JwtShiroToken(String username, String password, String token) {
        super(username, password);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
