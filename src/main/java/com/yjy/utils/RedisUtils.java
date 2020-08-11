package com.yjy.utils;


import com.yjy.service.base.RedisService;

/**
 * @author zhangjl
 * @description
 * @date 2020-07-28 12:16
 */
public class RedisUtils {
    /**
     * 获取spring的bean
     *
     * @return
     */
    public static RedisService getService() {
        return SpringBeanFactoryUtil.getBean(RedisService.class);
    }

    /**
     * 根据前缀批量清空数据
     *
     * @param keyPre
     */
    public static void removeALlKey(String keyPre){
        // TODO: 2020/8/7 批量清空缓存
        

    }

}
