package com.yjy.common;

/**
 * @author zhangjl
 * @description 应用类型
 * @date 2020-05-26 21:56
 */
public enum AppType {
    /**
     * h5微应用
     */
    MINI_H5("MINI_H5"),
    /**
     * 机器人端
     */
    ROBOT("ROBOT");


    AppType(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {


        return code;
    }
}
