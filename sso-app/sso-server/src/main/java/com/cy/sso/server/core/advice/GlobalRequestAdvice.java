package com.cy.sso.server.core.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/3 20:18
 * @Description:
 */
@RestControllerAdvice(annotations = {RestController.class})
public class GlobalRequestAdvice implements RequestBodyAdvice {

    /**
     * 首先调用以确定此拦截器是否适用。
     *
     * @param methodParameter 方法参数
     * @param targetType      目标类型，不一定与方法类型一致
     * @param converterType   消息转换类型
     * @return 是否应调用此拦截器
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 在读取和转换请求正文之前调用第二次。
     *
     * @param inputMessage  请求内容
     * @param parameter     方法参数
     * @param targetType    目标类型
     * @param converterType 消息转换类型
     * @return 请求内容或新的请求内容实例，切勿返回 null
     * @throws IOException
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    /**
     * 将请求正文转换为对象后调用的第三次（也是最后一次）。
     *
     * @param body          请求内容的消息体
     * @param inputMessage  请求内容
     * @param parameter     方法参数
     * @param targetType    目标类型
     * @param converterType 消息转换类型
     * @return 返回 body 或者 新的实例
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * 如果主体为空，则调用，不再调用 afterBodyRead()。
     *
     * @param body          请求内容的消息体
     * @param inputMessage  请求内容
     * @param parameter     方法参数
     * @param targetType    目标类型
     * @param converterType 消息转换类型
     * @return 返回要使用的值，或者返回{@code null}，如果需要该参数，则可能引发 {@code HttpMessageNotReadableException}。
     */
    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
