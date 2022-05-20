package cn.cy.server.web.sso.controller;

import cn.cy.mybatis.web.controller.BaseController;
import cn.cy.server.cache.IAppCacheService;
import cn.cy.server.cache.IUserCacheService;
import cn.cy.server.core.JwtHelper;
import cn.cy.server.core.exception.InvalidCodeException;
import cn.cy.server.web.sso.entity.UserInfo;
import cn.cy.sso.model.SsoResult;
import cn.cy.sso.model.SsoUser;
import cn.cy.sso.utils.SsoUtil;
import cn.cy.sso.utils.UserUtil;
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
    private IAppCacheService appCacheService;

    /**
     * 验证token 有效性接口，主要是提供给 Sso-client 调用，
     * 并且请求该接口不经过 TokenInvalidInterceptor（这个在拦截器配置文件中有声明）
     */
    @UnifiedReturn
    @GetMapping("/auth")
    public SsoResult auth(@RequestParam(name = "token") String token, @RequestHeader(name = "appCode") String appCode) {
        SsoResult ssoResult = new SsoResult();
        ssoResult.setResult(SsoResult.Result.FAIL);

        // appCode 与 token 都不能为空
        if (!StrUtil.isAllNotEmpty(token, appCode)) {
            return ssoResult;
        }

        // 校验 appCode
        String appName = appCacheService.getAppInfo(appCode);
        if (StrUtil.isEmpty(appName)) {
            return ssoResult;
        }


        // 校验 token
        UserInfo userInfo = userCacheService.getUserInfo(token);
        if (JwtHelper.isVerify(token) && ObjectUtil.isNotEmpty(userInfo)) {
            ssoResult.setResult(SsoResult.Result.SUCCESS);
            ssoResult.setUserInfo(userInfo);
            return ssoResult;
        }

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
    public String logout() {
        userCacheService.delAuthKeyToken(UserUtil.authKey());
        return "success";
    }

}
