package com.yjy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.qo.sys.SysRoleQo;
import com.yjy.bean.vo.sys.SysRoleVo;
import com.yjy.common.enums.StateEnum;
import com.yjy.entity.SysRole;
import com.yjy.mapper.SysRoleMapper;
import com.yjy.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private LoginAccountInfo loginAccountInfo;


    @Override
    public String saveBase(SysRole role) {
        if (ObjectUtil.isNotEmpty(loginAccountInfo)) {
            role.setCreateUser(loginAccountInfo.getIdAccount());
        }
        role.setCreateTime(LocalDateTime.now());
        role.setState(StateEnum.AVAILABLE.getCode());
        save(role);
        return role.getIdRole();
    }

    @Override
    public String updateBase(SysRole role) {
        if (ObjectUtil.isNotEmpty(loginAccountInfo)) {
            role.setUpdateUser(loginAccountInfo.getIdAccount());
        }
        role.setUpdateTime(LocalDateTime.now());
        updateById(role);
        return role.getIdRole();
    }

    @Override
    public List<SysRoleVo> queryList(SysRoleQo qo) {
        return list(getQuery(qo)).stream().map(role -> BeanUtil.toBean(role, SysRoleVo.class)).collect(Collectors.toList());
    }

    @Override
    public PageInfo<SysRoleVo> queryPage(SysRoleQo qo) {
        Page<SysRole> page = new Page<>(qo.getPageNo(), qo.getPageSize());
        return PageInfo.copy(page(page, getQuery(qo)), SysRoleVo.class);
    }


    public LambdaQueryWrapper<SysRole> getQuery(SysRoleQo qo) {
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery(SysRole.class);
        if (StrUtil.isNotEmpty(qo.getIdOrg())) {
            queryWrapper.eq(SysRole::getIdOrg, qo.getIdOrg());
        }
        return queryWrapper;
    }

}
