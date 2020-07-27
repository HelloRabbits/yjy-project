package com.yjy.service.sdk;

import com.yjy.bean.dto.SendMsgResultDto;
import com.yjy.bean.qo.SendWorkNoticeQo;
import com.yjy.common.QuestionException;
import com.dingtalk.api.request.OapiMessageSendToConversationRequest;

/**
 * @author zhangjl
 * @description 钉钉发送消息服务sdk
 * @date 2020-05-29 16:52
 */
public interface IDingDingSendMsgSdk {

    /**
     * 发送普通消息
     * 群聊或单聊消息
     *
     * @param sender 发送人userId
     * @param cid    群会话或者个人会话的id，通过JSAPI接口唤起联系人界面选择会话获取会话cid,有效期24小时
     * @param msg    消息内容 json格式 通过{{@link OapiMessageSendToConversationRequest}}构建
     * @return
     */
    SendMsgResultDto baseMsgSend(String sender, String cid, String msg) throws QuestionException;


    /**
     * 发送工作通知消息
     *
     * @param noticeQo 参数
     * @return
     */
    SendMsgResultDto workNoticeSend(SendWorkNoticeQo noticeQo) throws QuestionException;


}
