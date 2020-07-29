package com.yjy.bean.base;

import lombok.Data;

import java.util.Set;

/**
 * @author zhangjl
 * @description 权限缓存
 * @date 2020-07-29 20:16
 */
@Data
public class PermissionCache {

    private String account;
    /**
     * 角色编码集合
     */
    private Set<String> roles;
    /**
     * 权限编码集合
     */
    private Set<String> permissions;

}
