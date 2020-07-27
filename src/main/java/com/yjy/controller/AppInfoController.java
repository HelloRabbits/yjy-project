package com.yjy.controller;


import com.yjy.api.AppInfoApi;
import com.yjy.entity.AppInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangjl
 * @since 2020-06-15
 */
@Api(tags = "app信息")
@RestController
@RequestMapping("/app/info")
public class AppInfoController {

    @Resource
    private AppInfoApi appInfoApi;


    @ApiOperation(value = "查询全部的列表")
    @GetMapping("queryAllList")
    public List<AppInfo> queryAllList(){
        return appInfoApi.queryAllList();
    }

    //手动刷新
}
