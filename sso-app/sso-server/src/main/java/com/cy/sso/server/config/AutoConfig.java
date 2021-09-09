package com.cy.sso.server.config;

import com.cy.sso.server.config.properties.JwtProperties;
import com.cy.sso.server.config.properties.SsoProperties;
import com.cy.sso.server.core.JwtHelper;
import com.cy.sso.server.core.interceptor.TokenInvalidInterceptor;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 20:00
 * @Description: 配置
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = AutoConfig.BASE_PATH)
@EnableConfigurationProperties(value = {
        SsoProperties.class,
        JwtProperties.class
})
public class AutoConfig implements WebMvcConfigurer {

    protected static final String BASE_PATH = "com.cy.sso";

    @Resource
    private SsoProperties ssoProperties;

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Add sso core interceptor TokenInvalidInterceptor.");
        List<String> exclude = ssoProperties.getExclude();
        registry.addInterceptor(new TokenInvalidInterceptor())
                .addPathPatterns(ssoProperties.getBasePath())
                .excludePathPatterns(exclude.toArray(new String[]{}));

        exclude.forEach(s -> log.info("exclude path patterns: {}", s));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ssoProperties.getResourcePath())
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + ssoProperties.getResourcePath());
    }

    @Bean
    public JwtHelper jwtHelper() {
        return new JwtHelper(jwtProperties, SignatureAlgorithm.HS256);
    }
}
