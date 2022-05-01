package cn.cy.server.config;

import cn.cy.server.cache.IAppCacheService;
import cn.cy.server.web.sys.entity.App;
import cn.cy.server.web.sys.service.IAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: you
 * @date: 2022-05-01 12:36
 * @description: Sso 服务初始化
 */
@Slf4j
@Component
public class SsoInit implements CommandLineRunner {

    @Resource
    private IAppService appService;

    @Resource
    private IAppCacheService appCacheService;

    @Override
    public void run(String... args) {
        List<App> list = appService.selectAppAll();
        for (App app : list) {
            appCacheService.setAppInfo(app.getAppCode(), app.getAppName());
            log.info("Cache App Info code: {}, name: {}", app.getAppCode(), app.getAppName());
        }
    }
}
