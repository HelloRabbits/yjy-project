package com.yjy.service.sdk;

import cn.hutool.core.util.StrUtil;
import com.yjy.bean.dto.dingding.SendMsgResultDto;
import com.yjy.bean.qo.dingidng.SendWorkNoticeQo;
import com.yjy.common.enums.ErrorCodeEnum;
import com.yjy.common.exception.QuestionException;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiMessageSendToConversationRequest;

/**
 * @author zhangjl
 * @description 钉钉发送消息服务Sdk
 * @date 2020-05-29 17:01
 */
public class DingDingSendMsgSdk extends AbstractDingDingSdk implements IDingDingSendMsgSdk {

    public DingDingSendMsgSdk(String appKey) {
        super(appKey);
    }

    @Override
    public SendMsgResultDto baseMsgSend(String sender, String cid, String msg) throws QuestionException {
        OapiMessageSendToConversationRequest req = new OapiMessageSendToConversationRequest();
        req.setSender(sender);
        req.setCid(cid);
        req.setMsg(msg);
        return executeBase(appKey, req, SendMsgResultDto.class, "https://oapi.dingtalk.com/message/send_to_conversation");
    }

    @Override
    public SendMsgResultDto workNoticeSend(SendWorkNoticeQo noticeQo) throws QuestionException {
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(noticeQo.getUserIdList());
        request.setAgentId(noticeQo.getAgentId());
        request.setToAllUser(noticeQo.getToAllUser());
        request.setDeptIdList(noticeQo.getDeptIdList());
        if (StrUtil.isEmpty(noticeQo.getMsg())) {
            throw new QuestionException(ErrorCodeEnum.ERROR_500.getCode(), "参数msg不能为空，请查看消息是否构建完成");
        }
        request.setMsg(noticeQo.getMsg());

        return executeBase(appKey, request, SendMsgResultDto.class, "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
    }

}
