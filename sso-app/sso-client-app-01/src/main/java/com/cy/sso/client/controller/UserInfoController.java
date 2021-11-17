package com.cy.sso.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 21:47
 * @Description: 用户信息Controller
 */
@Controller
public class UserInfoController {

	@GetMapping("/info")
	public String userInfo(){
		return "index";
	}

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "test";
	}

}
