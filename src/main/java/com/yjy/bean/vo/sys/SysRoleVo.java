package com.yjy.bean.vo.sys;

import com.yjy.bean.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangjl
 * @description 角色返回信息
 * @date 2020-08-06 15:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleVo extends BaseVo {

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

    /**
     * 状态 1正常 0停用
     */
    private Integer state;

    private String reamark;
}
