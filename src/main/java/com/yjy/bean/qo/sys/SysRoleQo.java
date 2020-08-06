package com.yjy.bean.qo.sys;

import com.yjy.bean.base.PageQo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjl
 * @description 角色查询基础
 * @date 2020-08-06 15:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SysRoleQo extends PageQo {

    private String idOrg;

}
