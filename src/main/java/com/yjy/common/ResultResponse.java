package com.yjy.common;

/**
 * @author zhangjl
 * @description
 * @date 2020-05-26 12:15
 */
public class ResultResponse<T> {

    private int code = ErrorCode.SUCCESS.getCode();

    private String msg = "success";

    private T data;

    private ResultResponse(){
        //禁止通过外部创建
    }

    public static <T> ResultResponse<T> success(T t) {
        return new ResultResponse<T>().setData(t);
    }

    public static <T> ResultResponse<T> fail(int code, String msg) {
        return new ResultResponse<T>().setCode(code).setMsg(msg);
    }


    public static <T> ResultResponse<T> fail_500(String msg) {
        return new ResultResponse<T>().setCode(ErrorCode.ERROR_500.getCode()).setMsg(msg);
    }

    public int getCode() {
        return code;
    }

    public ResultResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
