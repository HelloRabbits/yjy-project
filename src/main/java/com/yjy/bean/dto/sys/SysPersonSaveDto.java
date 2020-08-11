package com.yjy.bean.dto.sys;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangjl
 * @description 人员信息
 * @date 2020-08-11 10:35
 */
@Data
public class SysPersonSaveDto {

    private String idPerson;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 账号记录主键
     */
    private String idAccount;

    /**
     * 是否网格管理员 1是 0否
     */
    private Integer gmFlag;

    /**
     * 部门信息
     */
    private SysPersonDepSaveDto depSaveDto;

}
