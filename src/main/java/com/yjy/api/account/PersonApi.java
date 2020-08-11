package com.yjy.api.account;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yjy.api.sys.BaseApi;
import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.bean.dto.sys.SysPersonDepSaveDto;
import com.yjy.bean.dto.sys.SysPersonSaveDto;
import com.yjy.bean.qo.sys.SysPersonQo;
import com.yjy.bean.vo.sys.SysPersonVo;
import com.yjy.common.exception.QuestionException;
import com.yjy.entity.SysAccount;
import com.yjy.entity.SysPerson;
import com.yjy.entity.SysPersonDep;
import com.yjy.service.ISysAccountService;
import com.yjy.service.ISysPersonDepService;
import com.yjy.service.ISysPersonService;
import com.yjy.service.base.RedisCacheInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author zhangjl
 * @description 用户服务
 * @date 2020-08-11 10:20
 */
@Service
public class PersonApi extends BaseApi<SysPerson, SysPersonQo, SysPersonVo, ISysPersonService> {

    @Resource
    private LoginAccountInfo loginAccountInfo;

    @Resource
    private ISysPersonDepService sysPersonDepService;

    @Resource
    private RedisCacheInfoService redisCacheInfoService;

    @Resource
    private ISysAccountService sysAccountService;

    public String save(SysPersonSaveDto dto) {
        SysPerson sysPerson = BeanUtil.toBean(dto, SysPerson.class);
        String id;
        if (StrUtil.isEmpty(sysPerson.getIdPerson())) {
            //基础信息
            id = saveBase(sysPerson);

            //科室信息
            savePersonDep(dto.getDepSaveDto(), id, true);
        } else {
            id = updateBase(sysPerson);
            savePersonDep(dto.getDepSaveDto(), id, false);
        }


        return id;
    }


    /**
     * 保存用户所属部门
     *
     * @param depSaveDto 部门信息
     * @param idPerson 用户主键
     * @param isSave 是否新增
     */
    private void savePersonDep(SysPersonDepSaveDto depSaveDto, String idPerson, boolean isSave) {
        String operateUser = loginAccountInfo == null ? null : loginAccountInfo.getIdAccount();
        if (isSave) {
            //新增
            SysPersonDep dep = new SysPersonDep();
            dep.setCreateTime(LocalDateTime.now());
            dep.setCreateUser(operateUser);
            dep.setIdDep(depSaveDto.getIdDep());
            dep.setIdOrg(depSaveDto.getIdOrg());
            dep.setIdPerson(idPerson);

            sysPersonDepService.save(dep);
        } else {
            SysPersonDep personDep = sysPersonDepService.getWithIdPerson(idPerson);
            if (!personDep.getIdOrg().equals(depSaveDto.getIdOrg())) {
                //如果变更机构需要考虑  原机构的角色是否还能继续使用
                throw new QuestionException("暂不支持,用户不能直接变更所属机构");
            }
            personDep.setIdOrg(depSaveDto.getIdOrg());
            personDep.setIdDep(depSaveDto.getIdDep());
            personDep.setUpdateTime(LocalDateTime.now());
            personDep.setUpdateUser(operateUser);

            sysPersonDepService.updateById(personDep);

            //清除账号对应的用户信息
            SysPersonVo personVo = getDetailById(idPerson);
            if (personVo != null && StrUtil.isNotEmpty(personVo.getIdAccount())) {
                SysAccount sysAccount = sysAccountService.getById(personVo.getIdAccount());
                redisCacheInfoService.removeAccountInfoCache(sysAccount.getAccount());
            }
        }
    }

}
