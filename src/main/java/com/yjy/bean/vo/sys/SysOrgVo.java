package com.yjy.bean.vo.sys;

import com.yjy.bean.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author zhangjl
 * @description
 * @date 2020-08-05 13:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOrgVo extends BaseVo {

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

    private String idParent;

    /**
     * 状态 1正常 0停用
     */
    private Integer state;

    private LocalDateTime createTime;


}
