package com.yjy.bean.vo.account;

import lombok.Data;

import java.util.List;

/**
 * @author zhangjl
 * @description 权限菜单
 * @date 2020-08-10 13:55
 */
@Data
public class PermissionMenuVo {

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

    /**
     * 子菜单
     * 暂不考虑三级菜单
     */
    private List<PermissionMenuVo> subMenu;

}
