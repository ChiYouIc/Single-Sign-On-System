package com.cy.sso.server.web.sso.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cy.sso.server.core.JwtHelper;
import com.cy.sso.server.core.redis.IRedisService;
import com.cy.sso.server.web.sso.domain.UserInfo;
import com.cy.sso.server.web.sso.mapper.UserInfoMapper;
import com.cy.sso.server.web.sso.service.ISsoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: 开水白菜
 * @description: Sso 业务 Service
 * @create: 2021-08-08 15:29
 **/
@Service
public class SsoServiceImpl implements ISsoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private IRedisService redisService;

    @Resource
    private JwtHelper jwtHelper;

    @Override
    public boolean authentication(UserInfo userInfo) {
        UserInfo info = userInfoMapper.selectUserInfo(userInfo.getUsername());
        return ObjectUtil.isNotEmpty(info) && StrUtil.equals(userInfo.getPassword(), info.getPassword());
    }

    @Override
    public String generateAuthCode(UserInfo userInfo) {
        String code = UUID.randomUUID().toString();
        UserInfo info = userInfoMapper.selectUserInfo(userInfo.getUsername());
        // 构建 Claims
        Map<String, Object> claims = MapUtil.newHashMap();
        claims.put("id", info.getId());
        claims.put("userId", info.getUserId());
        claims.put("username", info.getUsername());
        // 获取 token
        String token = jwtHelper.encode(claims);
        // 保存 token
        redisService.set(code, token, 60, TimeUnit.SECONDS);
        redisService.set(token, info, 60, TimeUnit.MINUTES);
        return code;
    }
}
