package com.yjy.service.sdk;

import com.yjy.bean.dto.dingding.*;
import com.yjy.common.exception.QuestionException;

/**
 * @author zhangjl
 * @description 钉钉用户服务sdk接口
 * @date 2020-05-28 20:37
 */
public interface IDingDingUserSdk {

    /**
     * 企业内部应用免登录
     * 通过code换取用户的userId信息等
     *
     * @param code   code  前段通过授权获取 有效期5分钟
     * @param appKey appKey
     * @return
     */
    InnerCodeUserInfoDto innerCodeUserInfo(String code, String appKey) throws QuestionException;

    /**
     * 企业内部应用免登录
     * 通过code换取用户的userId信息等
     *
     * @param code code  前段通过授权获取 有效期5分钟
     * @return
     */
    InnerCodeUserInfoDto innerCodeUserInfo(String code) throws QuestionException;


    /**
     * 按userId获取员工信息
     *
     * @param userId 员工userId 可设置，公司内唯一
     * @param appKey 应用的key
     * @return
     * @throws QuestionException
     */
    UserInfoDetailWithUserIdDto getUserInfoWithUserId(String userId, String appKey) throws QuestionException;

    /**
     * 按userId获取员工信息
     *
     * @param userId 员工userId 可设置，公司内唯一
     * @return
     * @throws QuestionException
     */
    UserInfoDetailWithUserIdDto getUserInfoWithUserId(String userId) throws QuestionException;

    /**
     * 查询部门用户列表
     *（如果被设置为管理员则不包含在这里）
     * @param lang   通讯录语言(默认zh_CN另外支持en_US)
     * @param deptId 获取的部门id
     * @param offset 支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
     * @param size   支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100 从0开始
     * @param order  支持分页查询，部门成员的排序规则，默认不传是按自定义排序；
     *               entry_asc：代表按照进入部门的时间升序，
     *               entry_desc：代表按照进入部门的时间降序，
     * @return
     */
    DeptUsersDto queryDeptUsers(String lang, Long deptId, Long offset, Long size, String order) throws QuestionException;

    /**
     * 查询部门用户列表
     * （如果被设置为管理员则不包含在这里）
     *
     * @param deptId 获取的部门id
     * @param offset 支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
     * @param size   支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100 从0开始
     * @param order  支持分页查询，部门成员的排序规则，默认不传是按自定义排序；
     *               entry_asc：代表按照进入部门的时间升序，
     *               entry_desc：代表按照进入部门的时间降序，
     * @return
     */
    DeptUsersDto queryDeptUsers(Long deptId, Long offset, Long size, String order) throws QuestionException;

    /**
     * 查询部门用户列表
     * （如果被设置为管理员则不包含在这里）
     *
     * @param deptId 获取的部门id
     * @return
     * @throws QuestionException
     */
    DeptUsersDto queryDeptUsers(Long deptId) throws QuestionException;

    /**
     * 查询部门用户详情列表
     * （如果被设置为管理员则不包含在这里）
     *
     * @param lang   通讯录语言(默认zh_CN另外支持en_US)
     * @param deptId 获取的部门id
     * @param offset 支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
     * @param size   支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100 从0开始
     * @param order  支持分页查询，部门成员的排序规则，默认不传是按自定义排序；
     *               entry_asc：代表按照进入部门的时间升序，
     *               entry_desc：代表按照进入部门的时间降序，
     * @return
     */
    DeptUserDetailsDto queryDeptUserDetails(String lang, Long deptId, Long offset, Long size, String order) throws QuestionException;

    /**
     * 查询部门用户详情列表
     * （如果被设置为管理员则不包含在这里）
     * @param deptId 获取的部门id
     * @param offset 支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
     * @param size   支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100 从0开始
     * @param order  支持分页查询，部门成员的排序规则，默认不传是按自定义排序；
     *               entry_asc：代表按照进入部门的时间升序，
     *               entry_desc：代表按照进入部门的时间降序，
     * @return
     */
    DeptUserDetailsDto queryDeptUserDetails(Long deptId, Long offset, Long size, String order) throws QuestionException;

    /**
     * 查询部门用户详情列表
     * （如果被设置为管理员则不包含在这里）
     *
     * @param deptId 获取的部门id
     * @return
     * @throws QuestionException
     */
    DeptUserDetailsDto queryDeptUserDetails(Long deptId) throws QuestionException;

    /**
     * 获取系统管理员列表
     *
     * @param appKey appKey
     * @return
     */
    AdminUserInfoDto queryAdminUsers(String appKey) throws QuestionException;

    /**
     * 获取系统管理员列表
     *
     * @return
     */
    AdminUserInfoDto queryAdminUsers() throws QuestionException;

    /**
     * 按手机号码获取用户的id
     *
     * 钉钉中的手机号码就是账号，不能修改
     *
     * @param phone 手机号码
     * @return
     * @throws QuestionException
     */
    UserInfoWithMobileDto getUserInfoByMobile(String phone) throws QuestionException;


    /**
     * 按unionId获取用户id
     *
     * unionId从用户详情中获取，当前开发者企业账号范围内的唯一标识
     *
     * @param unionId unionId
     * @return
     * @throws QuestionException
     */
    UserInfoWithUnionIdDto getUserInfoByUnionId(String unionId) throws QuestionException;



    /**
     * 获取accessToken
     * 正常情况下access_token有效期为7200秒，有效期内重复获取返回相同结果，并自动续期
     *
     * @param appSecret secret  可以为空
     * @return
     * @throws QuestionException
     */
    String getAccessToken(String appSecret) throws QuestionException;

    /**
     * 获取token
     *
     * @return
     * @throws QuestionException
     */
    String getAccessToken() throws QuestionException;
}
