package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统管理员账号表
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_account", type = IdType.ASSIGN_UUID)
    private String idAccount;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码加盐
     */
    private String salt;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * 状态 1正常 0注销 2停用
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
