package com.yjy.bean.vo.sys;

import com.yjy.bean.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjl
 * @description
 * @date 2020-08-07 10:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SysRolePermissionVo extends BaseVo {

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
     * 上一级权限主键 顶级默认0
     */
    private String idParent;
}
