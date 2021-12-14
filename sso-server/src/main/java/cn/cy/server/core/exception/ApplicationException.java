package cn.cy.server.core.exception;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/4 15:04
 * @Description: 系统应用 异常
 */
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable throwable) {
		super(throwable);
	}

	public ApplicationException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
