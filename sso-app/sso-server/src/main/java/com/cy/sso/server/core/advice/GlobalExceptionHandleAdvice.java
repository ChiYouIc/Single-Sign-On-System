package com.cy.sso.server.core.advice;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cy.sso.server.core.exception.AbstractExceptionHandleAdvice;
import com.cy.sso.server.core.exception.ApiException;
import com.cy.sso.server.core.exception.InvalidCodeException;
import com.cy.sso.server.core.response.ErrorCodeEnum;
import com.cy.sso.server.core.response.FailedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/4 15:09
 * @Description: 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandleAdvice extends AbstractExceptionHandleAdvice implements Ordered {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandleAdvice.class);

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<FailedResponse<Object>> handle(InvalidCodeException e) {
        FailedResponse<Object> response = FailedResponse.builder()
                .code(ErrorCodeEnum.BAD_REQUEST.code())
                .msg(ErrorCodeEnum.BAD_REQUEST.msg())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<FailedResponse<Object>> handle(ApiException e) {
        FailedResponse<Object> response = FailedResponse.builder()
                .code(e.getErrorCode().getCode())
                .msg(e.getMessage())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<FailedResponse<Object>> handle(TokenExpiredException e) {
        FailedResponse<Object> response = FailedResponse.builder()
                .code(ErrorCodeEnum.UNAUTHORIZED.code())
                .msg(ErrorCodeEnum.UNAUTHORIZED.msg())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FailedResponse<Object>> handle(Exception e) {
        FailedResponse<Object> response = FailedResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(e.getMessage())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
