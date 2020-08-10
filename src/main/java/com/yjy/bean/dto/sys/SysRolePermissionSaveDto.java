package com.yjy.bean.dto.sys;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author zhangjl
 * @description 角色和权限的关系
 * @date 2020-08-07 09:49
 */
@Data
public class SysRolePermissionSaveDto {

    @NotEmpty(message = "角色id不能为空")
    private String idRole;

    private List<String> idPermissions;
}
