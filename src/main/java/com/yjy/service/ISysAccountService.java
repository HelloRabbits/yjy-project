package com.yjy.service;

import com.yjy.bean.dto.account.SysUserPermissionDto;
import com.yjy.bean.dto.sys.SysUserPermissionMenuDto;
import com.yjy.entity.SysAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统管理员账号表 服务类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
public interface ISysAccountService extends IService<SysAccount> {

    /**
     * 查询当前用户的权限集合
     *
     * @param account 账号
     * @return SysUserPermissionDto
     */
    List<SysUserPermissionDto> queryUserPermissionList(String account);

    /**
     * 获取菜单列表
     *
     * @param idAccount 账号
     * @return List<SysUserPermissionMenuDto>
     */
    List<SysUserPermissionMenuDto> queryUserPermissionMenuList(String idAccount);


    /**
     * 根据账号查询用户信息
     *
     * @param account 账号
     * @return SysAccount
     */
    SysAccount getWithAccount(String account);
}
