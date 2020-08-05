package com.yjy.controller;

import com.yjy.api.sys.SysOrgApi;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.dto.sys.SysOrgSaveDto;
import com.yjy.bean.qo.sys.SysOrgQo;
import com.yjy.bean.vo.sys.SysOrgListVo;
import com.yjy.common.Constant;
import com.yjy.common.Response;
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
        return Response.success(sysOrgApi.save(dto));
    }

    @PostMapping("query/list")
    public Response<List<SysOrgListVo>> queryList(@RequestBody SysOrgQo qo) {
        return Response.success(sysOrgApi.queryList(qo));
    }

    @PostMapping("query/page")
    public Response<PageInfo<SysOrgListVo>> queryPage(@RequestBody SysOrgQo qo){
        return Response.success(sysOrgApi.queryPage(qo));
    }
}
