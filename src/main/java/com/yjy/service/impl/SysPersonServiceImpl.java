package com.yjy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.qo.sys.SysPersonQo;
import com.yjy.bean.vo.sys.SysPersonVo;
import com.yjy.entity.SysPerson;
import com.yjy.mapper.SysPersonMapper;
import com.yjy.service.ISysPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private LoginAccountInfo loginAccountInfo;

    @Override
    public SysPerson getWithIdAccount(String idAccount) {
        return getOne(Wrappers.lambdaQuery(SysPerson.class).eq(SysPerson::getIdAccount, idAccount));
    }

    @Override
    public String saveBase(SysPerson sysPerson) {
        if (loginAccountInfo != null) {
            sysPerson.setCreateUser(loginAccountInfo.getIdAccount());
        }
        sysPerson.setCreateTime(LocalDateTime.now());
        save(sysPerson);
        return sysPerson.getIdPerson();
    }

    @Override
    public String updateBase(SysPerson sysPerson) {
        if (loginAccountInfo != null) {
            sysPerson.setUpdateUser(loginAccountInfo.getIdAccount());
        }
        sysPerson.setUpdateTime(LocalDateTime.now());
        updateById(sysPerson);
        return sysPerson.getIdPerson();
    }

    @Override
    public List<SysPersonVo> queryList(SysPersonQo sysPersonQo) {
        return list(getQuery(sysPersonQo)).stream().map(p -> BeanUtil.toBean(p, SysPersonVo.class)).collect(Collectors.toList());
    }

    @Override
    public PageInfo<SysPersonVo> queryPage(SysPersonQo qo) {
        Page<SysPerson> page = new Page<>(qo.getPageNo(), qo.getPageSize());
        return PageInfo.copy(page(page, getQuery(qo)), SysPersonVo.class);
    }

    @Override
    public SysPersonVo getDetailById(String id) {
        if (StrUtil.isEmpty(id)) {
            return null;
        }
        return BeanUtil.toBean(getById(id), SysPersonVo.class);
    }

    @Override
    public LambdaQueryWrapper<SysPerson> getQuery(SysPersonQo qo) {
        LambdaQueryWrapper<SysPerson> query = Wrappers.lambdaQuery(SysPerson.class);

        return query;
    }
}
