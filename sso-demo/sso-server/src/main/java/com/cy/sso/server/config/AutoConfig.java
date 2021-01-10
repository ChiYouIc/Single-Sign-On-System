package com.cy.sso.server.config;

import com.cy.sso.server.interceptor.TokenInvalidInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 20:00
 * @Description: 配置
 */
@Configuration
public class AutoConfig implements WebMvcConfigurer {


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenInvalidInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/",
						"/login",
						"/auth/**",
						"/static/js/jQuery.js");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
				.addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/**");
	}
}
