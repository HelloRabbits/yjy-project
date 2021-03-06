package com.yjy.common.exception;

import com.yjy.common.enums.ErrorCodeEnum;

/**
 * @author zhangjl
 * @description 没有权限异常
 * @date 2020-07-27 10:15
 */
public class UnPermissionException extends Exception {

    private int code;

    public UnPermissionException(String message, int code) {
        super(message);
        this.code = code;
    }


    public UnPermissionException(String message) {
        super(message);
        this.code = ErrorCodeEnum.ERROR_403.getCode();
    }

    public int getCode() {
        return code;
    }
}
