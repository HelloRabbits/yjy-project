package com.yjy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.qo.sys.SysPermissionQo;
import com.yjy.bean.vo.sys.SysPermissionVo;
import com.yjy.common.enums.StateEnum;
import com.yjy.common.exception.QuestionException;
import com.yjy.entity.SysPermission;
import com.yjy.mapper.SysPermissionMapper;
import com.yjy.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统权限池 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Resource
    private LoginAccountInfo loginAccountInfo;


    @Override
    public String saveBase(SysPermission permission) {
        if (StrUtil.isNotEmpty(permission.getCd())) {
            SysPermission cd = getWithCd(permission.getCd());
            if (null != cd ) {
                throw new QuestionException("权限编码 cd不能重复");
            }
        }
        if (ObjectUtil.isNotEmpty(loginAccountInfo)) {
            permission.setCreateUser(loginAccountInfo.getIdAccount());
        }
        permission.setCreateTime(LocalDateTime.now());
        permission.setState(StateEnum.AVAILABLE.getCode());
        save(permission);
        return permission.getIdPermission();
    }

    @Override
    public String updateBase(SysPermission permission) {
        if (ObjectUtil.isNotEmpty(loginAccountInfo)) {
            permission.setUpdateUser(loginAccountInfo.getIdAccount());
        }
        permission.setUpdateTime(LocalDateTime.now());
        updateById(permission);
        return permission.getIdPermission();
    }

    @Override
    public List<SysPermissionVo> queryList(SysPermissionQo qo) {
        return list(getQuery(qo)).stream().map(p -> BeanUtil.toBean(p, SysPermissionVo.class)).collect(Collectors.toList());
    }

    @Override
    public PageInfo<SysPermissionVo> queryPage(SysPermissionQo qo) {
        Page<SysPermission> page = new Page<>(qo.getPageNo(), qo.getPageSize());
        return PageInfo.copy(page(page, getQuery(qo)), SysPermissionVo.class);
    }

    @Override
    public SysPermissionVo getDetailById(String id) {
        if (StrUtil.isEmpty(id)) {
            return null;
        }
        return BeanUtil.toBean(getById(id), SysPermissionVo.class);
    }

    @Override
    public SysPermission getWithCd(String cd) {
        return getOne(Wrappers.lambdaQuery(SysPermission.class).eq(SysPermission::getCd, cd));
    }

    @Override
    public SysPermission getWithPermissionCd(String permissionCd) {
        return getOne(Wrappers.lambdaQuery(SysPermission.class).eq(SysPermission::getPermissionCd, permissionCd));
    }


    @Override
    public LambdaQueryWrapper<SysPermission> getQuery(SysPermissionQo qo) {
        LambdaQueryWrapper<SysPermission> query = Wrappers.lambdaQuery(SysPermission.class);
        if (StrUtil.isNotEmpty(qo.getIdParent())) {
            query.eq(SysPermission::getIdParent, qo.getIdParent());
        }
        return query;
    }

}
