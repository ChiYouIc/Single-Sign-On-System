package cn.cy.server.core.exception;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/4 14:55
 * @Description: 未知枚举 异常
 */
public class UnknownEnumException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownEnumException(String message) {
		super(message);
	}

	public UnknownEnumException(Throwable throwable) {
		super(throwable);
	}

	public UnknownEnumException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
