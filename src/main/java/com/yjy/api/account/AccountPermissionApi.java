package com.yjy.api.account;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.yjy.bean.base.AccountInfoCache;
import com.yjy.common.Constant;
import com.yjy.common.exception.JwtException;
import com.yjy.common.exception.QuestionException;
import com.yjy.entity.SysAccount;
import com.yjy.entity.SysPerson;
import com.yjy.entity.SysPersonDep;
import com.yjy.service.ISysAccountService;
import com.yjy.service.ISysPermissionService;
import com.yjy.service.ISysPersonDepService;
import com.yjy.service.ISysPersonService;
import com.yjy.service.base.RedisCacheInfoService;
import com.yjy.service.base.RedisService;
import com.yjy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


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
    private ISysPermissionService sysPermissionService;

    @Resource
    private ISysPersonService sysPersonService;

    @Resource
    private ISysPersonDepService sysPersonDepService;

    /**
     * 获取缓存的账号信息
     * 如果没有则从数据库中查询，并放入缓存
     *
     * @param account 账号
     * @return
     */
    public AccountInfoCache getCacheInfoWithAccount(String account) {
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
}
