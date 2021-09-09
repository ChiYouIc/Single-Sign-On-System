package com.cy.sso.server.web.sso.service;

import com.cy.sso.server.web.sso.domain.UserInfo;

import java.security.NoSuchAlgorithmException;

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
     * @return boolean
     */
    public boolean authentication(UserInfo userInfo);

    /**
     * <p>创建认证code，用户兑换 Token
     * <p>传入用户信息，根据用户信息创建一个 code 和一个对应的 token，并进行定时缓存，等待用户使用 code 来兑换 token
     *
     * @param userInfo 用户信息
     * @return code
     */
    public String generateAuthCode(UserInfo userInfo);
}
