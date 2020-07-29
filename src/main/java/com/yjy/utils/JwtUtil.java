package com.yjy.utils;

import cn.hutool.core.codec.Base64;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yjy.common.Constant;
import com.yjy.common.exception.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JAVA-JWT工具类
 *
 * @author Wang926454
 * @date 2018/8/30 11:45
 */
// TODO: 2020/7/29 后续参数需要优化
@Slf4j
@Component
public class JwtUtil {

    /**
     * 过期时间改为从配置文件获取
     */
    private static String accessTokenExpireTime;

    /**
     * JWT认证加密私钥(Base64加密)
     */
    private static String encryptJWTKey;


    @Value("${accessTokenExpireTime}")
    public void setAccessTokenExpireTime(String accessTokenExpireTime) {
        JwtUtil.accessTokenExpireTime = accessTokenExpireTime;
    }

    @Value("${encryptJWTKey}")
    public void setEncryptJWTKey(String encryptJWTKey) {
        JwtUtil.encryptJWTKey = encryptJWTKey;
    }



    /**
     * 校验token是否正确
     *
     * @param token Token
     * @return boolean 是否正确
     * @author Wang926454
     * @date 2018/8/31 9:05
     */
    public static boolean verify(String token) throws JwtException {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, Constant.JWT_ACCOUNT) + Base64.decodeStr(encryptJWTKey);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("JWTToken认证解密出现UnsupportedEncodingException异常:{}", e.getMessage());
            throw new JwtException("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     *
     * @param token
     * @param claim
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/9/7 16:54
     */
    public static String getClaim(String token, String claim) throws JwtException {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (Exception e) {
            log.error("解密Token中的公共信息出现JWTDecodeException异常:{}", e.getMessage());
            throw new JwtException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }

    /**
     * 生成签名
     *
     * @param account 帐号
     * @return java.lang.String 返回加密的Token
     * @author Wang926454
     * @date 2018/8/31 9:07
     */
    public static String sign(String account, Long currentTimeMillis) throws JwtException {
        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64.decodeStr(encryptJWTKey);
            // 单位天，提前一小时失效，为了刷新准备
            long expireTime = currentTimeMillis + Long.parseLong(accessTokenExpireTime) * 1000 * 3600 * 24 - 3600000;
            Date date = new Date(expireTime);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim(Constant.JWT_ACCOUNT, account)
                    //只能保存Sring类型getClaim()其他类型会返回null
                    .withClaim(Constant.JWT_EXPIRE_TIME, String.valueOf(expireTime))
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("JWTToken加密出现UnsupportedEncodingException异常:{}", e.getMessage());
            throw new JwtException("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHBpcmVUaW1lIjoxNTk4NjAzNTc1MzMwLCJleHAiOjE1OTg2MDM1NzUsImFjY291bnQiOiJzdXBlcl9hZG1pbiJ9.02tXS84ec3ujp_oaWzkcHpP8KJ5jMMcbn-kIddkwJuI";
        System.out.println(getClaim(token, Constant.JWT_ACCOUNT));
        System.out.println(getClaim(token, Constant.JWT_EXPIRE_TIME));
    }

}
