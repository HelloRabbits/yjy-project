package com.yjy.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yjy.api.account.PersonApi;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.dto.sys.SysPersonSaveDto;
import com.yjy.bean.qo.sys.SysPersonQo;
import com.yjy.bean.vo.sys.SysPersonVo;
import com.yjy.common.Response;
import com.yjy.entity.SysPerson;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangjl
 * @description 人员信息服务
 * @date 2020-08-11 10:34
 */
@RequestMapping("/api/person/")
@RestController
public class SysPersonController {

    @Resource
    private PersonApi personApi;

    @PutMapping("save")
    public Response<String> save(@RequestBody SysPersonSaveDto dto) {
        SysPerson sysPerson = BeanUtil.toBean(dto, SysPerson.class);
        String id;
        if (StrUtil.isEmpty(sysPerson.getIdPerson())) {
            id = personApi.saveBase(sysPerson);
        } else {
            id = personApi.updateBase(sysPerson);
        }
        return Response.success(id);
    }

    @PostMapping("queryList")
    public Response<List<SysPersonVo>> queryList(@RequestBody SysPersonQo qo){
        return Response.success(personApi.queryList(qo));
    }

    @PostMapping("queryPage")
    public Response<PageInfo<SysPersonVo>> queryPage(@RequestBody SysPersonQo qo){
        return Response.success(personApi.queryPage(qo));
    }

    @GetMapping("get/detail/id")
    public Response<SysPersonVo> getDetailById(@RequestParam(name="idPerson") String idPerson) {
        return Response.success(personApi.getDetailById(idPerson));
    }
}
