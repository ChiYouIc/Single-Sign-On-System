package com.cy.sso.server.domain;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 19:33
 * @Description: 用户信息
 */
public class UserInfo {
	private String username;

	private String password;

	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", token='" + token + '\'' +
				'}';
	}
}
