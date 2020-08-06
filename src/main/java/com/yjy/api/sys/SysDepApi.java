package com.yjy.api.sys;


import com.yjy.bean.qo.sys.SysDepQo;

import com.yjy.bean.vo.sys.SysDepVo;
import com.yjy.entity.SysDep;

import com.yjy.service.ISysDepService;
import org.springframework.stereotype.Service;


/**
 * @author zhangjl
 * @description 科室服务
 * @date 2020-08-06 13:40
 */
@Service
public class SysDepApi extends BaseApi<SysDep, SysDepQo, SysDepVo, ISysDepService> {


}
