package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 人员信息表
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_person", type = IdType.ASSIGN_UUID)
    private String idPerson;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 账号记录主键
     */
    private String idAccount;

    /**
     * 是否网格管理员 1是 0否
     */
    private Integer gmFlag;

    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private String updateUser;

    /**
     * 创建时间
     */
    private LocalDateTime updateTime;


}
