package com.yjy.controller;

import com.yjy.api.account.AccountPermissionApi;
import com.yjy.bean.dto.account.AccountRoleSaveDto;
import com.yjy.common.Response;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangjl
 * @description 账号服务
 * @date 2020-08-10 13:50
 */
@RequestMapping("sys/account/")
@RestController
public class SysAccountController {

    @Resource
    private AccountPermissionApi accountPermissionApi;

    @PutMapping("saveAccountRole")
    public Response<String> saveAccountRole(@RequestBody AccountRoleSaveDto dto) {
        accountPermissionApi.saveAccountRole(dto);
        return Response.success();
    }


}
