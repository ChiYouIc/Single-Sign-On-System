package cn.cy.server.utils;

import cn.cy.common.util.SpringUtil;
import cn.cy.server.config.properties.JwtProperties;
import cn.cy.server.config.properties.SsoProperties;
import cn.cy.server.web.sys.service.IAppService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 友
 * @Date: 2022/5/20 15:49
 * @Description:
 */
@Slf4j
@SpringBootTest
public class SpringUtilTest {

    @Test
    public void test() {
        IAppService service = SpringUtil.getBean(IAppService.class);
        SsoProperties ssoProperties = SpringUtil.getBean(SsoProperties.class);
        JwtProperties jwtProperties = SpringUtil.getBean(JwtProperties.class);
        log.info("ssssss");
    }
}
