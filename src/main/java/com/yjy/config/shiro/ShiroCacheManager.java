package com.yjy.config.shiro;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author zhangjl
 * @description
 * @date 2020-07-28 12:07
 */
public class ShiroCacheManager implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroCache<>();
    }
}
