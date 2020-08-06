package com.yjy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.qo.sys.SysOrgQo;
import com.yjy.bean.vo.sys.SysOrgVo;
import com.yjy.common.enums.StateEnum;
import com.yjy.entity.SysOrg;
import com.yjy.mapper.SysOrgMapper;
import com.yjy.service.ISysOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户机构表 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements ISysOrgService {

    @Resource
    private LoginAccountInfo loginAccountInfo;

    @Override
    public String saveBase(SysOrg sysOrg) {
        sysOrg.setCreateTime(LocalDateTime.now());
        sysOrg.setState(StateEnum.AVAILABLE.getCode());
        if (loginAccountInfo != null) {
            sysOrg.setCreateUser(loginAccountInfo.getIdAccount());
        }
        save(sysOrg);
        return sysOrg.getIdOrg();
    }

    @Override
    public String updateBase(SysOrg sysOrg) {
        sysOrg.setUpdateTime(LocalDateTime.now());
        if (loginAccountInfo != null) {
            sysOrg.setUpdateUser(loginAccountInfo.getIdAccount());
        }
        //这个更新为空的值不会覆盖
        updateById(sysOrg);
        return sysOrg.getIdOrg();
    }

    /**
     * 条件查询（不分页）
     *
     * @param qo 入参
     * @return 返回参数
     */
    public List<SysOrgVo> queryList(SysOrgQo qo) {
        List<SysOrg> list = list(getQuery(qo));
        return list.stream().map(org -> BeanUtil.toBean(org, SysOrgVo.class)).collect(Collectors.toList());
    }

    /**
     * 分页请求
     *
     * @param qo 入参
     * @return 出参
     */
    @Override
    public PageInfo<SysOrgVo> queryPage(SysOrgQo qo) {
        Page<SysOrg> page = new Page<>(qo.getPageNo(), qo.getPageSize());
        return PageInfo.copy(page(page, getQuery(qo)), SysOrgVo.class);
    }


    /**
     * 拼接参数
     *
     * @param qo 入参
     * @return 出参
     */
    public LambdaQueryWrapper<SysOrg> getQuery(SysOrgQo qo) {
        LambdaQueryWrapper<SysOrg> queryWrapper = Wrappers.lambdaQuery(SysOrg.class);
        if (StrUtil.isNotEmpty(qo.getOrgName())) {
            queryWrapper.like(SysOrg::getOrgName, qo.getOrgName());
        }
        if (StrUtil.isNotEmpty(qo.getIdParent())) {
            queryWrapper.eq(SysOrg::getIdParent, qo.getIdParent());
        }
        return queryWrapper;
    }

}
