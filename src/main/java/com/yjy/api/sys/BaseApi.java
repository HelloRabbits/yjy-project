package com.yjy.api.sys;


import com.yjy.bean.base.BaseVo;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.base.PageQo;
import com.yjy.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhangjl
 * @description 基础操作服务
 * @date 2020-08-06 17:14
 */
public class BaseApi<T, K extends PageQo, V extends BaseVo, E extends BaseService<T, K, V>> {

    /**
     * 这里必须要用 Autowired
     * 不过会提示错误有点难受
     */
    @Autowired
    E baseService;


    /**
     * 基础保存
     *
     * @param t 内容
     * @return 主键
     */
    public String saveBase(T t) {
        return baseService.saveBase(t);
    }

    /**
     * 基础更新
     *
     * @param t 内容
     * @return 主键
     */
    public String updateBase(T t) {
        return baseService.updateBase(t);
    }


    /**
     * 基础条件查询
     *
     * @param qo 查询条件
     * @return List<V>
     */
    public List<V> queryList(K qo) {
        return baseService.queryList(qo);
    }

    /**
     * 基础条件查询
     *
     * @param k 查询条件
     * @return PageInfo<V>
     */
    public PageInfo<V> queryPage(K k) {
        return baseService.queryPage(k);
    }

    /**
     * 查询详情
     *
     * @param id 主键 默认使用String类型，如果其他类型，内部转换
     * @return V
     */
    public V getDetailById(String id) {
        return baseService.getDetailById(id);
    }
}
