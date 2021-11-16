package cn.cy.server.core.advice;

import cn.cy.web.exception.ApiException;
import cn.cy.web.response.ErrorCodeEnum;
import cn.cy.web.response.FailedResponse;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import cn.cy.server.core.exception.AbstractExceptionHandleAdvice;
import cn.cy.server.core.exception.InvalidCodeException;
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
public class ExceptionHandleAdvice extends AbstractExceptionHandleAdvice implements Ordered {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandleAdvice.class);

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<FailedResponse> handle(InvalidCodeException e) {
        FailedResponse response = FailedResponse.builder()
                .code(ErrorCodeEnum.UNAUTHORIZED.code())
                .msg(ErrorCodeEnum.UNAUTHORIZED.msg())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<FailedResponse> handle(ApiException e) {
        FailedResponse response = FailedResponse.builder()
                .code(e.getErrorCode().getCode())
                .msg(e.getMessage())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<FailedResponse> handle(TokenExpiredException e) {
        FailedResponse response = FailedResponse.builder()
                .code(ErrorCodeEnum.UNAUTHORIZED.code())
                .msg(ErrorCodeEnum.UNAUTHORIZED.msg())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FailedResponse> handle(Exception e) {
        FailedResponse response = FailedResponse.builder()
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
