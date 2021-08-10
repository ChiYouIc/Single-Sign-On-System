package com.cy.sso.server.interceptor;

import cn.hutool.core.util.StrUtil;
import com.cy.sso.server.core.LoginUserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 20:00
 * @Description:
 */
public class TokenInvalidInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(TokenInvalidInterceptor.class);

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            log.info("access source url " + request.getRequestURI());
            Cookie[] cookies = request.getCookies();
            // 获取缓存好的已登录用户的信息
            if (!ObjectUtils.isEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    log.info(cookie.getName() + ": " + cookie.getValue());
                    // 如果cookie 中存在 Authentication 的键，并且值不为空，则证明登陆过，直接放行
                    if ("Authentication-Token".equals(cookie.getName())) {
                        String token = LoginUserInfoMapper.getUserInfo(cookie.getValue()).getToken();
                        return !StrUtil.isEmpty(token);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("please login ...");
        }
        // 没有登陆过，重定向去登陆
        response.sendRedirect("/");
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("run postHandle() ...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("run afterCompletion() ...");
    }
}
