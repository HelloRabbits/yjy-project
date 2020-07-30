package com.yjy.service.sdk;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.yjy.common.Constant;
import com.yjy.common.enums.ErrorCodeEnum;
import com.yjy.common.exception.QuestionException;
import com.yjy.entity.AppInfo;
import com.yjy.service.IAppInfoService;
import com.yjy.utils.RedisUtils;
import com.yjy.utils.SpringBeanFactoryUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjl
 * @description token服务
 * @date 2020-05-28 19:34
 */
@Slf4j
public class TokenService {

    private final IAppInfoService appInfoService;

    public TokenService(IAppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    /**
     * 锁
     */
    private final static ReentrantLock tokenLock = new ReentrantLock();

    /**
     * 初始化时存放appInfo，支持更新
     */
    private final static HashMap<String, AppInfo> APP_INFO = new HashMap<>(16);

    @PostConstruct
    private void init() {
        //初始化appInfo
        //appInfo数据量不会很多，如果太多可以将
        List<AppInfo> appInfos = appInfoService.queryAllList();
        appInfos.forEach(app -> APP_INFO.put(app.getAppKey(), app));
    }

    /**
     * 若传入的appId没有在 appInfo 中，则尝试从新获取
     * 不考虑并发问题，短时间内重复查询不影响
     *
     * @param appKey appKey
     */
    private static void refreshAppInfo(String appKey) {
        //主要考虑将appinfo放在数据库的情况
        //如果需要查库，使用静态获取bean的方式，保证此方法为静态，引用方便
        IAppInfoService bean = SpringBeanFactoryUtil.getBean(IAppInfoService.class);
        AppInfo app = bean.getAppInfoWithAppId(appKey);
        if (ObjectUtil.isNotEmpty(app)) {
            APP_INFO.put(app.getAppKey(), app);
        }
    }

    /**
     * 获取appKey
     *
     * @param appKey appKey
     * @return
     */
    public static AppInfo getAppInfo(String appKey) {
        return APP_INFO.get(appKey);
    }

    /**
     * 获取token
     *
     * @return
     * @throws QuestionException
     */
    public static String getToken(String appKey) throws QuestionException {
        AppInfo appInfo = APP_INFO.get(appKey);
        if (appInfo == null) {
            //刷新
            refreshAppInfo(appKey);
            //重试一次
            appInfo = APP_INFO.get(appKey);
            if (appInfo == null) {
                throw new QuestionException(ErrorCodeEnum.ERROR_20001.getCode(), "appKey异常，没有匹配到");
            }
        }
        //先从缓存获取
        String token = RedisUtils.getService().strGet(Constant.DINGDING_REDIS_TOKEN + appKey);
        if (StrUtil.isEmpty(token)) {
            //加锁不重复获取
            tokenLock.lock();
            //再次获取，可能其他线程已经执行过了
            token = RedisUtils.getService().strGet(Constant.DINGDING_REDIS_TOKEN + appKey);
            if (token != null) {
                tokenLock.unlock();
                return token;
            }
            //token在有效期内重复获取结果相同,且自动续期
            token = DingDingSdk.getDingDingUserSdk(appKey).getAccessToken(appInfo.getAppSecret());
            RedisUtils.getService().strAddExpire(Constant.DINGDING_REDIS_TOKEN + appKey, token, 7100L, TimeUnit.SECONDS);
            tokenLock.unlock();
        }
        log.info("thread:{}, token:{}", Thread.currentThread().getName(), token);
        return token;
    }
}
