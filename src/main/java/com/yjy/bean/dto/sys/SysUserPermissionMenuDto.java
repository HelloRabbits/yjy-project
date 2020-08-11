package com.yjy.bean.dto.sys;

import lombok.Data;

/**
 * @author zhangjl
 * @description 菜单权限按钮
 * @date 2020-08-10 14:40
 */
@Data
public class SysUserPermissionMenuDto {

    /**
     * 权限标题
     */
    private String title;

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
}
