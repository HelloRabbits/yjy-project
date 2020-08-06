package com.yjy.bean.vo.sys;

import com.yjy.bean.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangjl
 * @description 科室通用列表返回对象
 * @date 2020-08-06 14:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDepVo extends BaseVo {

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

    /**
     * 状态 1正常 0停用
     */
    private Integer state;
}
