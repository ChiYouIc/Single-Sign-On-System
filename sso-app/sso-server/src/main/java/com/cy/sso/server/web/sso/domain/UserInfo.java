package com.cy.sso.server.web.sso.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 19:33
 * @Description: 用户信息
 */
@Getter
@Setter
@ToString
@Alias("userInfo")
public class UserInfo {
    private String id;

    private String userId;

    private String username;

    private String password;

    private String token;

    private String phone;

    private String email;

    private int status;

}
