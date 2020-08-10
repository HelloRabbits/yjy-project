package com.yjy.service;

import com.yjy.bean.qo.sys.SysPermissionQo;
import com.yjy.bean.vo.sys.SysPermissionVo;
import com.yjy.entity.SysPermission;


/**
 * <p>
 * 系统权限池 服务类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
public interface ISysPermissionService extends BaseService<SysPermission, SysPermissionQo, SysPermissionVo>{

    /**
     * 根据cd查询权限。
     *
     * @param cd cd
     * @return SysPermission
     */
    SysPermission getWithCd(String cd);

    /**
     * 根据权限cd查询
     *
     * @param permissionCd  permissionCd
     * @return SysPermission
     */
    SysPermission getWithPermissionCd(String permissionCd);
}
