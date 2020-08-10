package com.yjy.service;

import com.yjy.bean.vo.sys.SysRolePermissionVo;
import com.yjy.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {
    /**
     * 根据角色id查询对应的权限
     *
     * @param idRole 角色id
     * @return  List<SysRolePermissionVo>
     */
    List<SysRolePermissionVo> queryListWithIdRole(String idRole);
}
