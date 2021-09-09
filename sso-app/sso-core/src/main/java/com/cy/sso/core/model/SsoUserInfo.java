package com.cy.sso.core.model;

import java.util.StringJoiner;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 21:39
 * @Description: 用户信息
 */
public class SsoUserInfo {
    private String id;

    private String userId;

    private String username;

    private String password;

    private String token;

    private String phone;

    private String email;

    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SsoUserInfo.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("userId='" + userId + "'")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("token='" + token + "'")
                .add("phone='" + phone + "'")
                .add("email='" + email + "'")
                .add("status=" + status)
                .toString();
    }
}
