package com.yjy.common.enums;

/**
 * @author zhangjl
 * @description 应用类型
 * @date 2020-05-26 21:56
 */
public enum AppTypeEnum {
    /**
     * h5微应用
     */
    MINI_H5("MINI_H5"),
    /**
     * 机器人端
     */
    ROBOT("ROBOT");


    AppTypeEnum(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {


        return code;
    }
}
