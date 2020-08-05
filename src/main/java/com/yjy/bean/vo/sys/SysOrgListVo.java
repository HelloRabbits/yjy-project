package com.yjy.bean.vo.sys;

import lombok.Data;

/**
 * @author zhangjl
 * @description
 * @date 2020-08-05 13:03
 */
@Data
public class SysOrgListVo {

    private String idOrg;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构编号
     */
    private String orgCd;

    /**
     * 备注
     */
    private String reamark;

    /**
     * 状态 1正常 0停用
     */
    private Integer state;


}
