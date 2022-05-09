package cn.cy.server.core.advice;

import cn.cy.server.core.exception.AbstractExceptionHandleAdvice;
import cn.cy.server.core.exception.InvalidCodeException;
import cn.cy.web.response.ErrorCodeEnum;
import cn.cy.web.response.FailedResponse;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
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
public class SsoExceptionHandleAdvice extends AbstractExceptionHandleAdvice implements Ordered {

    private final static Logger LOGGER = LoggerFactory.getLogger(SsoExceptionHandleAdvice.class);

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<FailedResponse> handleInvalidCodeException(InvalidCodeException e) {
        FailedResponse response = FailedResponse.builder()
                .code(ErrorCodeEnum.UNAUTHORIZED.code())
                .msg(ErrorCodeEnum.UNAUTHORIZED.msg())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<FailedResponse> handleTokenExpiredException(TokenExpiredException e) {
        FailedResponse response = FailedResponse.builder()
                .code(ErrorCodeEnum.UNAUTHORIZED.code())
                .msg(ErrorCodeEnum.UNAUTHORIZED.msg())
                .exception(ExceptionUtil.stacktraceToString(e))
                .build();
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
