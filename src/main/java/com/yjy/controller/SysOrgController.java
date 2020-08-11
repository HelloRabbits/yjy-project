package com.yjy.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yjy.api.sys.SysOrgApi;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.dto.sys.SysOrgSaveDto;
import com.yjy.bean.qo.sys.SysOrgQo;
import com.yjy.bean.vo.sys.SysOrgVo;
import com.yjy.common.Constant;
import com.yjy.common.Response;
import com.yjy.entity.SysOrg;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangjl
 * @description 机构服务
 * @date 2020-08-05 11:09
 */
@RequestMapping(Constant.URL_PRE_API + "/org")
@RestController
public class SysOrgController {

    @Resource
    private SysOrgApi sysOrgApi;

    @PutMapping("saveOrUpdate")
    public Response<String> save(@RequestBody SysOrgSaveDto dto) {
        return Response.success(sysOrgApi.saveBase(BeanUtil.toBean(dto, SysOrg.class)));
    }

    @PostMapping("query/list")
    public Response<List<SysOrgVo>> queryList(@RequestBody SysOrgQo qo) {
        return Response.success(sysOrgApi.queryList(qo));
    }

    @PostMapping("query/page")
    public Response<PageInfo<SysOrgVo>> queryPage(@RequestBody SysOrgQo qo){
        return Response.success(sysOrgApi.queryPage(qo));
    }

    @GetMapping("get/detail/id")
    public Response<SysOrgVo> getDetailById(@RequestParam(name = "idOrg") String idOrg){
        return Response.success(sysOrgApi.getDetailById(idOrg));
    }

}
