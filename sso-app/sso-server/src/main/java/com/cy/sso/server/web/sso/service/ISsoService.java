package com.cy.sso.server.web.sso.service;

import com.cy.sso.server.web.sso.domain.UserInfo;

/**
 * @author: 开水白菜
 * @description: Sso 业务 Service
 * @create: 2021-08-08 15:29
 **/
public interface ISsoService {
    /**
     * 检查用户信息是否存在
     *
     * @param userInfo 用户信息
     */
    public boolean authentication(UserInfo userInfo);
}
