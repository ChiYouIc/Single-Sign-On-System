package com.cy.sso.server.core.exception;

/**
 * @author: 开水白菜
 * @description: 无效的 code 异常
 * @create: 2021-08-29 17:47
 **/
public class InvalidCodeException extends RuntimeException {

    public InvalidCodeException() {
        super("无效的 Code 异常！");
    }
}
