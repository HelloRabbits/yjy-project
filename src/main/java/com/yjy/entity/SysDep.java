package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统部门表
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDep implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_dep", type = IdType.ASSIGN_UUID)
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

    private String createUser;

    private LocalDateTime createTime;

    private String updateUser;

    private LocalDateTime updateTime;


}
