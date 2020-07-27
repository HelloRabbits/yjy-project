package com.yjy.service.sdk;

import com.yjy.bean.dto.dingding.DeptDetailInfo;
import com.yjy.bean.dto.dingding.DeptListInfo;
import com.yjy.common.exception.QuestionException;

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


    /**
     * 获取部门列表
     *
     * @param parentId 上级id 不传默认1 根部门
     * @param lang  语言 默认zh_CN，未来会支持en_US
     * @param fetchChild 是否递归部门的全部子部门
     * @return
     * @throws QuestionException
     */
    DeptListInfo findDeptList(String parentId, String lang, Boolean fetchChild) throws QuestionException;


    /**
     * 获取部门列表
     *
     * @param parentId 上级id 不传默认1 根部门
     * @return
     * @throws QuestionException
     */
    DeptListInfo findDeptList(String parentId) throws QuestionException;

}
