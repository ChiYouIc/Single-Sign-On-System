package com.cy.sso.core.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

/**
 * @author: 开水白菜
 * @description: 核心工具包
 * @create: 2021-08-24 20:36
 **/
public class CoreUtil {

    private static final String AUTHENTICATION_TOKEN = "Authentication-Token";

    public static String generateCode() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取token
     *
     * @param request 请求
     * @return token 值
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authentication-Token");
        return Optional.ofNullable(token).orElse("");
    }

}
