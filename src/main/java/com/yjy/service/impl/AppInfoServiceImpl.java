package com.yjy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yjy.config.DingDingProperties;
import com.yjy.entity.AppInfo;
import com.yjy.mapper.AppInfoMapper;
import com.yjy.service.IAppInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjl
 * @description 获取应用信息
 * @date 2020-05-28 13:21
 */
@Component
public class AppInfoServiceImpl implements IAppInfoService {

    @Resource
    private DingDingProperties dingProperties;

    @Resource
    private AppInfoMapper appInfoMapper;


    @Override
    public List<AppInfo> queryListFromProperties() {
        // TODO: 2020/5/28 后续可以从 数据库中拉取
        List<AppInfo> appInfos = new ArrayList<>(16);
        appInfos.add(BeanUtil.toBean(dingProperties, AppInfo.class));
        return appInfos;
    }

    @Override
    public List<AppInfo> queryAllList(){
        return appInfoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public AppInfo getAppInfoWithAppId(String appKey) {;
        return appInfoMapper.selectOne(Wrappers.lambdaQuery(AppInfo.class).eq(AppInfo::getAppKey, appKey));
    }

}
