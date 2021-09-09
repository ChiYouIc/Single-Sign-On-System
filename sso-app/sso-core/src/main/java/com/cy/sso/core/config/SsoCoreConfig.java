package com.cy.sso.core.config;

import com.cy.sso.core.config.properties.SsoProperties;
import com.cy.sso.core.interceptor.AuthInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 21:42
 * @Description: sso单点核心配置
 */
@Configuration
@ComponentScan(basePackages = SsoCoreConfig.BASE_PACKAGE)
@EnableConfigurationProperties(value = SsoProperties.class)
public class SsoCoreConfig implements WebMvcConfigurer {

    private final static Log logger = LogFactory.getLog(SsoCoreConfig.class);

    protected final static String BASE_PACKAGE = "com.cy.sso.core";

    @Resource
    private SsoProperties properties;

    @Resource
    private AuthInterceptor authInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("Add sso core interceptor AuthInterceptor.");
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/error",
                        "/logout");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri(properties.getUrl())
                .build();
    }
}
