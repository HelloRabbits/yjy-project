package com.yjy.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjy.bean.base.BaseVo;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.base.PageQo;

import java.util.List;

/**
 * @author zhangjl
 * @description
 * @date 2020-08-06 16:59
 */
public interface BaseService<T, K extends PageQo, V extends BaseVo> extends IService<T> {

    /**
     * 基础保存
     *
     * @param t
     * @return
     */
    String saveBase(T t);

    /**
     * 基础更新
     *
     * @param t
     * @return
     */
    String updateBase(T t);


    /**
     * 基础条件查询
     *
     * @param k
     * @return
     */
    List<V> queryList(K k);

    /**
     * 基础条件查询
     *
     * @param qo
     * @return
     */
    PageInfo<V> queryPage(K qo);

    /**
     * 查询详情
     *
     * @param id 主键 默认使用String类型，如果其他类型，内部转换
     * @return
     */
    V getDetailById(String id);

    /**
     * 简单
     * 条件参数拼接
     *
     * @param qo
     * @return
     */
    LambdaQueryWrapper<T> getQuery(K qo);

}
