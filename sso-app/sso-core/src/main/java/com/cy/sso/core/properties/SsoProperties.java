package com.cy.sso.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: 开水白菜
 * @description: SSO 配置
 * @create: 2021-08-08 13:16
 **/
@ConfigurationProperties(prefix = SsoProperties.PREFIX)
public class SsoProperties {
    protected final static String PREFIX = "sso";

    private String url;

    private String originUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }
}
