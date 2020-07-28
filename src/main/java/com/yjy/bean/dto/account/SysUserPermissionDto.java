package com.yjy.bean.dto.account;

import lombok.Data;

/**
 * @author zhangjl
 * @description 用户权限
 * @date 2020-07-22 14:32
 */
@Data
public class SysUserPermissionDto {
    /**
     * 权限主键
     */
    private String idPermission;
    /**
     * 权限标题
     */
    private String title;
    /**
     * 用户主键
     */
    private String idAccount;
    /**
     * 角色主键
     */
    private String idRole;
    /**
     * 权限编码
     */
    private String permissionCd;
    /**
     * 角色编码
     */
    private String roleCd;

}
