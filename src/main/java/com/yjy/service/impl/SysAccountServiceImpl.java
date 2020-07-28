package com.yjy.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yjy.bean.dto.account.SysUserPermissionDto;
import com.yjy.entity.SysAccount;
import com.yjy.mapper.SysAccountMapper;
import com.yjy.service.ISysAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统管理员账号表 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements ISysAccountService {

    @Resource
    private SysAccountMapper sysAccountMapper;

    @Override
    public List<SysUserPermissionDto> queryUserPermissionList(String idAccount) {
        if (StrUtil.isEmpty(idAccount)) {
            return null;
        }
        return sysAccountMapper.queryUserPermissionList(idAccount);
    }

    @Override
    public SysAccount getWithAccount(String account) {
        if (StrUtil.isEmpty(account)) {
            return null;
        }
        return getOne(Wrappers.lambdaQuery(SysAccount.class).eq(SysAccount::getAccount, account));
    }

}
