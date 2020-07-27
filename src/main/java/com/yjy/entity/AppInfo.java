package com.yjy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangjl
 * @since 2020-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 企业id
     */
    private String corpId;

    /**
     * 企业名称
     */
    private String corpName;

    /**
     * 企业内应用id
     */
    private String appKey;

    /**
     * 秘钥
     */
    private String appSecret;

    /**
     * AgentId  用于消息发送等接口，在应用基础页面可以看到
     */
    private Long agentId;


    /**
     * 应用类型 
h5微应用 h5_mini
机器人  robot


     */
    private String appType;

    /**
     * 状态 1正常 0注销
     */
    private Integer state;


}
