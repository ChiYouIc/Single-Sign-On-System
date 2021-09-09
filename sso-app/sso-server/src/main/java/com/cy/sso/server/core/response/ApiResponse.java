package com.cy.sso.server.core.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @Author: 友叔
 * @Date: 2020/11/24 21:29
 * @Description: Api 接口返回（多态，向下封装）
 */
public class ApiResponse implements Serializable {

	/**
	 * 序列
	 */
	private static final long serialVersionUID = -1296644476749112463L;

	/**
	 * 不需要返回结果
	 *
	 * @return 成功结果
	 */
	public static SuccessResponse<Void> success(HttpServletResponse response, int code) {
		response.setStatus(code);
		return SuccessResponse.<Void>builder().code(code).build();
	}

	/**
	 * 成功返回
	 *
	 * @param data 返回的数据
	 * @return 成功结果
	 */
	public static <T> SuccessResponse<T> success(T data) {
		return success(HttpStatus.OK.value(), data);
	}

	/**
	 * 成功返回
	 *
	 * @param data 返回的数据
	 * @return 成功结果
	 */
	public static <T> SuccessResponse<T> success(int code, T data) {
		return SuccessResponse.<T>builder().code(code).data(data).build();
	}

	/**
	 * 成功返回
	 *
	 * @param response 响应对象
	 * @param data     返回的数据
	 * @return 成功结果
	 */
	public static <T> SuccessResponse<T> success(HttpServletResponse response, T data) {
		response.setStatus(HttpStatus.OK.value());
		return SuccessResponse.<T>builder().code(HttpStatus.OK.value()).data(data).build();
	}

	/**
	 * 成功返回
	 *
	 * @param status 状态码
	 * @param data   返回的数据
	 * @return 成功结果
	 */
	public static <T> SuccessResponse<T> success(HttpServletResponse response, int status, T data) {
		response.setStatus(status);
		return SuccessResponse.<T>builder().code(status).data(data).build();
	}

	/**
	 * 成功返回
	 *
	 * @param status 状态码
	 * @param data   返回的数据
	 * @return 成功结果
	 */
	public static <T> SuccessResponse<T> success(ServerHttpResponse response, int status, T data) {
		response.setStatusCode(HttpStatus.valueOf(status));
		return SuccessResponse.<T>builder().code(status).data(data).build();
	}

	/**
	 * 失败返回
	 *
	 * @param exception
	 */
	public static <T> FailedResponse<T> failure(ErrorCodeEnum codeEnum, Exception exception) {
		ErrorCode errorCode = codeEnum.convert();
		return failure(errorCode, exception);
	}

	/**
	 * 失败返回
	 *
	 * @param errorCode
	 * @param exception
	 */
	public static <T> FailedResponse<T> failure(ErrorCode errorCode, Exception exception) {
		return null;
		/*
		 * return ResponseUtils.exceptionMsg(FailedResponse.<T>builder().msg(errorCode.getMsg() ), exception) // .exception(TypeUtils.castToString(exception))
		 * .time(LocalDateTime.now()).code(errorCode.getCode()).build();
		 */
	}

	/**
	 * 失败返回
	 *
	 * @param errorCode
	 */
	public static <T> FailedResponse<T> failure(ErrorCode errorCode) {
		return failure(errorCode, null);
	}

}
