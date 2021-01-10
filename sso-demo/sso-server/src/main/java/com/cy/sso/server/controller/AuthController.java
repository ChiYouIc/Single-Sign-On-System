package com.cy.sso.server.controller;

import cn.hutool.core.util.ObjectUtil;
import com.cy.sso.server.core.LoginUserInfoMapper;
import com.cy.sso.server.domain.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 友叔
 * @Date: 2021/1/8 16:54
 * @Description: 认证Controller
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/")
	public Map<String, Object> auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = request.getParameter("token");
		Map<String, Object> result = new HashMap<>();
		// 校验 token
		if (token != null && token.length() > 0) {
			// 校验是否存在该 token
			UserInfo userInfo = LoginUserInfoMapper.getUserInfo(token);
			if (!ObjectUtil.isEmpty(userInfo)) {
				result.put("result", "success");
				result.put("userInfo", userInfo);
				return result;
			}
		}
		result.put("result", "fail");
		return result;
	}

}
