package com.yjy.bean.qo.sys;

import com.yjy.bean.base.PageQo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjl
 * @description 权限管理
 * @date 2020-08-06 16:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SysPermissionQo extends PageQo {


    /**
     * 上一级权限主键 顶级默认0
     */
    private String idParent;

}
