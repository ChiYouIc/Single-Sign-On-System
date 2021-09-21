package com.cy.sso.server.core.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.cy.sso.core.utils.SsoUtil;
import com.cy.sso.server.core.JwtHelper;
import com.cy.sso.server.cache.IUserCacheService;
import com.cy.sso.server.web.sso.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 20:00
 * @Description:
 */
@Slf4j
@Component
public class TokenInvalidInterceptor implements HandlerInterceptor {

    private final JwtHelper jwtHelper;

    private final IUserCacheService userCacheService;

    @Autowired
    public TokenInvalidInterceptor(JwtHelper jwtHelper, IUserCacheService userCacheService) {
        this.jwtHelper = jwtHelper;
        this.userCacheService = userCacheService;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("access source url " + request.getRequestURI());
        String token = request.getHeader("Authentication-Token");

        // 验证 token 有效性
        UserInfo userInfo = userCacheService.getUserInfo(token);
        if (jwtHelper.isVerify(token) && ObjectUtil.isNotEmpty(userInfo)) {
            SsoUtil.setInfo(userInfo);
            return true;
        }

        // 没有登陆过，重定向去登陆
        response.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("run postHandle() ...");
    }
}
