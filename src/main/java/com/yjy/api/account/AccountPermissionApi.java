package com.yjy.api.account;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yjy.bean.base.AccountInfoCache;
import com.yjy.bean.base.PermissionCache;
import com.yjy.bean.dto.account.AccountRoleSaveDto;
import com.yjy.bean.dto.account.SysUserPermissionDto;
import com.yjy.bean.dto.sys.SysUserPermissionMenuDto;
import com.yjy.bean.vo.account.PermissionMenuVo;
import com.yjy.common.Constant;
import com.yjy.common.enums.ErrorCodeEnum;
import com.yjy.common.exception.QuestionException;
import com.yjy.entity.SysAccount;
import com.yjy.entity.SysAccountRole;
import com.yjy.entity.SysPerson;
import com.yjy.entity.SysPersonDep;
import com.yjy.service.ISysAccountRoleService;
import com.yjy.service.ISysAccountService;
import com.yjy.service.ISysPersonDepService;
import com.yjy.service.ISysPersonService;
import com.yjy.service.base.RedisCacheInfoService;
import com.yjy.utils.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author zhangjl
 * @description 账号服务
 * @date 2020-07-28 15:57
 */
@Component
public class AccountPermissionApi {

    @Resource
    private RedisCacheInfoService cacheInfoService;

    @Resource
    private ISysAccountService sysAccountService;

    @Resource
    private ISysPersonService sysPersonService;

    @Resource
    private ISysPersonDepService sysPersonDepService;

    @Resource
    private ISysAccountRoleService sysAccountRoleService;

    /**
     * 获取缓存的账号信息
     * 如果没有则从数据库中查询，并放入缓存
     *
     * @param account 账号
     * @return
     */
    public AccountInfoCache getAccountInfoWithAccount(String account) {
        //优先从redis中获取
        AccountInfoCache cache = cacheInfoService.getAccountInfoCache(account);
        if (null == cache) {
            //账号信息
            SysAccount sysAccount = sysAccountService.getWithAccount(account);
            if (ObjectUtil.isEmpty(sysAccount)) {
                throw new QuestionException("账号信息异常");
            }
            cache = BeanUtil.toBean(sysAccount, AccountInfoCache.class);
            //用户信息
            SysPerson person = sysPersonService.getWithIdAccount(sysAccount.getIdAccount());
            if (null != person) {
                cache.setIdPerson(person.getIdPerson());
                //查询科室部门信息
                SysPersonDep personDep = sysPersonDepService.getWithIdPerson(person.getIdPerson());
                cache.setIdDep(personDep.getIdDep());
                cache.setIdOrg(personDep.getIdOrg());
            }
            cacheInfoService.addAccountInfoCache(cache);
        }
        return cache;
    }

    /**
     * 查询用于权限信息
     * 优先从缓存中获取
     *
     * @param account 账号
     * @return PermissionCache
     */
    public PermissionCache getPermissionsWithAccount(String account) {
        PermissionCache cache = cacheInfoService.getPermissionCache(account);
        cache.setAccount(account);
        if (ObjectUtil.isEmpty(cache)) {
            cache = new PermissionCache();
            //查询角色和权限
            List<SysUserPermissionDto> permissionDtos = sysAccountService.queryUserPermissionList(account);
            if (CollUtil.isEmpty(permissionDtos)) {
                return cache;
            }
            //初始化角色
            cache.setRoles(permissionDtos.stream().map(SysUserPermissionDto::getRoleCd).collect(Collectors.toSet()));
            //初始化权限
            cache.setPermissions(permissionDtos.stream().map(SysUserPermissionDto::getPermissionCd).collect(Collectors.toSet()));
            cacheInfoService.addPermissionCache(cache);
        }
        return cache;
    }

    /**
     * 保存账号和角色的关联关心
     *
     * @param dto
     */
    public void saveAccountRole(AccountRoleSaveDto dto) {
        if (StrUtil.isEmpty(dto.getIdAccount())) {
            throw new QuestionException(ErrorCodeEnum.ERROR_11001.getCode(), "idAccount不能为空");
        }
        //先全部删除
        sysAccountRoleService.remove(Wrappers.lambdaQuery(SysAccountRole.class).eq(SysAccountRole::getIdAccount, dto.getIdAccount()));
        //重新添加
        dto.getIdRoles().forEach(role -> {
            SysAccountRole accountRole = new SysAccountRole();
            accountRole.setCreateTime(LocalDateTime.now());
            accountRole.setIdAccount(dto.getIdAccount());
            accountRole.setIdRole(role);
            sysAccountRoleService.save(accountRole);
        });
        SysAccount account = sysAccountService.getById(dto.getIdAccount());
        //所有账号缓存的权限
        RedisUtils.getService().delKey(Constant.REDIS_PERMISSION_INFO + account.getAccount());
    }

    /**
     * 按账号id查询菜单权限
     *
     * @param idAccount 账号id
     * @return List<PermissionMenuVo>
     */
    public List<PermissionMenuVo> queryMenuList(String idAccount) {
        List<SysUserPermissionMenuDto> menuDtos = sysAccountService.queryUserPermissionMenuList(idAccount);
        List<PermissionMenuVo> menuVos = menuDtos.stream().map(m -> BeanUtil.toBean(m, PermissionMenuVo.class)).collect(Collectors.toList());
        //子菜单
        List<PermissionMenuVo> sonMenus = menuVos.stream().filter(m -> StrUtil.isNotEmpty(m.getIdParent())).collect(Collectors.toList());
        //获取顶级菜单
        return menuVos.stream()
                //顶级菜单
                .filter(m -> "0".equals(m.getIdParent()))
                .peek(m -> m.setSubMenu(getChild(m.getIdPermission(), sonMenus)))
                .collect(Collectors.toList());
    }


    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return List<PermissionMenuVo>
     */
    private List<PermissionMenuVo> getChild(String id, List<PermissionMenuVo> rootMenu) {
        // 子菜单
        List<PermissionMenuVo> childList = new ArrayList<>();
        for (PermissionMenuVo menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StrUtil.isNotEmpty(menu.getIdParent())) {
                if (menu.getIdParent().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (PermissionMenuVo menu : childList) {// 没有url子菜单还有子菜单
            if (StrUtil.isNotEmpty(menu.getUrl())) {
                // 递归
                menu.setSubMenu(getChild(menu.getIdPermission(), rootMenu));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
