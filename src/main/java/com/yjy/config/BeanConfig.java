package com.yjy.config;

import com.yjy.bean.base.LoginAccountInfo;
import com.yjy.utils.SpringBeanFactoryUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * @author zhangjl
 * @description bean配置
 * @date 2020-06-15 16:09
 */
@Configuration
public class BeanConfig {

    @Bean
    public SpringBeanFactoryUtil springBeanFactoryUtil(){
        return new SpringBeanFactoryUtil();
    }

    /**
     * 作为单次请求时保存用户信息使用
     *
     * @return
     */
    @Scope(
            value = "request",
            proxyMode = ScopedProxyMode.TARGET_CLASS
    )
    @Bean
    public LoginAccountInfo loginAccountInfo(){
        return new LoginAccountInfo();
    }

}
