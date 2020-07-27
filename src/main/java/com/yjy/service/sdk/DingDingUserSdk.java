package com.yjy.service.sdk;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.yjy.bean.dto.dingding.*;
import com.yjy.common.enums.ErrorCodeEnum;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.*;
import com.yjy.common.exception.QuestionException;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import com.yjy.entity.AppInfo;
import lombok.extern.slf4j.Slf4j;


/**
 * @author zhangjl
 * @description 钉钉用户服务sdk(不做任何业务操作)
 * @date 2020-05-26 19:16
 */
@Slf4j
public class DingDingUserSdk extends AbstractDingDingSdk implements IDingDingUserSdk {


    public DingDingUserSdk(String appKey) {
        super(appKey);
    }

    @Override
    public InnerCodeUserInfoDto innerCodeUserInfo(String code, String appKey) throws QuestionException {
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(code);
        request.setHttpMethod("GET");
        return executeBase(appKey, request, InnerCodeUserInfoDto.class, "https://oapi.dingtalk.com/user/getuserinfo");
    }

    @Override
    public InnerCodeUserInfoDto innerCodeUserInfo(String code) throws QuestionException {
        return innerCodeUserInfo(code, appKey);
    }


    @Override
    public UserInfoDetailWithUserIdDto getUserInfoWithUserId(String userId, String appKey) throws QuestionException {
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod("GET");
        return execute(appKey, request, UserInfoDetailWithUserIdDto.class, "https://oapi.dingtalk.com/user/get");
    }

    @Override
    public UserInfoDetailWithUserIdDto getUserInfoWithUserId(String userId) throws QuestionException {
        return getUserInfoWithUserId(userId, appKey);
    }


    @Override
    public DeptUsersDto queryDeptUsers(String lang, Long deptId, Long offset, Long size, String order) throws QuestionException {
        offset = ObjectUtil.isEmpty(offset) ? 0L : offset;
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(deptId);
        request.setOffset(offset * size + 1);
        request.setSize(size);
        request.setLang(lang);
        request.setHttpMethod("GET");
        return executeBase(appKey, request, DeptUsersDto.class, "https://oapi.dingtalk.com/user/simplelist");
    }

    @Override
    public DeptUsersDto queryDeptUsers(Long deptId, Long offset, Long size, String order) throws QuestionException {
        return queryDeptUsers(null, deptId, offset, size, order);
    }

    @Override
    public DeptUsersDto queryDeptUsers(Long deptId) throws QuestionException {
        return queryDeptUsers(deptId, 1L, 99L, null);
    }

    @Override
    public DeptUserDetailsDto queryDeptUserDetails(String lang, Long deptId, Long offset, Long size, String order) throws QuestionException {
        offset = ObjectUtil.isEmpty(offset) ? 0L : offset;
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(deptId);
        request.setOffset(offset * size + 1);
        request.setSize(size);
        request.setOrder(order);
        request.setLang(lang);
        request.setHttpMethod("GET");
        return executeBase(appKey, request, DeptUserDetailsDto.class, "https://oapi.dingtalk.com/user/listbypage");
    }

    @Override
    public DeptUserDetailsDto queryDeptUserDetails(Long deptId, Long offset, Long size, String order) throws QuestionException {
        return queryDeptUserDetails(null, deptId, offset, size, order);
    }

    @Override
    public DeptUserDetailsDto queryDeptUserDetails(Long deptId) throws QuestionException {
        return queryDeptUserDetails(deptId, 1L, 99L, null);
    }

    @Override
    public AdminUserInfoDto queryAdminUsers(String appKey) throws QuestionException {
        OapiUserGetAdminRequest request = new OapiUserGetAdminRequest();
        request.setHttpMethod("GET");
        return executeBase(appKey, request, AdminUserInfoDto.class, "https://oapi.dingtalk.com/user/get_admin");
    }

    @Override
    public AdminUserInfoDto queryAdminUsers() throws QuestionException {
        return queryAdminUsers(appKey);
    }

    @Override
    public UserInfoWithMobileDto getUserInfoByMobile(String phone) throws QuestionException {
        OapiUserGetByMobileRequest request = new OapiUserGetByMobileRequest();
        request.setMobile(phone);
        request.setHttpMethod("GET");
        return executeBase(appKey, request, UserInfoWithMobileDto.class, "https://oapi.dingtalk.com/user/get_by_mobile");
    }

    @Override
    public UserInfoWithUnionIdDto getUserInfoByUnionId(String unionId) throws QuestionException {
        OapiUserGetUseridByUnionidRequest request = new OapiUserGetUseridByUnionidRequest();
        request.setUnionid(unionId);
        request.setHttpMethod("GET");
        return executeBase(appKey, request, UserInfoWithUnionIdDto.class, "https://oapi.dingtalk.com/user/getUseridByUnionid");
    }

    @Override
    public String getAccessToken(String appSecret) throws QuestionException {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod("GET");
        OapiGettokenResponse response;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            throw new QuestionException(ErrorCodeEnum.ERROR_10001.getCode(), "token获取失败");
        }
        log.info("获取token请求，result:{}", JSON.toJSONString(response));
        if (response == null || response.getErrcode() != 0) {
            throw new QuestionException(ErrorCodeEnum.ERROR_10001.getCode(), "token获取失败");
        }
        return response.getAccessToken();
    }

    @Override
    public String getAccessToken() throws QuestionException {
        if (StrUtil.isEmpty(appKey)) {
            throw new QuestionException(ErrorCodeEnum.ERROR_404.getCode(), "appKey  is null");
        }
        //尝试从数据库中查询
        AppInfo info = TokenService.getAppInfo(appKey);
        if (ObjectUtil.isEmpty(info)) {
            throw new QuestionException(ErrorCodeEnum.ERROR_404.getCode(), "appInfo  is null");
        }
        return getAccessToken(info.getAppSecret());
    }
}
