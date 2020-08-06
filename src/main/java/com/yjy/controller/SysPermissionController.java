package com.yjy.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yjy.api.sys.SysPermissionApi;
import com.yjy.bean.dto.sys.SysPermissionSaveDto;
import com.yjy.common.Constant;
import com.yjy.common.Response;
import com.yjy.entity.SysPermission;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangjl
 * @description 权限服务
 * @date 2020-08-06 17:38
 */
@RequestMapping("api/permission")
@RestController
public class SysPermissionController {

    @Resource
    private SysPermissionApi sysPermissionApi;

    @PutMapping("save")
    public Response<String> save(@RequestBody SysPermissionSaveDto dto) {
        return Response.success(sysPermissionApi.saveBase(BeanUtil.toBean(dto, SysPermission.class)));
    }


}
