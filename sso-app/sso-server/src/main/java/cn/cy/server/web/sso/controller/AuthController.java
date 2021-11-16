package cn.cy.server.web.sso.controller;

import cn.cy.mybatis.web.controller.BaseController;
import cn.cy.server.cache.IUserCacheService;
import cn.cy.server.core.JwtHelper;
import cn.cy.server.core.exception.InvalidCodeException;
import cn.cy.server.web.sso.entity.UserInfo;
import cn.cy.sso.model.SsoResult;
import cn.cy.sso.model.SsoUser;
import cn.cy.sso.utils.SsoUtil;
import cn.cy.web.response.UnifiedReturn;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 友叔
 * @Date: 2021/1/8 16:54
 * @Description: 认证Controller
 */
@RestController
public class AuthController extends BaseController {

    @Resource
    private IUserCacheService userCacheService;

    @Resource
    private JwtHelper jwtHelper;

    /**
     * 验证token 有效性接口，主要是提供给 Sso-client 调用，
     * 并且请求该接口不经过 TokenInvalidInterceptor（这个在拦截器配置文件中有声明）
     */
    @UnifiedReturn
    @GetMapping("/auth")
    public SsoResult auth(String token) {
        SsoResult ssoResult = new SsoResult();
        // 校验 token
        if (token != null && token.length() > 0) {
            // 校验是否存在该 token
            UserInfo userInfo = userCacheService.getUserInfo(token);
            if (jwtHelper.isVerify(token) && ObjectUtil.isNotEmpty(userInfo)) {
                ssoResult.setUserInfo(userInfo);
                return ssoResult;
            }
        }
        ssoResult.setResult("fail");
        return ssoResult;
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/currentUser")
    public SsoUser currentUser() {
        return SsoUtil.getInfo();
    }

    /**
     * 验证 code 码，获取 token 令牌
     */
    @GetMapping("/callback/{code}")
    public String callback(@PathVariable("code") String code) throws InvalidCodeException {
        String token = userCacheService.getToken(code);
        if (StrUtil.isEmpty(token)) {
            throw new InvalidCodeException();
        }
        return token;
    }

    /**
     * 退出登陆
     */
    @PostMapping("/logout")
    public String logout(@CookieValue(value = "auth-key", defaultValue = StrUtil.EMPTY) String authKey) {
        userCacheService.delAuthKeyToken(authKey);
        return "redirect:/";
    }

}