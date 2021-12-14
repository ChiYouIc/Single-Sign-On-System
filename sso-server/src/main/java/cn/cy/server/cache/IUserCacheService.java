package cn.cy.server.cache;

import cn.cy.server.web.sso.entity.UserInfo;

/**
 * @author: 开水白菜
 * @description: 用户缓存信息Service
 * @create: 2021-09-15 23:24
 **/
public interface IUserCacheService {

    /**
     * 缓存用户信息
     *
     * @param token    令牌
     * @param userInfo 用户信息
     */
    public void setUserInfo(String token, UserInfo userInfo);

    /**
     * 获取用户信息
     *
     * @param token 令牌
     * @return 用户信息
     */
    public UserInfo getUserInfo(String token);

    /**
     * 删除用户信息
     *
     * @param token 令牌
     */
    public void delUserInfo(String token);

    /**
     * 缓存 token
     *
     * @param code  code 码
     * @param token 令牌
     */
    public void setToken(String code, String token);

    /**
     * 获取 token
     *
     * @param code code码
     * @return token 令牌
     */
    public String getToken(String code);

    /**
     * 保存 session 与 token 关系
     *
     * @param authKey 认证key
     * @param token   令牌
     */
    public void setAuthKeyToken(String authKey, String token);

    /**
     * 保存 session 与 token 关系
     *
     * @param authKey 认证key
     * @return token 令牌
     */
    public String getAuthKeyToken(String authKey);

    /**
     * 删除 认证key 与 token 关系
     *
     * @param authKey 认证key
     */
    public void delAuthKeyToken(String authKey);

}
