package com.yjy.service.sdk;

import com.yjy.bean.dto.DeptDetailInfo;
import com.yjy.common.QuestionException;

/**
 * @author zhangjl
 * @description 钉钉部门sdk服务
 * @date 2020-05-29 16:28
 */
public interface IDingDingDeptSdk {

    /**
     * 获取部门详情
     *
     * @param deptId 部门id
     * @param lang   通讯录语言(默认zh_CN，未来会支持en_US)
     * @return
     */
    DeptDetailInfo getDeptDetail(String deptId, String lang) throws QuestionException;

    /**
     * 获取部门详情
     *
     * @param deptId 部门id
     * @return
     */
    DeptDetailInfo getDeptDetail(String deptId) throws QuestionException;

}
