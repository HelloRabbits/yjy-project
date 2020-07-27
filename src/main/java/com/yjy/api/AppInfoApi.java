package com.yjy.api;

import com.yjy.entity.AppInfo;
import com.yjy.service.IAppInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangjl
 * @description appinfo
 * @date 2020-06-15 15:23
 */
@Component
public class AppInfoApi{
    @Resource
    private IAppInfoService appInfoService;


    public List<AppInfo> queryListFromProperties() {
        return appInfoService.queryListFromProperties();
    }

    public List<AppInfo> queryAllList() {
        return appInfoService.queryAllList();
    }

    public AppInfo getAppInfoWithAppId(String appKey) {
        return appInfoService.getAppInfoWithAppId(appKey);
    }
}
