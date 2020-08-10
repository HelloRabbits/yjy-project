package com.yjy.bean.vo.account;

import lombok.Data;

import java.util.List;


/**
 * @author zhangjl
 * @description 登陆返回信息
 * @date 2020-08-10 16:11
 */
@Data
public class LogonBackInfoVo {
    private String account;

    private String idAccount;

    private List<PermissionMenuVo> menuVos;
}
