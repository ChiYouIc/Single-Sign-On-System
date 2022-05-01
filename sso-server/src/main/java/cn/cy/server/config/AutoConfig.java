package cn.cy.server.config;

import cn.cy.log.bo.Operator;
import cn.cy.log.service.IOperatorGetService;
import cn.cy.server.config.properties.JwtProperties;
import cn.cy.server.config.properties.SsoProperties;
import cn.cy.server.core.JwtHelper;
import cn.cy.server.core.interceptor.TokenInvalidInterceptor;
import cn.cy.sso.model.SsoUser;
import cn.cy.sso.utils.SsoUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableConfigurationProperties(value = {SsoProperties.class, JwtProperties.class})
public class AutoConfig implements WebMvcConfigurer {

    @Resource
    private SsoProperties ssoProperties;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private TokenInvalidInterceptor tokenInvalidInterceptor;

    /**
     * 拦截器配置
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Add sso core interceptor TokenInvalidInterceptor.");
        // 配置过滤器
        List<String> exclude = ssoProperties.getExclude();
        registry.addInterceptor(tokenInvalidInterceptor)
                .addPathPatterns(ssoProperties.getBasePath())
                .excludePathPatterns(exclude.toArray(new String[]{}));

        exclude.forEach(s -> log.info("exclude path patterns: {}", s));
    }

    /**
     * 添加资源处理程序
     *
     * @param registry 资源注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ssoProperties.getResourcePath())
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public JwtHelper jwtHelper() {
        return new JwtHelper(jwtProperties, SignatureAlgorithm.HS256);
    }

    /**
     * 提供给日志获取操作人
     *
     * @return IOperatorGetService
     */
    @Bean
    public IOperatorGetService operatorGetService() {
        return () -> {
            SsoUser info = SsoUtil.getInfo();
            return new Operator(info.getId(), info.getUsername());
        };
    }
}
