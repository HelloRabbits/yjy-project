package com.yjy.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yjy.api.sys.SysPermissionApi;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.dto.sys.SysPermissionSaveDto;
import com.yjy.bean.dto.sys.SysRolePermissionSaveDto;
import com.yjy.bean.qo.sys.SysPermissionQo;
import com.yjy.bean.vo.sys.SysPermissionVo;
import com.yjy.bean.vo.sys.SysRolePermissionVo;
import com.yjy.common.Response;
import com.yjy.entity.SysPermission;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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


    @PostMapping("queryList")
    public Response<List<SysPermissionVo>> queryList(@RequestBody SysPermissionQo qo) {
        return Response.success(sysPermissionApi.queryList(qo));
    }


    @PostMapping("queryPage")
    public Response<PageInfo<SysPermissionVo>> queryPage(@RequestBody SysPermissionQo qo) {
        return Response.success(sysPermissionApi.queryPage(qo));
    }

    @GetMapping("get/detail/id")
    public Response<SysPermissionVo> getDetailById(@RequestParam String idPermission){
        return Response.success(sysPermissionApi.getDetailById(idPermission));
    }


    @PutMapping("saveRolePermission")
    public Response<String> saveRolePermission(@Validated @RequestBody SysRolePermissionSaveDto saveDto) {
        sysPermissionApi.saveRolePermission(saveDto);
        return Response.success();
    }

    @GetMapping("/queryRolePermission")
    public Response<List<SysRolePermissionVo>> queryRolePermission(@RequestParam(name = "idRole") String idRole) {
        return Response.success(sysPermissionApi.queryRolePermission(idRole));
    }

}
