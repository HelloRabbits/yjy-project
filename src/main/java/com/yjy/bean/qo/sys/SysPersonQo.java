package com.yjy.bean.qo.sys;

import com.yjy.bean.base.PageQo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangjl
 * @description 人员信息
 * @date 2020-08-11 10:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPersonQo extends PageQo {

    private String name;

    private String mobile;
}
