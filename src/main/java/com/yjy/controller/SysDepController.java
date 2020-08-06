package com.yjy.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yjy.api.sys.SysDepApi;
import com.yjy.bean.base.PageInfo;
import com.yjy.bean.dto.sys.SysDepSaveDto;
import com.yjy.bean.qo.sys.SysDepQo;
import com.yjy.bean.vo.sys.SysDepVo;
import com.yjy.common.Constant;
import com.yjy.common.Response;
import com.yjy.entity.SysDep;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangjl
 * @description 科室服务
 * @date 2020-08-06 15:31
 */
@RequestMapping(Constant.URL_PRE_API + "/dep")
@RestController
public class SysDepController {

    @Resource
    private SysDepApi sysDepApi;


    /**
     * 基础保存
     * 会设置部分默认值
     *
     * @param dep 入参
     * @return 主键
     */
    @PutMapping("save")
    public Response<String> save(@RequestBody SysDepSaveDto dep) {
        sysDepApi.saveBase(BeanUtil.toBean(dep, SysDep.class));
        return Response.success();
    }

    /**
     * 基础列表条件查询（不分页）
     *
     * @param qo 入参
     * @return  List<SysDepListVo>
     */
    @PostMapping("queryList")
    public Response<List<SysDepVo>> queryList(@RequestBody SysDepQo qo) {
        return Response.success(sysDepApi.queryList(qo));
    }

    /**
     * 基础列表条件查询（分页）
     *
     * @param qo 入参
     * @return  Page<SysDep>
     */
    @PostMapping("queryPage")
    public Response<PageInfo<SysDepVo>> queryPage(@RequestBody SysDepQo qo) {
        return Response.success(sysDepApi.queryPage(qo));
    }

}
