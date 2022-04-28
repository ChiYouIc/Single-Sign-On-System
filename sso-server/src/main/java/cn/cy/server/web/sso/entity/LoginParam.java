package cn.cy.server.web.sso.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author: 开水白菜
 * @description: 登录参数
 * @create: 2021-09-18 00:06
 **/
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class LoginParam {
    private String username;
    private String password;
    private String originUrl;
    private String originUri;
    private String app;

    private String msg;

    @Setter(value = AccessLevel.NONE)
    private Integer code;

    public void setCode(Integer code) {
        this.msg = code == 401 ? "用户名或密码错误！" : null;
        this.code = code;
    }
}
