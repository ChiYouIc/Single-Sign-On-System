package com.cy.sso.server.core;

import cn.hutool.core.util.ObjectUtil;
import com.cy.sso.server.core.redis.IRedisService;
import com.cy.sso.server.core.redis.impl.IRedisServiceImpl;
import com.cy.sso.server.util.SpringUtil;
import com.cy.sso.server.web.sso.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 友叔
 * @create: 2021/1/7 21:00
 * @description: 已登录用户信息
 */
@Slf4j
public class LoginUserInfoMapper {

    private static final ThreadLocal<UserInfo> contextHolder = new ThreadLocal();

    private static IRedisService getRedisService() {
        return SpringUtil.getBean(IRedisServiceImpl.class);
    }

    public static void setUserInfoMap(String token, UserInfo userInfo) {
        getRedisService().set(token, userInfo);
    }

    public static UserInfo getUserInfo(String token) {
        UserInfo userInfo = (UserInfo) getRedisService().get(token);
        if (ObjectUtil.isNotEmpty(userInfo)) {
            contextHolder.set(userInfo);
        }
        log.info(contextHolder.toString());
        return userInfo;
    }

    public static void removeUserInfo() {
        getRedisService().del(getUserInfo().getToken());
    }

    public static void clearContextHolder() {
        contextHolder.remove();
    }

    public static UserInfo getUserInfo() {
        return contextHolder.get();
    }


}
