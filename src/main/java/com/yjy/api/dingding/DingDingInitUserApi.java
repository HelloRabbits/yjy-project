package com.yjy.api.dingding;

import cn.hutool.core.util.StrUtil;
import com.yjy.config.DingDingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhangjl
 * @description 钉钉初始化全部的用户信息服务
 * @date 2020-07-27 10:28
 */
@Component
public class DingDingInitUserApi {

    @Resource
    private DingDingProperties dingDingProperties;

    public void initUsers(String appKey){
        appKey = StrUtil.isEmpty(appKey) ? dingDingProperties.getAppKey() : appKey;

        //拉取钉钉的部门列表

    }

}
