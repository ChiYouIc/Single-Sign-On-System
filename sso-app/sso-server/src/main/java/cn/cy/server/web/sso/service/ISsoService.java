package cn.cy.server.web.sso.service;

import cn.cy.server.web.sso.entity.LoginParam;

import javax.servlet.http.HttpSession;

/**
 * 单点登录验证业务 Service
 *
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
     * <p>传入用户登录信息，并进行验证，如果验证通过就为当前用户创建一个 code-token 键值对缓存到服务中，
     * code 的目的是用来兑换 token 的；除了为用户创建 token 外，还需要为当前 Sso-Server 创建一个凭证，
     * 即 auth-key，auth-key 的目的也是用来验证当前用户是否已认证，这是为了实现不跨域单点而设计的。
     *
     * @param param   用户名
     * @param session session
     * @return code
     */
    public String generateToken(LoginParam param, HttpSession session);

    /**
     * 创建 code - token 键值对
     *
     * @param token 令牌
     * @return code 码
     */
    public String generateCode(String token);
}
