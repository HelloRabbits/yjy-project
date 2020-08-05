package com.yjy.bean.base;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhangjl
 * @description 基础分页参数
 * @date 2020-08-05 13:08
 */
@ToString
@Data
public class PageQo {
    private int pageNo = 1;
    private int pageSize = 20;
}
