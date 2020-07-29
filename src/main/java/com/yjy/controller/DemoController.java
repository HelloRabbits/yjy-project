package com.yjy.controller;

import com.alibaba.fastjson.JSON;
import com.yjy.api.dingding.DingDingInitUserApi;
import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.bean.dto.dingding.DeptListInfo;
import com.yjy.bean.qo.dingidng.SendWorkNoticeQo;
import com.yjy.common.Constant;
import com.yjy.common.exception.QuestionException;
import com.yjy.common.Response;
import com.yjy.service.base.RedisService;
import com.yjy.service.sdk.DingDingSdk;
import com.yjy.service.sdk.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 * @author zhangjl
 * @description 测试
 * @date 2020-05-26 20:37
 */
@Slf4j
@Api(tags = "测试")
@RequestMapping("demo/")
@RestController
public class DemoController {

    @Resource
    private DingDingInitUserApi dingDingInitUserApi;

    @Resource
    private RedisService redisService;

    @Resource
    private LoginAccountInfo loginAccountInfo;

    @GetMapping("redis/addStr")
    public void addStr(String key, String value){
        redisService.strAddExpire(key, value, 1, TimeUnit.MINUTES);
    }

    @RequiresPermissions(value = "user")
    @GetMapping("redis/getObj")
    public void getObj(String key){
        Object attribute = SecurityUtils.getSubject().getSession().getAttribute(Constant.SESSION_USER_INFO);
        System.out.println(JSON.toJSONString(attribute));
        System.out.println(JSON.toJSONString(loginAccountInfo));
        throw new QuestionException("yichang");
    }

    @GetMapping("redis/addObj")
    public void addObj(String key){
        HashMap<String, Object> hashMap = new HashMap<>(10);
        hashMap.put("1", "2");
        redisService.objAddExpire(key, hashMap, 1, TimeUnit.MINUTES);
    }

    @ApiOperation(value = "获取token")
    @GetMapping("getToken")
    @ApiImplicitParam(name = "appKey", value = "appKey", dataType = "string", paramType = "query")
    public Response<String> getAccessToken(String appKey) throws QuestionException {

        return Response.success(TokenService.getToken(appKey));
    }

    @ApiOperation(value = "发送消息")
    @GetMapping("sendMsg")
    @ApiImplicitParam(name = "appKey", value = "appKey", dataType = "string", paramType = "query")
    public void sendMsg(String appKey) throws QuestionException {
        SendWorkNoticeQo noticeQo = new SendWorkNoticeQo(appKey)
                .build()
                .buildTxtMsg("你好：www.baidu.com")
                .setToAllUser(true);
        DingDingSdk.getDingDingSendMsgSdk(appKey).workNoticeSend(noticeQo);
    }

    @ApiOperation(value = "获取钉钉部门列表")
    @GetMapping("queryDeptList")
    @ApiImplicitParam(name = "appKey", value = "appKey", dataType = "string", paramType = "query")
    public void queryDeptList(String appKey) throws QuestionException {
        DeptListInfo deptList = DingDingSdk.getDingDingDeptSdk(appKey).findDeptList("", null, Boolean.TRUE);
        log.info("deptList:{}", JSON.toJSONString(deptList));
    }

    @ApiOperation(value = "初始化人员信息")
    @GetMapping("initUsers")
    public void initUsers() throws QuestionException {
        dingDingInitUserApi.initUsers("");
    }

    @ApiOperation(value = "获取token")
    @GetMapping("userSdkToken")
    public String userSdkToken(String appKey) throws QuestionException {
        return DingDingSdk.getDingDingUserSdk(appKey).getAccessToken();
    }

    public static void main(String[] args) {

    }
}
