package com.yjy.bean.vo.sys;

import com.yjy.bean.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangjl
 * @description 权限
 * @date 2020-08-06 16:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermissionVo extends BaseVo {

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
}
