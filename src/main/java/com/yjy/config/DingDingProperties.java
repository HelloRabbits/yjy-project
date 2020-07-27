package com.yjy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangjl
 * @description 钉钉配置
 * @date 2020-05-26 20:23
 */
@Data
@ConfigurationProperties(prefix = "dingding")
public class DingDingProperties {
    /**
     * 企业id
     */
    private String corpId;
    /**
     * 应用类型 {{@link com.yjy.common.AppType}}
     */
    private String appType;
    /**
     * 应用key
     */
    private String appKey;
    /**
     * 应用秘钥
     */
    private String appSecret;
}
