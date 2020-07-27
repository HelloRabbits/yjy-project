package com.yjy.service.sdk;


/**
 * @author zhangjl
 * <p>
 *     基于钉钉官方sdk的二次开发，将内部的bean转换本地的，方便以后扩展
 *     钉钉sdk服务汇总
 *     这样写无法预览所有的请求接口，接口被按类型划分到了各个模块中
 *     缺少总览。
 * </p>
 *
 * @date 2020-06-15 20:19
 */
public class DingDingSdk {

    /**
     * 用户服务
     *
     * @param appKey appKey
     * @return
     */
    public static IDingDingUserSdk getDingDingUserSdk(String appKey) {
        //这种方式会创建很多个bean，若有影响可以通过注入成bean在静态获取bean的方式调用
        return new DingDingUserSdk(appKey);
    }

    /**
     * 部门服务
     *
     * @param appKey appKey
     * @return
     */
    public static IDingDingDeptSdk getDingDingDeptSdk(String appKey) {
        return new DingDingDeptSdk(appKey);
    }

    /**
     * 发送消息服务
     *
     * @param appKey appKey
     * @return
     */
    public static IDingDingSendMsgSdk getDingDingSendMsgSdk(String appKey) {
        return new DingDingSendMsgSdk(appKey);
    }

    /**
     * 机器人服务
     *
     * @param appKey appKey
     * @return
     */
    public static IDingDingRobotSdk getDingDingRobotSdk(String appKey) {
        return new DingDingRobotSdk(appKey);
    }
}
