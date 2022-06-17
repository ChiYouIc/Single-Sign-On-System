package cn.cy.server.core.exception;

/**
 * @Author: 友
 * @Date: 2022/6/17 11:43
 * @Description: 未知应用异常
 */
public class UnknownAppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnknownAppException(String message) {
        super(message);
    }
}
