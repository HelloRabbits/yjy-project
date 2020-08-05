package com.yjy.common.enums;

/**
 * @author zhangjl
 * @description 基础状态代码
 * @date 2020-08-05 12:41
 */
public enum StateEnum {
    /**
     * 有效的
     */
    AVAILABLE(1),

    /**
     * 失效
     */
    INVALID(0),

    /**
     * 删除
     */
    delete(-1)
    ;


    StateEnum(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
