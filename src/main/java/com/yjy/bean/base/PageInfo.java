package com.yjy.bean.base;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjl
 * @description 分页类型数据返回
 * @date 2020-08-05 14:01
 */
@Data
public class PageInfo<T> {

    private List<T> list;
    private long total = 0L;
    private long pageSize = 20L;
    private long pageNo = 1L;


    public static <K> PageInfo<K> copy(Page<?> page, Class<K> k) {
        PageInfo<K> pageInfo = new PageInfo<>();
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setTotal(page.getTotal());
        List<K> ks = page.getRecords().stream().map(list -> BeanUtil.toBean(list, k)).collect(Collectors.toList());
        pageInfo.setList(ks);
        return pageInfo;
    }

}
