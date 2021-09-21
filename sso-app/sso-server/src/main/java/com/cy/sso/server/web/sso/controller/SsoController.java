package com.cy.sso.server.web.sso.controller;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cy.sso.server.cache.IUserCacheService;
import com.cy.sso.server.core.JwtHelper;
import com.cy.sso.server.util.CookieUtil;
import com.cy.sso.server.web.sso.domain.LoginParam;
import com.cy.sso.server.web.sso.service.ISsoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 17:44
 * @Description: 登陆Controller
 */
@Slf4j
@Controller
public class SsoController {

    @Resource
    private JwtHelper jwtHelper;

    @Resource
    private ISsoService ssoService;

    @Resource
    private IUserCacheService userCacheService;

    /**
     * <p>登录页</p>
     * <p>访问登录页的时候，这里先去 cookie 获取 auth-key，如果存在{@link #login(HttpSession, HttpServletResponse, LoginParam)}，
     * <p>即证明该用户是已登录过，所以直接使用 auth-key 到缓存（这里使用的是 redis 缓存）中获取 token 并校验；
     * <p>如果校验通过，在缓存中创建一个 code - token 键值对{@link ISsoService#generateCode(String)}，并将 code 作为参数重定向到 originUrl 对应的业务系统；
     * <p><b>tip：code 是用来兑换 token 的{@link AuthController#callback(String)}</b>
     */
    @GetMapping("/")
    public String index(Model model, @RequestParam String originUrl) {
        // 先校验一下当前域下cookie是否存在token信息，存在就验证 token，直接redirect 到访问系统页面
        String authKey = CookieUtil.getCookieValue("auth-key");
        try {
            log.info(originUrl);
            // 如果用户已经登陆过，直接重新发布 code，用于获取 token 令牌
            String token = StrUtil.isAllNotEmpty(authKey, originUrl) ? userCacheService.getAuthKeyToken(authKey) : null;
            if (StrUtil.isNotEmpty(token)) {
                jwtHelper.isVerify(token);
                String[] split = originUrl.split("\\?");
                return "redirect:" + split[0] + "?code=" + ssoService.generateCode(token);
            }

            // 当前连接的用户认证无效
            model.addAttribute("loginParam", new LoginParam().setOriginUrl(originUrl));
            return "index";

        }
        // token 验证失败 { jwtHelper.isVerify(token); }
        catch (TokenExpiredException e) {
            e.printStackTrace();
            userCacheService.delAuthKeyToken(authKey);
            model.addAttribute("loginParam", new LoginParam().setOriginUrl(originUrl));
            return "index";
        }
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public String login(HttpSession session, HttpServletResponse response, @ModelAttribute LoginParam param) {

        String originUrl = param.getOriginUrl();

        // 如果没有 originUrl 参数，则默认跳转到系统 Sso-Server 后台管理
        String redirectUrl = StrUtil.isEmpty(originUrl) ? "/main" : originUrl;

        // 校验密码
        if (ssoService.authentication(param)) {
            String code = ssoService.generateToken(param, session);
            CookieUtil.addCookieValue(response, "auth-key", session.getId());
            String[] split = redirectUrl.split("\\?");
            return "redirect:" + split[0] + "?code=" + code;
        }
        return "redirect:/?originUrl=" + redirectUrl;
    }
}
