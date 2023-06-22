package com.runjing.learn_runjing.erp.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private ErpInterceptor erpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(erpInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

}
