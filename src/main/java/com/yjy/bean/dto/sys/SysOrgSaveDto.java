package com.yjy.bean.dto.sys;

import lombok.Data;

/**
 * @author zhangjl
 * @description 机构信息保存
 * @date 2020-08-05 11:14
 */
@Data
public class SysOrgSaveDto {

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

}
