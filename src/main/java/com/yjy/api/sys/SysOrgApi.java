package com.yjy.api.sys;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.dto.sys.SysOrgSaveDto;
import com.yjy.bean.qo.sys.SysOrgQo;
import com.yjy.bean.vo.sys.SysOrgListVo;
import com.yjy.entity.SysOrg;
import com.yjy.service.ISysOrgService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangjl
 * @description
 * @date 2020-07-28 15:58
 */
@Component
public class SysOrgApi {

    @Resource
    private ISysOrgService sysOrgService;


    //新增or修改
    public String save(SysOrgSaveDto dto) {
        SysOrg sysOrg = BeanUtil.toBean(dto, SysOrg.class);
        if (StrUtil.isEmpty(dto.getIdOrg())) {
            sysOrg = BeanUtil.toBean(dto, SysOrg.class);
            sysOrgService.saveBase(sysOrg);
        } else {
            sysOrgService.updateBase(sysOrg);
        }
        return sysOrg.getIdOrg();
    }

    /**
     * 列表 不分页
     *
     * @param qo 入参
     * @return 返回
     */
    public List<SysOrgListVo> queryList(SysOrgQo qo) {
        return sysOrgService.queryList(qo);
    }

    /**
     * 列表分页
     *
     * @param qo 入参
     * @return 返回参数
     */
    public PageInfo<SysOrgListVo> queryPage(SysOrgQo qo) {
        Page<SysOrg> queryPage = sysOrgService.queryPage(qo);
        return PageInfo.copy(queryPage, SysOrgListVo.class);
    }

}
