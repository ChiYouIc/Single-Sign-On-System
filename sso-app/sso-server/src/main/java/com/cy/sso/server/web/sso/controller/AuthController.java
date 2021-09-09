package com.cy.sso.server.web.sso.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cy.sso.core.model.SsoResult;
import com.cy.sso.server.core.LoginUserInfoMapper;
import com.cy.sso.server.core.exception.InvalidCodeException;
import com.cy.sso.server.core.redis.IRedisService;
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
    private IRedisService redisService;

    @UnifiedReturn
    @GetMapping("/auth")
    public SsoResult auth(String token) {
        SsoResult ssoResult = new SsoResult();
        // 校验 token
        if (token != null && token.length() > 0) {
            // 校验是否存在该 token
            UserInfo userInfo = LoginUserInfoMapper.getUserInfo(token);
            if (!ObjectUtil.isEmpty(userInfo)) {
                ssoResult.setResult("success");
                ssoResult.setUserInfo(userInfo);
                return ssoResult;
            }
        }
        ssoResult.setResult("fail");
        return ssoResult;
    }

    @GetMapping("/currentUser")
    public UserInfo currentUser() {
        return LoginUserInfoMapper.getUserInfo();
    }

    /**
     * 验证 code 码，颁发 token
     *
     * @param code code码
     * @return token
     */
    @GetMapping("/callback/{code}")
    public String callback(@PathVariable("code") String code) throws InvalidCodeException {
        String token = redisService.get(code, String.class);
        if (StrUtil.isEmpty(token)) {
            throw new InvalidCodeException();
        }
        redisService.del(code);
        return token;
    }

    /**
     * 退出登陆
     */
    @PostMapping("/logout")
    public String logout() {
        LoginUserInfoMapper.removeUserInfo();
        return "redirect:/";
    }

}
