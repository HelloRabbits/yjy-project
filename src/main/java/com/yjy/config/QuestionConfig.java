package com.yjy.config;

import com.yjy.service.IAppInfoService;
import com.yjy.service.sdk.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangjl
 * @description 配置文件
 * @date 2020-05-26 20:25
 */
@Configuration
public class QuestionConfig {

    @Bean
    public DingDingProperties dingProperties(){
        return new DingDingProperties();
    }



    @Bean
    public TokenService tokenService(IAppInfoService appInfoService){
        return new TokenService(appInfoService);
    }
}
