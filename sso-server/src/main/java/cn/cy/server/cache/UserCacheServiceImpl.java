package cn.cy.server.cache;

import cn.cy.redis.service.IRedisService;
import cn.cy.server.config.properties.JwtProperties;
import cn.cy.server.web.sso.entity.UserInfo;
import cn.cy.web.exception.ApiAssert;
import cn.cy.web.response.ErrorCodeEnum;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: 开水白菜
 * @description: 用户信息缓存service
 * @create: 2021-09-15 23:24
 **/
@Service
public class UserCacheServiceImpl implements IUserCacheService {

    @Resource
    public IRedisService redisService;

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public void setUserInfo(String userId, UserInfo userInfo) {
        redisService.set(userId, userInfo, jwtProperties.getExpireTime(), TimeUnit.SECONDS);
    }

    @Override
    public UserInfo getUserInfo(String token) {
        return redisService.get(token, UserInfo.class);
    }

    @Override
    public void delUserInfo(String token) {
        redisService.del(token);
    }

    @Override
    public void setToken(String code, String token) {
        redisService.set(code, token, 60, TimeUnit.SECONDS);
    }

    @Override
    public String getToken(String code) {
        String token = redisService.get(code, String.class);
        redisService.del(code);
        return token;
    }

    @Override
    public void setAuthKeyToken(String authKey, String token) {
        redisService.set(authKey, token, jwtProperties.getExpireTime(), TimeUnit.SECONDS);
    }

    @Override
    public String getAuthKeyToken(String sessionId) {
        return redisService.get(sessionId, String.class);
    }

    @Override
    public void delAuthKeyToken(String authKey) {
        ApiAssert.notNull(ErrorCodeEnum.BAD_REQUEST.convert("authKey 不能为空."), authKey);
        
        String token = this.getAuthKeyToken(authKey);
        if (StrUtil.isNotEmpty(token)) {
            // 删除 token 与用户关系
            this.delUserInfo(token);
        }

        // 删除 authKey 与 token 关系
        redisService.del(authKey);
    }
}
