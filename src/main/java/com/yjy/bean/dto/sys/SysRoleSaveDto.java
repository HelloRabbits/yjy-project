package com.yjy.bean.dto.sys;

import lombok.Data;

/**
 * @author zhangjl
 * @description
 * @date 2020-08-06 15:44
 */
@Data
public class SysRoleSaveDto {

    private String idRole;

    /**
     * 编号
     */
    private String roleCd;

    /**
     * 名称
     */
    private String roleNa;

    /**
     * 机构id，默认1，超级管理员创建
     */
    private String idOrg;

    /**
     * 等级  0 超级管理员 1机构管理员  2部门管理员
     */
    private Integer level;


    private String reamark;
}
