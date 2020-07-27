package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author zhangjl
 * @description 部门员工详情
 * @date 2020-05-29 15:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class DeptUserDetailsDto extends DingDingBaseDto {

    private Boolean hasMore;
    private List<UserlistBean> userlist;


    @Data
    public static class UserlistBean {
        /**
         * 员工在当前企业内的唯一标识，也称staffId。可由企业在创建时指定，并代表一定含义比如工号，创建后不可修改
         */
        private String userid;
        /**
         * 员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变
         */
        private String unionid;
        /**
         * 手机号
         */
        private String mobile;
        /**
         * 分机号
         */
        private String tel;
        /**
         * 办公地点
         */
        private String workPlace;
        /**
         *
         * 备注
         */
        private String remark;
        /**
         * 表示人员在此部门中的排序，列表是按order的倒序排列输出的，即从大到小排列输出的
         * （钉钉管理后台里面调整了顺序的话order才有值）
         */
        private Long order;
        /**
         * 是否是企业的管理员，true表示是，false表示不是
         */
        private Boolean isAdmin;
        /**
         * 是否为企业的老板，true表示是，false表示不是
         */
        private Boolean isBoss;
        /**
         * 是否隐藏号码，true表示是，false表示不是
         */
        private Boolean isHide;
        /**
         * 是否是部门的主管，true表示是，false表示不是
         */
        private Boolean isLeader;
        /**
         * 成员名称
         */
        private String name;
        /**
         * 表示该用户是否激活了钉钉
         */
        private Boolean active;
        /**
         * 职位信息
         */
        private String position;
        /**
         * 员工的邮箱
         */
        private String email;
        /**
         * 员工的企业邮箱，如果员工的企业邮箱没有开通，返回信息中不包含
         */
        private String orgEmail;
        /**
         * 头像
         */
        private String avatar;
        /**
         * 工号
         */
        private String jobnumber;
        /**
         * 扩展参数
         */
        private String extattr;
        /**
         * 成员所属部门id列表
         */
        private List<Long> department;
    }
}
