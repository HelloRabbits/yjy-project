package com.yjy.service.sdk;

import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.yjy.bean.dto.dingding.DeptDetailInfo;
import com.yjy.bean.dto.dingding.DeptListInfo;
import com.yjy.common.exception.QuestionException;
import com.dingtalk.api.request.OapiDepartmentGetRequest;

/**
 * @author zhangjl
 * @description 钉钉部门Sdk服务
 * @date 2020-05-29 16:27
 */
public class DingDingDeptSdk extends AbstractDingDingSdk implements IDingDingDeptSdk {

    public DingDingDeptSdk(String appKey) {
        super(appKey);
    }

    @Override
    public DeptDetailInfo getDeptDetail(String deptId, String lang) throws QuestionException {
        OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
        request.setId(deptId);
        request.setLang(lang);
        request.setHttpMethod("GET");
        return executeBase(appKey, request, DeptDetailInfo.class, "https://oapi.dingtalk.com/department/get");
    }


    @Override
    public DeptDetailInfo getDeptDetail(String deptId) throws QuestionException {
        return getDeptDetail(deptId, null);
    }

    @Override
    public DeptListInfo findDeptList(String parentId, String lang, Boolean fetchChild) throws QuestionException {
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(parentId);
        //默认不遍历
        request.setFetchChild(fetchChild == null ? Boolean.FALSE : fetchChild);
        request.setHttpMethod("GET");
        return executeBase(appKey, request, DeptListInfo.class, "https://oapi.dingtalk.com/department/list");
    }

    @Override
    public DeptListInfo findDeptList(String parentId) throws QuestionException {
        return findDeptList(parentId, null, Boolean.TRUE);
    }
}
