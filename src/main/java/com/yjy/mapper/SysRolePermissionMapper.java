package com.yjy.mapper;

import com.yjy.bean.vo.sys.SysRolePermissionVo;
import com.yjy.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {


    List<SysRolePermissionVo> queryListWithIdRole(@Param("idRole") String idRole);

}
