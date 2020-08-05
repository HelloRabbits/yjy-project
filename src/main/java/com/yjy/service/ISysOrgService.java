package com.yjy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjy.bean.qo.sys.SysOrgQo;
import com.yjy.bean.vo.sys.SysOrgListVo;
import com.yjy.entity.SysOrg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 客户机构表 服务类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
public interface ISysOrgService extends IService<SysOrg> {
    /**
     * 基础的保存方法
     * 会初始化一些默认的参数
     *
     * @param sysOrg 入参
     * @return 主键
     */
    String saveBase(SysOrg sysOrg);


    /**
     * 基础更新方法
     * @param sysOrg 入参
     * @return 主键
     */
    String updateBase(SysOrg sysOrg);

    /**
     * 列表（不分页）
     *
     * @param qo 入参
     * @return 返回参数
     */
    List<SysOrgListVo> queryList(SysOrgQo qo);


    /**
     * 列表（分页）
     *
     * @param qo
     * @return
     */
    Page<SysOrg> queryPage(SysOrgQo qo);
}
