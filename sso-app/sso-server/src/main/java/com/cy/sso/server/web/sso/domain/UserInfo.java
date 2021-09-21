package com.cy.sso.server.web.sso.domain;

import com.cy.sso.core.model.SsoUser;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 19:33
 * @Description: 用户信息
 */
@Getter
@Setter
@Alias("userInfo")
public class UserInfo extends SsoUser {
    private String password;
    private String originUrl;
}
