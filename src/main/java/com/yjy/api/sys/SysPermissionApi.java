package com.yjy.api.sys;

import com.yjy.bean.qo.sys.SysPermissionQo;
import com.yjy.bean.vo.sys.SysPermissionVo;
import com.yjy.entity.SysPermission;
import com.yjy.service.ISysPermissionService;
import org.springframework.stereotype.Service;


/**
 * @author zhangjl
 * @description 权限服务
 * @date 2020-08-06 16:47
 */
@Service
public class SysPermissionApi extends BaseApi<SysPermission, SysPermissionQo, SysPermissionVo, ISysPermissionService> {


}
