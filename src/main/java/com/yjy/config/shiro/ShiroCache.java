package com.yjy.config.shiro;

import com.yjy.common.Constant;
import com.yjy.utils.RedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

/**
 * @author zhangjl
 * @description  自定义shiro缓存 通过redis实现
 * @date 2020-07-28 12:05
 */
public class ShiroCache<K, V> implements Cache<K, V> {

    /**
     * 缓存的key
     *
     * @param key key
     * @return
     */
    private String getKey(Object key){
        return Constant.PREFIX_SHIRO_CACHE + key.toString();
    }


    @Override
    public V get(K k) throws CacheException {
        return (V)RedisUtils.getService().objGet(getKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
