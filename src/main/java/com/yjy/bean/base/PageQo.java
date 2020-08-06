package com.yjy.bean.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhangjl
 * @description 基础分页参数
 * @date 2020-08-05 13:08
 */
@ToString
@Data
public class PageQo implements Serializable {
    private int pageNo = 1;
    private int pageSize = 20;
}
