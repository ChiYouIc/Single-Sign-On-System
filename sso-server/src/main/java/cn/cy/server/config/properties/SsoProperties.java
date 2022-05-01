package cn.cy.server.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: 开水白菜
 * @description: Sso配置属性
 * @create: 2021-08-15 11:49
 **/
@Setter
@Getter
@ConfigurationProperties(prefix = SsoProperties.PREFIX)
public class SsoProperties {

    protected static final String PREFIX = "sso-server";

    private String basePath = "/**";

    private String resourcePath = "/static/**";

    private List<String> exclude;

    private String originPassword;

    private String userCacheKey;

    private String appCacheKey;
}
