package com.cy.sso.server.web.sso.service;

import com.cy.sso.server.web.sso.domain.LoginParam;
import com.cy.sso.server.web.sso.domain.UserInfo;

import javax.servlet.http.HttpSession;
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
     * @param param 登录信息
     * @return boolean
     */
    public boolean authentication(LoginParam param);

    /**
     * <p>创建认证code，用户兑换 Token
     * <p>传入用户信息，根据用户信息创建一个 code 和一个对应的 token，并进行定时缓存，等待用户使用 code 来兑换 token
     *
     * @param param   用户名
     * @param session session
     * @return code
     */
    public String generateToken(LoginParam param, HttpSession session);

    /**
     * 创建 code
     *
     * @param token 令牌
     * @return code 码
     */
    public String generateCode(String token);
}
