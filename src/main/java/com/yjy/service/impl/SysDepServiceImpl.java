package com.yjy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.qo.sys.SysDepQo;
import com.yjy.bean.vo.sys.SysDepVo;
import com.yjy.common.enums.StateEnum;
import com.yjy.entity.SysDep;
import com.yjy.mapper.SysDepMapper;
import com.yjy.service.ISysDepService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统部门表 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysDepServiceImpl extends ServiceImpl<SysDepMapper, SysDep> implements ISysDepService {

    @Resource
    private LoginAccountInfo loginAccountInfo;

    @Override
    public String saveBase(SysDep dep) {
        dep.setCreateTime(LocalDateTime.now());
        if (loginAccountInfo != null) {
            dep.setCreateUser(loginAccountInfo.getIdAccount());
        }
        dep.setState(StateEnum.AVAILABLE.getCode());
        save(dep);
        return dep.getIdDep();
    }

    @Override
    public String updateBase(SysDep dep) {
        if (loginAccountInfo != null) {
            dep.setUpdateUser(loginAccountInfo.getIdAccount());
        }
        dep.setUpdateTime(LocalDateTime.now());
        updateById(dep);
        return dep.getIdDep();
    }

    @Override
    public List<SysDepVo> queryList(SysDepQo qo) {
        return list(getQuery(qo)).stream().map(dep -> BeanUtil.toBean(dep, SysDepVo.class)).collect(Collectors.toList());
    }

    @Override
    public PageInfo<SysDepVo> queryPage(SysDepQo qo) {
        Page<SysDep> page = new Page<>(qo.getPageNo(), qo.getPageSize());
        return PageInfo.copy(page(page, getQuery(qo)), SysDepVo.class);
    }


    public LambdaQueryWrapper<SysDep> getQuery(SysDepQo qo) {
        LambdaQueryWrapper<SysDep> queryWrapper = Wrappers.lambdaQuery(SysDep.class);
        if (StrUtil.isNotEmpty(qo.getIdParent())) {
            queryWrapper.eq(SysDep::getIdParent, qo.getIdParent());
        }
        return queryWrapper;
    }
}
