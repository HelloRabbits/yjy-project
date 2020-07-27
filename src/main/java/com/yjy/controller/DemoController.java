package com.yjy.controller;

import com.yjy.bean.qo.dingidng.SendWorkNoticeQo;
import com.yjy.common.QuestionException;
import com.yjy.common.ResultResponse;
import com.yjy.service.sdk.DingDingSdk;
import com.yjy.service.sdk.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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


    @ApiOperation(value = "获取token")
    @GetMapping("getToken")
    @ApiImplicitParam(name = "appKey", value = "appKey", dataType = "string", paramType = "query")
    public ResultResponse<String> getAccessToken(String appKey) throws QuestionException {
        return ResultResponse.success(TokenService.getToken(appKey));
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
}
