package com.yjy.bean.qo.sys;

import com.yjy.bean.base.PageQo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjl
 * @description 科室列表通用查询条件
 * @date 2020-08-06 14:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SysDepQo extends PageQo {


    private String idParent;

}
