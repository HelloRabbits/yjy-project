package com.yjy.bean.dto.account;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author zhangjl
 * @description 变更密码
 * @date 2020-08-11 14:31
 */
@Data
public class ChangePwdDto {

    @NotEmpty
    private String oldPwd;

    @NotEmpty
    private String newPwd;
}
