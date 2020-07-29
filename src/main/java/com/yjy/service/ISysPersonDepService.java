package com.yjy.service;

import com.yjy.entity.SysPersonDep;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门和成员之间的关系 服务类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
public interface ISysPersonDepService extends IService<SysPersonDep> {

    /**
     * 查询人员所属科室部门信息
     *
     * @param idPerson 人员主键
     * @return
     */
    SysPersonDep getWithIdPerson(String idPerson);
}
