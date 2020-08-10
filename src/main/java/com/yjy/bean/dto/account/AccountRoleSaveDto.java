package com.yjy.bean.dto.account;

import lombok.Data;

import java.util.List;

/**
 * @author zhangjl
 * @description 账号角色保存
 * @date 2020-08-10 13:35
 */
@Data
public class AccountRoleSaveDto {

    private String idAccount;

    private List<String> idRoles;
}
