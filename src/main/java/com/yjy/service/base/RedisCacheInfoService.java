package com.yjy.service.base;

import com.yjy.bean.base.AccountInfoCache;
import com.yjy.bean.base.PermissionCache;
import com.yjy.common.Constant;
import com.yjy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjl
 * @description redis缓存数据服务，统一处理
 * <p>
 * 统一处理方便全局变更缓存的key和时间
 * 1.账号信息的缓存不与token关联，如果账号数据未发生变动则不更新缓存，直到失效
 * 2.所有的缓存都是30分钟有效期，如果权限或个人信息有变更的话 需要等30分钟或者主动退出后登陆即可，这里可以将时间设置短一点，在token校验处刷新
 * </p>
 * @date 2020-07-29 10:42
 */
@Component
public class RedisCacheInfoService {

    @Resource
    private RedisService redisService;

    @Value("${redis.cache.time.account}")
    private Long accountCacheTime;

    @Value("${redis.cache.time.permission}")
    private Long permissionCacheTime;

    @Value("${redis.cache.time.token}")
    private Long tokenCacheTime;


    /**
     * 缓存账号基本信息
     *
     * @param cache 缓存数据内容
     */
    public String addAccountInfoCache(AccountInfoCache cache) {
        String key = Constant.REDIS_ACCOUNT_INFO + cache.getAccount();
        redisService.objAddExpire(key, cache, accountCacheTime, TimeUnit.MINUTES);
        return key;
    }

    /**
     * 从缓存中获取账号的基本信息
     *
     * @param account 账号
     * @return
     */
    public AccountInfoCache getAccountInfoCache(String account) {
        //优先从redis中获取
        Boolean hasKey = redisService.hasKey(Constant.REDIS_ACCOUNT_INFO + account);
        if (hasKey) {
            return (AccountInfoCache) redisService.objGet(account);
        }
        return null;
    }

    /**
     * 刷新账号缓存的时间
     *
     * @param account 账号
     * @return
     */
    public Boolean refreshAccountInfoCache(String account) {
        return redisService.expire(Constant.REDIS_ACCOUNT_INFO + account, accountCacheTime, TimeUnit.MINUTES);
    }

    /**
     * 删除账号缓存
     *
     * @param account 账号
     */
    public Boolean removeAccountInfoCache(String account) {
        return redisService.delKey(Constant.REDIS_ACCOUNT_INFO + account);
    }


    /**
     * 缓存权限
     *
     * @param cache 权限内容
     * @return
     */
    public String addPermissionCache(PermissionCache cache){
        String key = Constant.REDIS_PERMISSION_INFO + cache.getAccount();
        redisService.objAddExpire(key, cache, permissionCacheTime, TimeUnit.MINUTES);
        return key;
    }


    /**
     * 从缓存中获取账号的基本信息
     *
     * @param account 账号
     * @return
     */
    public PermissionCache getPermissionCache(String account) {
        //优先从redis中获取
        Boolean hasKey = redisService.hasKey(Constant.REDIS_PERMISSION_INFO + account);
        if (hasKey) {
            return (PermissionCache) redisService.objGet(account);
        }
        return null;
    }


    /**
     * 删除账号缓存
     *
     * @param account 账号
     */
    public Boolean removePermissionCache(String account) {
        return redisService.delKey(Constant.REDIS_PERMISSION_INFO + account);
    }

    /**
     * 缓存token的信息
     * <p>
     * 用账号作为key方便扩展
     * 若不允许一个账号多处登陆，每次刷新token即可
     * 若允许，每次用账号查询，若存在且不过期，不刷新
     * </p>
     *
     * @param account 账号信息
     * @return token
     */
    public String addToken(String account) {
        String key = Constant.REDIS_JWT_TOKEN + account;
        String token = JwtUtil.sign(account, System.currentTimeMillis());
        //存入的过程可能会有一点误差，在请求接口的时候进行校验，若剩余时间小于 10分钟 则进行刷新token，这样多端登陆的情况下可能其他端的会被踢掉。
        //或者其他端请求接口的时候如果发现redis中的token时间更新了，则相应接口的同时刷新header的token
        redisService.strAddExpire(key, token, tokenCacheTime, TimeUnit.MINUTES);
        return token;
    }

    /**
     * 获取token
     *
     * @param account 账号
     * @return
     */
    public String getToken(String account){
        String key = Constant.REDIS_JWT_TOKEN + account;
        if (!redisService.hasKey(key)) {
            return null;
        }
        return redisService.strGet(key);
    }

    /**
     * 刷新token有效期
     * @param account account
     * @return
     */
    public Boolean refreshToken(String account) {
        String key = Constant.REDIS_JWT_TOKEN + account;
        return redisService.expire(key, tokenCacheTime, TimeUnit.MINUTES);
    }

    /**
     * 删除token的缓存
     *
     * @param account 账号
     * @return
     */
    public Boolean removeToken(String account) {
        String key = Constant.REDIS_JWT_TOKEN + account;
        return redisService.delKey(key);
    }


}
