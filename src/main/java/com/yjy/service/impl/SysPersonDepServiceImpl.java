package com.yjy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yjy.entity.SysPersonDep;
import com.yjy.mapper.SysPersonDepMapper;
import com.yjy.service.ISysPersonDepService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门和成员之间的关系 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysPersonDepServiceImpl extends ServiceImpl<SysPersonDepMapper, SysPersonDep> implements ISysPersonDepService {

    @Override
    public SysPersonDep getWithIdPerson(String idPerson) {
        return getOne(Wrappers.lambdaQuery(SysPersonDep.class).eq(SysPersonDep::getIdPerson, idPerson));
    }
}
