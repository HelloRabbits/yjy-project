package com.yjy.common;

/**
 * @author zhangjl
 * @description
 * @date 2020-07-28 12:27
 */
public interface Constant {

    /**
     * session中存放的用户信息
     */
    public final static String SESSION_USER_INFO = "session_user_info";

    String PREFIX_SHIRO_CACHE = "shiro:cache:account";

    /**
     * redis 缓存个人信息
     */
    String REDIS_PERMISSION_INFO = "redis:account:permission:";

    /**
     * redis 缓存个人权限
     */
    String REDIS_ACCOUNT_INFO = "redis:account:info:";

    /**
     * redis缓存 token信息
     */
    String REDIS_JWT_TOKEN = "redis:jwt:token:";


    /**
     * JWT-account:
     */
    String JWT_ACCOUNT = "account";
    /**
     * jwt-expire_time
     */
    String JWT_EXPIRE_TIME = "expireTime";

    /**
     * 钉钉token
     */
    String DINGDING_REDIS_TOKEN = "other:token:dingding:";
    /**
     * 微信小程序token
     */
    String WECHAT_MA_REDIS_TOKEN = "other:token:wx:ma:";
}
