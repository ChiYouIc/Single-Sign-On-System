package cn.cy.server.core.interceptor;

import cn.cy.server.cache.IUserCacheService;
import cn.cy.server.core.JwtHelper;
import cn.cy.server.web.sso.entity.UserInfo;
import cn.cy.sso.utils.SsoUtil;
import cn.hutool.core.util.ObjectUtil;
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
    private IUserCacheService userCacheService;

    /**
     * <p>验证当前请求用户令牌有效性，如果没有令牌或者是令牌失效，则直接重定向到登录首页；
     * <p>如果用户令牌有效，绑定一个用户信息的当前线程变量，前往一下个执行链（返回 true）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authentication-Token");

        // 验证 token 有效性
        UserInfo userInfo = userCacheService.getUserInfo(token);
        if (JwtHelper.isVerify(token) && ObjectUtil.isNotEmpty(userInfo)) {
            userInfo.setToken(token);
            SsoUtil.setInfo(userInfo);
            return true;
        }
        response.setStatus(401);
        return false;
    }

    @Override
    public int getOrder() {
        return 90;
    }
}
