package com.cy.sso.server.core.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cy.sso.server.core.LoginUserInfoMapper;
import com.cy.sso.server.web.sso.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
        log.info("access source url " + request.getRequestURI());
        String token = request.getHeader("Authentication-Token");
        // 获取缓存好的已登录用户的信息
        if (StrUtil.isNotEmpty(token)) {
            UserInfo userInfo = LoginUserInfoMapper.getUserInfo(token);
            return !ObjectUtil.isEmpty(userInfo);
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
        // 清空当前线程变量
        LoginUserInfoMapper.clearContextHolder();
        log.info("run afterCompletion() ...");
    }
}
