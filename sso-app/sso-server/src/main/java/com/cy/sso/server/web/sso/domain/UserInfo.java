package com.cy.sso.server.web.sso.domain;

import com.cy.sso.core.model.SsoUserInfo;
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
public class UserInfo extends SsoUserInfo {

}
