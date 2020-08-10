package com.yjy.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yjy.api.sys.SysRoleApi;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.dto.sys.SysRoleSaveDto;
import com.yjy.bean.qo.sys.SysRoleQo;
import com.yjy.bean.vo.sys.SysRoleVo;
import com.yjy.common.Constant;
import com.yjy.common.Response;
import com.yjy.entity.SysRole;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangjl
 * @description 角色服务
 * @date 2020-08-06 16:37
 */
@RequestMapping(Constant.URL_PRE_API + "/role")
@RestController
public class SysRoleController {

    @Resource
    private SysRoleApi sysRoleApi;

    @PutMapping("save")
    public Response<String> save(@RequestBody SysRoleSaveDto dto) {
        sysRoleApi.saveBase(BeanUtil.toBean(dto, SysRole.class));
        return Response.success();
    }


    /**
     * 基础条件查询
     *
     * @param qo 入参
     * @return
     */
    @PostMapping("queryList")
    public Response<List<SysRoleVo>> queryList(@RequestBody SysRoleQo qo) {
        return Response.success(sysRoleApi.queryList(qo));
    }

    /**
     * 基础条件查询
     *
     * @param qo
     * @return
     */
    @PostMapping("queryPage")
    public Response<PageInfo<SysRoleVo>> queryPage(@RequestBody SysRoleQo qo) {
        return Response.success(sysRoleApi.queryPage(qo));
    }

}
