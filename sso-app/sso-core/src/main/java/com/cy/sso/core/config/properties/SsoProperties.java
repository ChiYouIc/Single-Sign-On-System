package com.cy.sso.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: 开水白菜
 * @description: SSO 配置
 * @create: 2021-08-08 13:16
 **/
@ConfigurationProperties(prefix = SsoProperties.PREFIX)
public class SsoProperties {
    protected final static String PREFIX = "sso-core";

    private ServerType type;

    private String url;

    public ServerType getType() {
        return type;
    }

    public void setType(ServerType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String logoutUrl() {
        return "/logout";
    }
}
