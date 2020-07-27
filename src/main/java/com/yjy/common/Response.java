package com.yjy.common;

import com.yjy.common.enums.ErrorCodeEnum;

/**
 * @author zhangjl
 * @description
 * @date 2020-05-26 12:15
 */
public class Response<T> {

    private int code = ErrorCodeEnum.SUCCESS.getCode();

    private String msg = "success";

    private T data;

    private Response(){
        //禁止通过外部创建
    }

    public static <T> Response<T> success(T t) {
        return new Response<T>().setData(t);
    }

    public static <T> Response<T> fail(int code, String msg) {
        return new Response<T>().setCode(code).setMsg(msg);
    }


    public static <T> Response<T> fail_500(String msg) {
        return new Response<T>().setCode(ErrorCodeEnum.ERROR_500.getCode()).setMsg(msg);
    }

    public int getCode() {
        return code;
    }

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }
}
