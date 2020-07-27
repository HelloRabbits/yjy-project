package com.yjy.bean.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhangjl
 * @description 钉钉接口基础返回
 * @date 2020-05-29 10:05
 */
@Data
@ToString
public class DingDingBaseDto {
    private Integer errcode;
    private String errmsg;
}
