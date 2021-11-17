package com.cy.sso.client.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 21:51
 * @Description: 全局配置
 */
@Configuration
@ComponentScan(basePackages = AutoConfig.BASE_PATH)
public class AutoConfig implements WebMvcConfigurer {
    protected static final String BASE_PATH = "cn.cy";
}
