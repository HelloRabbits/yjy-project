package com.yjy.common;

/**
 * @author zhangjl
 * @description 统一异常类
 * @date 2020-05-26 12:11
 */
public class QuestionException extends Exception{

    private int code;

    private String msg;

    public QuestionException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public QuestionException(String msg){
        super(msg);
        this.code = 500;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
