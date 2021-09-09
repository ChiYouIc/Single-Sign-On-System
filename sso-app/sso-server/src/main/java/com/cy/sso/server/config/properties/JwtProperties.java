package com.cy.sso.server.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: 开水白菜
 * @description: Json Web Token 配置信息
 * @create: 2021-08-29 11:23
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = JwtProperties.PREFIX)
public class JwtProperties {

    protected static final String PREFIX = "jwt";

    /**
     * 定义 token 的返回头
     */
    private String header;

    /**
     * token 前缀
     */
    private String tokenPrefix;

    /**
     * 密钥签发人
     */
    private String iss;

    /**
     * 签名密钥
     */
    private String secret;

    /**
     * 有效期
     */
    private long expireTime;

}
