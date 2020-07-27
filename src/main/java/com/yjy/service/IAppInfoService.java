package com.yjy.service;


import com.yjy.entity.AppInfo;

import java.util.List;

/**
 * @author zhangjl
 * @description 获取应用信息
 * @date 2020-05-28 13:17
 */
public interface IAppInfoService {

    List<AppInfo> queryListFromProperties();

    /**
     * 查询全部的appInfo
     *
     * @return
     */
    List<AppInfo> queryAllList();

    /**
     * 根据appKey获取appInfo
     *
     * @param appKey appKey
     * @return
     */
    AppInfo getAppInfoWithAppId(String appKey);
}
