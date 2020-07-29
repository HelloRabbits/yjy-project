package com.yjy.service;

import com.yjy.entity.SysPerson;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 人员信息表 服务类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
public interface ISysPersonService extends IService<SysPerson> {

    /**
     * 根据账号id查询关联的用户信息
     *
     * @param idAccount  账号id
     * @return
     */
    SysPerson getWithIdAccount(String idAccount);
}
