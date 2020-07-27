package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangjl
 * @description 按unionId获取用户信息
 * @date 2020-06-15 22:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoWithUnionIdDto extends DingDingBaseDto{

    private String userid;

    /**
     * 联系类型，0表示企业内部员工，1表示企业外部联系人
     */
    private String contactType;

}
