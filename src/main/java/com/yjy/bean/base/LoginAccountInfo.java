package com.yjy.bean.base;

import lombok.Data;

/**
 * @author zhangjl
 * @description 登陆后用户信息缓存 单次请求有效
 * @date 2020-07-29 09:08
 */
@Data
public class LoginAccountInfo {
    /**
     * 账号id
     */
    private String idAccount;

    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 个人主键（未必有）
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

    /**
     * jwtToken 冗余字段，目前token中放置的有account信息
     */
    private String token;
}
