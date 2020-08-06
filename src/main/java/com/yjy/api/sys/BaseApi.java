package com.yjy.api.sys;


import com.yjy.bean.base.BaseVo;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.base.PageQo;
import com.yjy.service.BaseService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangjl
 * @description
 * @date 2020-08-06 17:14
 */
public class BaseApi<T, K extends PageQo, V extends BaseVo, E extends BaseService<T, K, V>> {


    @Resource
    E baseService;


    /**
     * 基础保存
     *
     * @param t
     * @return
     */
    public String saveBase(T t) {
        return baseService.saveBase(t);
    }

    /**
     * 基础更新
     *
     * @param t
     * @return
     */
    public String updateBase(T t) {
        return baseService.updateBase(t);
    }


    /**
     * 基础条件查询
     *
     * @param qo
     * @return
     */
    public List<V> queryList(K qo) {
        return baseService.queryList(qo);
    }

    /**
     * 基础条件查询
     *
     * @param k
     * @return
     */
    public PageInfo<V> queryPage(K k) {
        return baseService.queryPage(k);
    }
}
