package com.yjy.bean.dto.sys;

import lombok.Data;

/**
 * @author zhangjl
 * @description 科室信息保存
 * @date 2020-08-06 15:32
 */
@Data
public class SysDepSaveDto {

    private String idDep;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 部门编码唯一
     */
    private String depCd;

    /**
     * 科室主键
     */
    private String idOrg;

    /**
     * 上一级id，默认1 顶层部门
     */
    private String idParent;

    /**
     * 备注
     */
    private String remark;
}
