package com.yjy.bean.base;

import lombok.Data;

/**
 * @author zhangjl
 * @description 账号信息缓存
 * @date 2020-07-28 21:08
 */
@Data
public class AccountInfoCache {

    /**
     * 账号主键
     */
    private String idAccount;
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;
    /**
     * 账号对应的用户姓名（可能没有）
     */
    private String name;
    /**
     * 个人主键
     */
    private String idPerson;
    /**
     * 机构主键
     */
    private String idOrg;

    /**
     * 部门主键
     */
    private String idDep;

}
