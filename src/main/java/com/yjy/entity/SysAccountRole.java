package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 账号角色表，账号与角色的关联关系
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_user_role", type = IdType.AUTO)
    private String idUserRole;

    private String idAccount;

    private String idRole;

    private LocalDateTime createTime;


}
