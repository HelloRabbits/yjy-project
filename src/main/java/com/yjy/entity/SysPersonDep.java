package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门和成员之间的关系
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPersonDep implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_person_dep", type = IdType.AUTO)
    private String idPersonDep;

    /**
     * 人员主键
     */
    private String idPerson;

    /**
     * 科室主键
     */
    private String idDep;

    /**
     * 机构主键
     */
    private String idOrg;

    /**
     * 创建人
     */
    private String createUser;

    private LocalDateTime createTime;

    private String updateUser;

    private LocalDateTime updateTime;


}
