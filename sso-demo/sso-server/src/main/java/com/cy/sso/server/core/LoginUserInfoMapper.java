package com.cy.sso.server.core;

import com.cy.sso.server.domain.UserInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 21:00
 * @Description: 已登录用户信息
 */
public class LoginUserInfoMapper {

	private final static Map<String, UserInfo> USER_INFO_MAP = new ConcurrentHashMap<>();

	public static void setUserInfoMap(String token, UserInfo userInfo) {
		USER_INFO_MAP.put(token, userInfo);
	}

	public static UserInfo getUserInfo(String token) {
		return USER_INFO_MAP.get(token);
	}

	public static void removeUserInfo(String token) {
		USER_INFO_MAP.remove(token);
	}

}
