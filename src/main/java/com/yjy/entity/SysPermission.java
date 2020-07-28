package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统权限池
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_permission", type = IdType.AUTO)
    private String idPermission;

    /**
     * 权限标题
     */
    private String title;

    /**
     * 模块编码
     */
    private String cd;

    /**
     * 权限等级 1菜单 2按钮 3属性
     */
    private Integer level;

    /**
     * 权限编码，层级递进，如：user:save:name （全局唯一）
     */
    private String permissionCd;

    /**
     * 跳转url
     */
    private String url;

    /**
     * 上一级权限主键 顶级默认0
     */
    private String idParent;

    /**
     * 1正常 0停用
     */
    private Integer state;

    private String createUser;

    private LocalDateTime createTime;

    private String updateUser;

    private LocalDateTime updateTime;


}
