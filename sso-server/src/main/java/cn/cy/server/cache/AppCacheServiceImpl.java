package cn.cy.server.cache;

import cn.cy.redis.service.IRedisService;
import cn.cy.server.web.sys.entity.App;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: you
 * @date: 2022-05-01 12:40
 * @description: 应用信息缓存Service实现
 */
@Service
public class AppCacheServiceImpl implements IAppCacheService {

    @Resource
    private IRedisService redisService;

    @Value("${sso-server.app-cache-key}")
    private String appCacheKey;

    @Override
    public void setAppInfo(String appCode, String appName) {
        redisService.hSet(appCacheKey, appCode, appName);
    }

    @Override
    public String getAppInfo(String appCode) {
        Object o = redisService.hGet(appCacheKey, appCode);
        return StrUtil.toString(o);
    }
}
