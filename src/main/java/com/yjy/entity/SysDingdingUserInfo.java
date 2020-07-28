package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 从钉钉获取用户信息表
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDingdingUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_d_user", type = IdType.AUTO)
    private String idDUser;

    /**
     * 内部用户id
     */
    private String idPerson;

    /**
     * 企业开放平台内唯一id
     */
    private String unionid;

    /**
     * 钉钉用户id
     */
    private String userid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 员工电子邮箱
     */
    private String email;

    /**
     * 是否激活 1是 0否
     */
    private Integer active;

    /**
     * 是否管理员 1是 0否
     */
    private Integer isAdmin;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 员工工号
     */
    private String jobnumber;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
