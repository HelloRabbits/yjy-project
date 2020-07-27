package com.yjy.service.sdk;

import com.alibaba.fastjson.JSON;
import com.yjy.bean.dto.dingding.DingDingBaseDto;
import com.yjy.common.enums.ErrorCodeEnum;
import com.yjy.common.exception.QuestionException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.taobao.api.ApiException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangjl
 * @description 钉钉sdk 通用基础服务
 * @date 2020-05-29 15:28
 */
@Slf4j
public abstract class AbstractDingDingSdk {

    protected String appKey;


    public AbstractDingDingSdk(String appKey) {
        this.appKey = appKey;
    }

    /**
     * 执行
     *
     * @param appKey  appkey
     * @param request 请求
     * @param url     路径
     * @return
     * @throws QuestionException
     */
    protected <K extends TaobaoResponse, H extends TaobaoRequest<K>, T extends DingDingBaseDto> T execute(String appKey, H request, Class<T> tClass, String url) throws QuestionException {
        log.info("钉钉请求:param:{}", JSON.toJSONString(request));
        DingTalkClient client = new DefaultDingTalkClient(url);
        K response;
        //写在这里方便区别token的异常
        String token = TokenService.getToken(appKey);
        try {
            response = client.execute(request, token);
        } catch (ApiException e) {
            log.error("调用钉钉接口异常,param:{},error:{}", JSON.toJSONString(request), e);
            throw new QuestionException(ErrorCodeEnum.ERROR_10000.getCode(), "调用钉钉接口失败");
        }
        // 这里有可能会有转换问题，比如时间格式等 遇到在修改吧
        T t = JSON.parseObject(JSON.toJSONString(response), tClass);
        if (t == null || t.getErrcode() != 0) {
            log.error("调用钉钉接口失败,param:{},response:{}", JSON.toJSONString(request), JSON.toJSONString(t));
            throw new QuestionException(ErrorCodeEnum.ERROR_10000.getCode(), "调用钉钉接口失败");
        }
        log.info("钉钉响应:result:{}", JSON.toJSONString(response));
        return t;
    }

    /**
     * 执行
     *
     * @param appKey  appkey
     * @param request 请求
     * @param url     路径
     * @return
     * @throws QuestionException
     */
    protected <K extends TaobaoResponse, H extends BaseTaobaoRequest<K>, T extends DingDingBaseDto> T executeBase(String appKey, H request, Class<T> tClass, String url) throws QuestionException {
        return execute(appKey, request, tClass, url);
    }

}
