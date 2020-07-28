package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_role", type = IdType.AUTO)
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

    /**
     * 创建人
     */
    private String createUser;

    private LocalDateTime createTime;

    private String updateUser;

    private LocalDateTime updateTime;


}
