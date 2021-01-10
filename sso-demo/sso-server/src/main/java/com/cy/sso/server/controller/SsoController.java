package com.cy.sso.server.controller;

import com.cy.sso.server.core.LoginUserInfoMapper;
import com.cy.sso.server.domain.UserInfo;
import com.cy.sso.server.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

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
@Controller
public class SsoController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		return "index";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserInfo userInfo) {
		String referer = request.getHeader("Referer");
		String pattern = "orginUrl=[a-zA-z]+://[^\\s]*";
		Matcher matcher = Pattern.compile(pattern).matcher(referer);
		String orginUrl = null;
		if (matcher.find()) {
			orginUrl = matcher.group(0).trim();
			orginUrl = orginUrl.substring(orginUrl.indexOf("=") + 1);
		}
		System.out.println(referer);
		String redirectUrl = StringUtils.isEmpty(orginUrl) ? "/main" : orginUrl;
		// 校验密码
		if ("123456".equals(userInfo.getPassword())) {
			String token = UUID.randomUUID().toString();
			userInfo.setToken(token);
			LoginUserInfoMapper.setUserInfoMap(token, userInfo);
			// cookie 要设置路径，有效时间
			Cookie cookie = new Cookie("Authentication-Token", token);
			cookie.setPath("/");
			cookie.setDomain("sso.demo");
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
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

}
