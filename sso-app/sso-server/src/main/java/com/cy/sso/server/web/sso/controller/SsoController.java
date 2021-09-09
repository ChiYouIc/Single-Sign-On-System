package com.cy.sso.server.web.sso.controller;

import cn.hutool.core.util.StrUtil;
import com.cy.sso.server.core.LoginUserInfoMapper;
import com.cy.sso.server.util.CookieUtil;
import com.cy.sso.server.web.sso.domain.UserInfo;
import com.cy.sso.server.web.sso.service.ISsoService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        // 如果没有 originUrl 参数，则默认跳转到系统 Sso-Server 后台管理
        String redirectUrl = StrUtil.isEmpty(originUrl) ? "/main" : originUrl;

        // 校验密码
        if (ssoService.authentication(userInfo)) {
            String code = ssoService.generateAuthCode(userInfo);
            String[] split = redirectUrl.split("\\?");
            return "redirect:" + split[0] + "?code=" + code;
        }
        return "redirect:/?originUrl=" + redirectUrl;
    }


}
