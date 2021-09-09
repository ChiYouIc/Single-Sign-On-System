package com.cy.sso.server.core.exception;


import com.cy.sso.server.core.response.ErrorCode;
import com.cy.sso.server.core.response.ErrorCodeEnum;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/4 15:03
 * @Description: API 业务异常类
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	private final ErrorCode errorCode;

	public ApiException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum.msg());
		this.errorCode = errorCodeEnum.convert();
	}

	public ApiException(ErrorCode errorCode) {
		super(errorCode.getMsg());
		this.errorCode = errorCode;

	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
