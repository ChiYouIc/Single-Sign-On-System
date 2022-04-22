package cn.cy.server.sys;

import cn.cy.server.web.sys.entity.App;
import cn.cy.server.web.sys.service.IAppService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/4/22 10:10
 * @Description:
 */
@Slf4j
@SpringBootTest
public class AppServiceTest {

    @Autowired
    private IAppService appService;

    @Test
    public void selectAppList() {
        List<App> apps = appService.selectAppList(null);
        apps.forEach(o -> log.info(o.toString()));
    }


}
