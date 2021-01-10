package com.cy.sso.core.config;

import com.cy.sso.core.interceptor.AuthInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 21:42
 * @Description: sso单点核心配置
 */
@Configuration
public class SsoCoreConfig implements WebMvcConfigurer {

	private final static Log logger = LogFactory.getLog(SsoCoreConfig.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("create sso core interceptor...");
		registry.addInterceptor(new AuthInterceptor())
				.addPathPatterns("/**");
	}
}
