package com.yjy.service.impl;

import cn.hutool.core.util.StrUtil;
import com.yjy.bean.vo.sys.SysRolePermissionVo;
import com.yjy.entity.SysRolePermission;
import com.yjy.mapper.SysRolePermissionMapper;
import com.yjy.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;


    @Override
    public List<SysRolePermissionVo> queryListWithIdRole(String idRole) {
        if (StrUtil.isEmpty(idRole)) {
            return null;
        }
        return sysRolePermissionMapper.queryListWithIdRole(idRole);
    }
}
