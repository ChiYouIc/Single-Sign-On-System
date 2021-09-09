package com.cy.sso.server.core.redis.impl;

import com.cy.sso.server.core.redis.IRedisKeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author: 友叔
 * @Date: 2021/1/1 23:47
 * @Description: Redis Key 操作封装Service 实体类
 */
@Service
public class IRedisKeysServiceImpl implements IRedisKeysService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public Set<String> getKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}
}
