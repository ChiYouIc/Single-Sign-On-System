package com.cy.sso.server.core.response;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/4 15:09
 * @Description: 失败返回API
 */


@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class FailedResponse<T> implements ResponseType {

	private static final long serialVersionUID = 1L;
	/**
	 * http 状态码
	 */
	@Builder.Default
	private int code = 500;
	/**
	 * 错误描述
	 */
	private String msg;
	/**
	 * 异常信息
	 */
	private String exception;
	/**
	 * 当前时间戳
	 */
	@JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
	private LocalDateTime time;
}
