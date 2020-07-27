package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author zhangjl
 * @description 部门员工列表
 * @date 2020-05-29 14:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class DeptUsersDto extends DingDingBaseDto{
    /**
     * 是否有更多，用于分页查询
     */
    private Boolean hasMore;
    private List<UserlistBean> userlist;


    @Data
    public static class UserlistBean {
        /**
         * userId
         */
        private String userid;
        /**
         * 名字
         */
        private String name;

    }
}
