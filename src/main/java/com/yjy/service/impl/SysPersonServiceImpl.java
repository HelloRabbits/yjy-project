package com.yjy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yjy.entity.SysPerson;
import com.yjy.mapper.SysPersonMapper;
import com.yjy.service.ISysPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人员信息表 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysPersonServiceImpl extends ServiceImpl<SysPersonMapper, SysPerson> implements ISysPersonService {

    @Override
    public SysPerson getWithIdAccount(String idAccount) {
        return getOne(Wrappers.lambdaQuery(SysPerson.class).eq(SysPerson::getIdAccount, idAccount));
    }
}
