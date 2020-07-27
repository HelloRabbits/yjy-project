package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjl
 * @description 发送消息返回
 * @date 2020-05-29 16:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SendMsgResultDto extends DingDingBaseDto {

    /**
     * 普通消息返回 接收者id，可能是群
     */
    private String receiver;


    /**
     * 工作通知消息返回
     */
    private String taskId;
}
