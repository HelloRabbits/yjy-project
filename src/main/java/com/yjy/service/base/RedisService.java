package com.yjy.service.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjl
 * @description redis服务
 * @date 2020-04-27 20:03
 */
@Slf4j
@Component
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 字符串类型操作
     *
     * @return
     */
    private ValueOperations<String, Object> opsForValue() {
        return redisTemplate.opsForValue();
    }

    /**
     * hash类型操作
     *
     * @return
     */
    private HashOperations<String, Object, Object> opsForHash() {
        return redisTemplate.opsForHash();
    }


    /**
     * zset 操作
     *
     * @return
     */
    private ZSetOperations<String, Object> opsForZSet() {
        return redisTemplate.opsForZSet();
    }

    /**
     * set操作
     *
     * @return
     */
    private SetOperations<String, Object> opsForSet() {
        return redisTemplate.opsForSet();
    }

    /**
     * 集合操作
     *
     * @return
     */
    private ListOperations<String, Object> opsForList() {
        return redisTemplate.opsForList();
    }

    // ---------------------------------------------- 通用  ----------------------------------------------
    //添加过期时间
    //判断key是否存在

    /**
     * 删除key
     *
     * @param key key
     * @return
     */
    public Boolean delKey(String key) {


        return redisTemplate.delete(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key key
     * @return Boolean
     */
    public Boolean hasKey(String key) {
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey == null ? false : hasKey;
    }

    /**
     * 设置key的过期时间
     *
     * @param key key
     * @param time time
     * @param unit 单位
     * @return Boolean
     */
    public Boolean expire(String key, long time, TimeUnit unit) {
        Boolean expire = redisTemplate.expire(key, time, unit);
        return expire == null ? false : expire;
    }
    // ---------------------------------------------- list  ----------------------------------------------
    // ---------------------------------------------- hash -----------------------------------------------

    // ---------------------------------------------- set  ----------------------------------------------

    /**
     * set集合-add
     *
     * @param key   key
     * @param value value
     * @return
     */
    public Long setAdd(String key, Object value) {
        return opsForSet().add(key, value);
    }

    /**
     * set集合
     * 判断是否是成员
     *
     * @param key   key
     * @param value value
     * @return
     */
    public Boolean setIsMember(String key, Object value) {
        return opsForSet().isMember(key, value);
    }

    // ---------------------------------String ---------------------------

    /**
     * 字符串操作
     * 添加一个字符串类型的值
     *
     * @param key   key
     * @param value value
     */
    public void strAdd(String key, String value) {
        opsForValue().set(key, value);
    }


    /**
     * 字符串操作
     * 获取value
     *
     * @param key key
     * @return
     */
    public String strGet(String key) {
        Object value = opsForValue().get(key);
        return value == null ? "" : value.toString();
    }


    /**
     * string操作-添加有实效的value
     *
     * @param key   key
     * @param value value
     * @param time  时长
     * @param unit  单位
     */
    public void strAddExpire(String key, String value, long time, TimeUnit unit) {
        opsForValue().set(key, value, time, unit);
    }


    /**
     * 对象类型的字符串
     *
     * @param key
     * @return
     */
    public Object objGet(String key) {
        return opsForValue().get(key);
    }

    /**
     * string操作-添加有实效的value
     *
     * @param key   key
     * @param value value
     * @param time  时长
     * @param unit  单位
     */
    public void objAddExpire(String key, Object value, long time, TimeUnit unit) {
        opsForValue().set(key, value, time, unit);
    }

    /**
     * 如果不存在则存入
     *
     * @param key   key
     * @param value value
     * @param time  时长
     * @param unit  单位
     */
    public Boolean setIfAbsent(String key, Object value, long time, TimeUnit unit) {
        Boolean aBoolean = opsForValue().setIfAbsent(key, value, time, unit);
        return aBoolean == null ? false : aBoolean;
    }

    /**
     * 如果存在则存入
     *
     * @param key   key
     * @param value value
     * @param time  时长
     * @param unit  单位
     */
    public Boolean setIfPresent(String key, Object value, long time, TimeUnit unit) {
        return opsForValue().setIfPresent(key, value, time, unit);
    }

    /**
     * bitMap 比较
     * 需要先了解bitMap算法
     *
     * @param key   key
     * @param value long类型
     * @return
     */
    public Boolean bitMapContain(String key, Long value) {
        return redisTemplate.opsForValue().getBit(key, value);
    }

    /**
     * bitMap 比较
     * 需要先了解
     *
     * @param key   key
     * @param value value
     * @param var4
     * @return
     */
    public Boolean setBit(String key, long value, boolean var4) {
        return redisTemplate.opsForValue().setBit(key, value, var4);
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
}
