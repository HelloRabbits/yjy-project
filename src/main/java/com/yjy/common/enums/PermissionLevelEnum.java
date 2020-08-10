package com.yjy.common.enums;

/**
 * @author zhangjl
 * @description 菜单等级
 * @date 2020-08-10 14:53
 */
public enum PermissionLevelEnum {
    /**
     * 菜单
     */
    MENU((byte) 1),
    /**
     * 接口
     */
    INTERFACES((byte) 2),
    /**
     * 属性
     */
    ATTRIBUTE((byte) 3),
    ;


    PermissionLevelEnum(byte level) {
        this.level = level;
    }

    public byte getLevel() {
        return level;
    }

    private byte level;
}
