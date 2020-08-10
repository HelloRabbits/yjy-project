package com.yjy.api.sys;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yjy.bean.qo.sys.SysPermissionQo;
import com.yjy.bean.dto.sys.SysRolePermissionSaveDto;
import com.yjy.bean.vo.sys.SysPermissionVo;
import com.yjy.bean.vo.sys.SysRolePermissionVo;
import com.yjy.common.Constant;
import com.yjy.entity.SysPermission;
import com.yjy.entity.SysRolePermission;
import com.yjy.service.ISysPermissionService;
import com.yjy.service.ISysRolePermissionService;
import com.yjy.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhangjl
 * @description 权限服务
 * @date 2020-08-06 16:47
 */
@Service
public class SysPermissionApi extends BaseApi<SysPermission, SysPermissionQo, SysPermissionVo, ISysPermissionService> {


    @Resource
    private ISysRolePermissionService sysRolePermissionService;

    /**
     * 维护权限和角色的关系
     *
     * @param saveQo 入参
     */
    public void saveRolePermission(SysRolePermissionSaveDto saveQo) {
        sysRolePermissionService.remove(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getIdRole, saveQo.getIdRole()));
        if (CollUtil.isNotEmpty(saveQo.getIdPermissions())) {
            saveQo.getIdPermissions().forEach(s -> {
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setIdRole(saveQo.getIdRole());
                rolePermission.setIdPermission(s);
                rolePermission.setCreateTime(LocalDateTime.now());
                sysRolePermissionService.save(rolePermission);
            });
            //清除所有权限缓存
            RedisUtils.removeALlKey(Constant.REDIS_PERMISSION_INFO);
        }
    }

    /**
     * 根据角色id查询 拥有的权限
     *
     * @param idRole 角色id
     * @return List<SysRolePermissionVo>
     */
    public List<SysRolePermissionVo> queryRolePermission(String idRole) {
        return sysRolePermissionService.queryListWithIdRole(idRole);
    }

}
