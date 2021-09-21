package com.cy.sso.server.web.sso.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cy.sso.core.model.SsoResult;
import com.cy.sso.core.model.SsoUser;
import com.cy.sso.core.utils.SsoUtil;
import com.cy.sso.server.core.JwtHelper;
import com.cy.sso.server.core.exception.InvalidCodeException;
import com.cy.sso.server.cache.IUserCacheService;
import com.cy.sso.server.core.response.UnifiedReturn;
import com.cy.sso.server.web.sso.domain.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 友叔
 * @Date: 2021/1/8 16:54
 * @Description: 认证Controller
 */
@RestController
public class AuthController {

    @Resource
    private IUserCacheService userCacheService;

    @Resource
    private JwtHelper jwtHelper;

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

    @GetMapping("/currentUser")
    public SsoUser currentUser() {
        return SsoUtil.getInfo();
    }

    /**
     * 验证 code 码，颁发 token
     *
     * @param code code码
     * @return token
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
    public String logout() {
        return "redirect:/";
    }

}
