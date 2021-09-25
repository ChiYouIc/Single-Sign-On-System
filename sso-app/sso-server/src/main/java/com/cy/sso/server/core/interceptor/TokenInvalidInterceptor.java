package com.cy.sso.server.core.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.cy.sso.core.utils.SsoUtil;
import com.cy.sso.server.cache.IUserCacheService;
import com.cy.sso.server.core.JwtHelper;
import com.cy.sso.server.web.sso.domain.UserInfo;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 20:00
 * @Description:
 */
@Component
public class TokenInvalidInterceptor implements HandlerInterceptor, Ordered {

    @Resource
    private JwtHelper jwtHelper;

    @Resource
    private IUserCacheService userCacheService;

    /**
     * <p>验证当前请求用户令牌有效性，如果没有令牌或者是令牌失效，则直接重定向到登录首页；
     * <p>如果用户令牌有效，绑定一个用户信息的当前线程变量，前往一下个执行链（返回 true）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
    public int getOrder() {
        return 90;
    }
}
