package cn.cy.server.web.sso.entity;

import cn.cy.sso.model.SsoUser;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 19:33
 * @Description: 用户信息
 */
@Getter
@Setter
@Alias("userInfo")
public class UserInfo extends SsoUser implements Serializable {

    private static final long serialVersionUID = 369194306814680969L;

    private String password;

    private String originUrl;
}
