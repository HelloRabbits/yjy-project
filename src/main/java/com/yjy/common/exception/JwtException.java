package com.yjy.common.exception;

import com.yjy.common.enums.ErrorCodeEnum;

/**
 * @author zhangjl
 * @description 没有权限异常
 * @date 2020-07-27 10:15
 */
public class JwtException extends RuntimeException {

    public JwtException(String message) {
        super(message);
        //默认500
        this.code = ErrorCodeEnum.ERROR_500.getCode();
    }

    private int code;

    public JwtException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
