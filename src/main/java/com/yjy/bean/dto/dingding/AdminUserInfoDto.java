package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author zhangjl
 * @description 系统管理员信息
 * @date 2020-05-29 15:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class AdminUserInfoDto extends DingDingBaseDto{

    private List<AdminListBean> adminList;


    @Data
    public static class AdminListBean {
        /**
         * 管理员角色，1表示主管理员，2表示子管理员
         */
        private Long sysLevel;
        /**
         * 员工id
         */
        private String userid;
    }
}
