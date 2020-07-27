package com.yjy.common;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjl
 * @description 使用map缓存token
 * @date 2020-05-26 19:33
 */
public class LocalCache {

    //使用guava的内部缓存，当然也可使用map来存
    private final static Cache<String, String> TOKEN_CACHE = CacheBuilder.newBuilder()
            .maximumSize(100) // 设置缓存的最大容量
            .expireAfterWrite(7000, TimeUnit.SECONDS) // 设置缓存在写入7000后失效
            .concurrencyLevel(10) // 设置并发级别为10
            .recordStats() // 开启缓存统计
            .build();

    public static String getToken(String key) {
        // 获取缓存
        return TOKEN_CACHE.getIfPresent(key);
    }

    public static void putToken(String key, String value) {
        // 放入缓存
        TOKEN_CACHE.put(key, value);
    }
}
