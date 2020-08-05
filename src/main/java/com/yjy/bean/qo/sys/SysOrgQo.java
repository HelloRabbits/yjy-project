package com.yjy.bean.qo.sys;

import com.yjy.bean.base.PageQo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjl
 * @description
 * @date 2020-08-05 13:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SysOrgQo extends PageQo {


    private String orgName;

}
