package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjl
 * @description 企业内部应用 - 按code获取用户信息
 * @date 2020-05-29 10:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class InnerCodeUserInfoDto extends DingDingBaseDto {
    /**
     * 级别。1是主管理员，2是子管理员，100是老板，0是其他（如普通员工）
     */
    private String sysLevel;
    /**
     * 是否是管理员，true：是，false：不是
     */
    private Boolean isSys;
    /**
     * 员工在当前企业内的唯一标识，也称staffId
     */
    private String userid;
}
