package com.yjy.config;

import com.yjy.utils.SpringBeanFactoryUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
