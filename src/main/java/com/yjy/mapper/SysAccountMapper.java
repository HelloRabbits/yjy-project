package com.yjy.mapper;

import com.yjy.bean.dto.account.SysUserPermissionDto;
import com.yjy.bean.dto.sys.SysUserPermissionMenuDto;
import com.yjy.entity.SysAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统管理员账号表 Mapper 接口
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
public interface SysAccountMapper extends BaseMapper<SysAccount> {

    /**
     * 查询当前用户的权限集合
     *
     * @param account 账号
     * @return List<SysUserPermissionDto>
     */
    List<SysUserPermissionDto> queryUserPermissionList(String account);


    /**
     * 获取菜单列表
     *
     * @param idAccount 账号
     * @return List<SysUserPermissionMenuDto>
     */
    List<SysUserPermissionMenuDto> queryUserPermissionMenuList(String idAccount);

}
