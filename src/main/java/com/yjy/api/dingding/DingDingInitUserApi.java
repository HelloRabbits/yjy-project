package com.yjy.api.dingding;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.yjy.bean.dto.dingding.AdminUserInfoDto;
import com.yjy.bean.dto.dingding.DeptListInfo;
import com.yjy.bean.dto.dingding.DeptUserDetailsDto;
import com.yjy.bean.dto.dingding.UserInfoDetailWithUserIdDto;
import com.yjy.common.exception.QuestionException;
import com.yjy.config.DingDingProperties;
import com.yjy.service.sdk.DingDingSdk;
import com.yjy.service.sdk.IDingDingUserSdk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhangjl
 * @description 钉钉初始化全部的用户信息服务
 * @date 2020-07-27 10:28
 */
@Slf4j
@Component
public class DingDingInitUserApi {

    @Resource
    private DingDingProperties dingDingProperties;

    /**
     * 从钉钉拉取全部用户信息 初始化到本地
     * 这里需要注意 用户被指定为管理员后 从部门用户列表接口中获取不到
     * 需要从管理员接口中查询
     *
     * @param appKey
     * @throws QuestionException
     */
    public void initUsers(String appKey) throws QuestionException {
        appKey = StrUtil.isEmpty(appKey) ? dingDingProperties.getAppKey() : appKey;
        //拉取钉钉的部门列表
        DeptListInfo deptList = DingDingSdk.getDingDingDeptSdk(appKey).findDeptList("");
        log.info("deptList:{}", JSON.toJSONString(deptList));

        IDingDingUserSdk dingUserSdk = DingDingSdk.getDingDingUserSdk(appKey);
        //普通部门成员
        deptList.getDepartment().forEach(dept -> {
            //遍历部门下的人员信息详情，分页查询每次99条
            boolean hasMore;
            long size = 2;
            long offset = 0;
            do {
                DeptUserDetailsDto detailsDto;
                try {
                    detailsDto = dingUserSdk.queryDeptUserDetails(dept.getId(), offset, size, "");
                    hasMore = detailsDto.getHasMore() == null ? false : detailsDto.getHasMore();
                    //如果不止一页则自动翻页
                    if (hasMore) {
                        offset++;
                    }
                } catch (QuestionException e) {
                    //异常的事就退出此次循环
                    log.error("获取部门用户详情接口失败:{},deptInfo:{}", e.getMsg(), JSON.toJSONString(dept));
                    break;
                }
                log.info("deptUserInfo:{}, offset:{}", JSON.toJSONString(detailsDto), offset);
            } while (hasMore);
        });
        //管理员
        //设置为管理员 则从部门列表查询不到
        AdminUserInfoDto adminUsers = dingUserSdk.queryAdminUsers();
        adminUsers.getAdminList().forEach(admin->{
            UserInfoDetailWithUserIdDto adminUserInfo;
            try {
                adminUserInfo = dingUserSdk.getUserInfoWithUserId(admin.getUserid());
            } catch (QuestionException e) {
                log.error("获取管理员详情失败:{}", e.getMsg());
                return;
            }
            log.info("adminUserInfo:{}", JSON.toJSONString(adminUserInfo));
        });

        // TODO: 2020/7/27 初始化数据到数据库  钉钉的人员与部门是1对多的，考虑重复的人员。手机号是唯一的。
    }
}
