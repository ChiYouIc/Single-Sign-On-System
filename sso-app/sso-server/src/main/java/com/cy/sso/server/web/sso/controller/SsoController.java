package com.cy.sso.server.web.sso.controller;

import com.cy.sso.server.core.LoginUserInfoMapper;
import com.cy.sso.server.util.CookieUtil;
import com.cy.sso.server.web.sso.domain.UserInfo;
import com.cy.sso.server.web.sso.service.ISsoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 17:44
 * @Description: 登陆Controller
 */
@Slf4j
@Controller
public class SsoController {

    @Resource
    private ISsoService ssoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserInfo userInfo) {
        String referer = request.getHeader("Referer");
        String pattern = "originUrl=[a-zA-z]+://[^\\s]*";
        Matcher matcher = Pattern.compile(pattern).matcher(referer);
        String originUrl = null;
        if (matcher.find()) {
            originUrl = matcher.group(0).trim();
            originUrl = originUrl.substring(originUrl.indexOf("=") + 1);
        }
        log.info(referer);
        String redirectUrl = StringUtils.isEmpty(originUrl) ? "/main" : originUrl;

        // 校验密码
        if (ssoService.authentication(userInfo)) {
            response.addCookie(this.tokenWrapper(userInfo));
            return "redirect:" + redirectUrl;
        }
        return "redirect:/?" + redirectUrl;
    }

    @GetMapping("/main")
    public String main(HttpServletRequest request) {
        request.setAttribute("userInfo", LoginUserInfoMapper.getUserInfo(CookieUtil.getCookieValue("Authentication-Token")));
        return "main";
    }

    /**
     * 退出登陆
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        LoginUserInfoMapper.removeUserInfo(session.getId());
        return "redirect:/";
    }

    /**
     * 将 token 包装进 cookie
     */
    private Cookie tokenWrapper(UserInfo userInfo) {
        String token = UUID.randomUUID().toString();
        userInfo.setToken(token);
        LoginUserInfoMapper.setUserInfoMap(token, userInfo);

        Cookie cookie = new Cookie("Authentication-Token", token);
        // cookie 要设置路径，有效时间
        cookie.setPath("/");
        cookie.setDomain("sso.com");
        cookie.setMaxAge(60 * 60 * 24);
        return cookie;
    }

}
