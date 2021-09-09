package com.cy.sso.server.core.response;

import org.springframework.http.HttpStatus;

import java.lang.annotation.*;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/4 15:09
 * @Description: 包装API返回注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnifiedReturn {

	/**
	 * 是否包装返回
	 */
	boolean wrapper() default false;

	/**
	 * 正常返回httpcode码
	 */
	HttpStatus status() default HttpStatus.OK;
}