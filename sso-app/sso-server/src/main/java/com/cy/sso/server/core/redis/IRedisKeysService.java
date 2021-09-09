package com.cy.sso.server.core.redis;

import java.util.Set;

/**
 * @Author: 友叔
 * @Date: 2021/1/1 23:46
 * @Description: Redis Key 操作封装Service
 */
public interface IRedisKeysService {

	/**
	 * key 模糊查询
	 *
	 * @param pattern 匹配的key
	 * @return 所有匹配key
	 */
	public Set<String> getKeys(String pattern);
}
