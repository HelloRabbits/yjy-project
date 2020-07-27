package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author zhangjl
 * @description 按userId获取用户信息详情
 * @date 2020-05-29 10:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class UserInfoDetailWithUserIdDto extends DingDingBaseDto{
    /**
     * 员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变
     */
    private String unionid;
    /**
     *备注
     */
    private String remark;
    /**
     * 员工在当前企业内的唯一标识，也称staffId。
     * 可由企业在创建时指定，并代表一定含义比如工号，创建后不可修改
     */
    private String userid;
    /**
     * 员工工号
     */
    private String jobnumber;
    /**
     * 员工名字
     */
    private String name;
    /**
     * 入职时间。Unix时间戳 （在OA后台通讯录中的员工基础信息中维护过入职时间才会返回)
     */
    private Date hiredDate;

    /**
     * 分机号（仅限企业内部开发调用）
     */
    private String tel;
    /**
     * 办公地点
     */
    private String workPlace;
    /**
     * 员工的电子邮箱
     */
    private String email;
    /**
     * 员工的企业邮箱，如果员工已经开通了企业邮箱，接口会返回，否则不会返回
     */
    private String orgEmail;
    /**
     * 在对应的部门中的排序，Map结构的json字符串，key是部门的id，value是人员在这个部门的排序值
     */
    private String orderInDepts;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 头像url
     */
    private String avatar;
    /**
     * 是否为企业的管理员，true表示是，false表示不是
     */
    private Boolean isAdmin;
    /**
     * 在对应的部门中是否为主管：Map结构的json字符串，key是部门的id，value是人员在这个部门中是否为主管，true表示是，false表示不是
     */
    private String isLeaderInDepts;
    /**
     * 是否为企业的老板，true表示是，false表示不是
     */
    private Boolean isBoss;
    /**
     * 是否号码隐藏，true表示隐藏，false表示不隐藏
     */
    private Boolean isHide;
    /**
     * 是否是高管
     */
    private Boolean isSenior;

    /**
     * 国家地区码
     */
    private String stateCode;
    /**
     * 职位信息
     */
    private String position;
    /**
     * 成员所属部门id列表
     */
    private List<Long> department;
    /**
     *
     * 用户所在角色列表
     */
    private List<RolesBean> roles;
    /**
     * 是否已经激活，true表示已激活，false表示未激活
     */
    private Boolean active;
    /**
     * 扩展字段，只有维护了才有
     * 手机上最多显示10个扩展属性，具体显示哪些属性，请到OA管理后台->设置->通讯录信息设置和OA管理后台->设置->手机端显示信息设置）。
     */
    private String extattr;

    /**
     * 角色
     */
    @Data
    public static class RolesBean {
        /**
         * 角色id
         */
        private Integer id;
        /**
         * 角色名称
         */
        private String name;
        /**
         * 角色组名称
         */
        private String groupName;
    }
}
