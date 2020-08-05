package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 客户机构表
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_org", type = IdType.ASSIGN_UUID )
    private String idOrg;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构编号
     */
    private String orgCd;

    /**
     * 备注
     */
    private String reamark;

    /**
     * 状态 1正常 0停用
     */
    private Integer state;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;


}
