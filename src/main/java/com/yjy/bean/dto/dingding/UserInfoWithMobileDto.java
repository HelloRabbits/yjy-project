package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangjl
 * @description 按手机号码获取用户信息
 * @date 2020-06-15 22:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoWithMobileDto extends DingDingBaseDto{

    private String userid;
}
