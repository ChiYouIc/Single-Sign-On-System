package com.cy.sso.server.core.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: 友叔
 * @create: 2020/11/27 21:48
 * @description: redis 操作Service
 */
public interface IRedisService {
    /**
     * 保存属性
     */
    public void set(String key, Object value, long time);

    /**
     * 保存属性
     */
    public void set(String key, Object value, long time, TimeUnit timeUnit);

    /**
     * 保存属性
     */
    public void set(String key, Object value);

    /**
     * 获取属性
     */
    public Object get(String key);

    /**
     * 获取属性
     */
    public <T> T get(String key, Class<T> clz);

    /**
     * 删除属性
     */
    public Boolean del(String key);

    /**
     * 批量删除属性
     */
    public Long del(List<String> keys);

    /**
     * 设置过期时间
     */
    public Boolean expire(String key, long time);

    /**
     * 获取过期时间
     */
    public Long getExpire(String key);

    /**
     * 判断是否有该属性
     */
    public Boolean hasKey(String key);

    /**
     * 按delta递增
     */
    public Long incr(String key, long delta);

    /**
     * 按delta递减
     */
    public Long decr(String key, long delta);

    /**
     * 获取Hash结构中的属性
     */
    public Object hGet(String key, String hashKey);

    /**
     * 向Hash结构中放入一个属性
     */
    public Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * 向Hash结构中放入一个属性
     */
    public void hSet(String key, String hashKey, Object value);

    /**
     * 直接获取整个Hash结构
     */
    public Map<Object, Object> hGetAll(String key);

    /**
     * 直接设置整个Hash结构
     */
    public Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * 直接设置整个Hash结构
     */
    public void hSetAll(String key, Map<String, ?> map);

    /**
     * 删除Hash结构中的属性
     */
    public void hDel(String key, Object... hashKey);

    /**
     * 判断Hash结构中是否有该属性
     */
    public Boolean hHasKey(String key, String hashKey);

    /**
     * Hash结构中属性递增
     */
    public Long hIncr(String key, String hashKey, Long delta);

    /**
     * Hash结构中属性递减
     */
    public Long hDecr(String key, String hashKey, Long delta);

    /**
     * 获取Set结构
     */
    public Set<Object> sMembers(String key);

    /**
     * 向Set结构中添加属性
     */
    public Long sAdd(String key, Object... values);

    /**
     * 向Set结构中添加属性
     */
    public Long sAdd(String key, long time, Object... values);

    /**
     * 是否为Set中的属性
     */
    public Boolean sIsMember(String key, Object value);

    /**
     * 获取Set结构的长度
     */
    public Long sSize(String key);

    /**
     * 删除Set结构中的属性
     */
    public Long sRemove(String key, Object... values);

    /**
     * 获取List结构中的属性
     */
    public List<Object> lRange(String key, long start, long end);

    /**
     * 获取List结构的长度
     */
    public Long lSize(String key);

    /**
     * 根据索引获取List中的属性
     */
    public Object lIndex(String key, long index);

    /**
     * 向List结构中添加属性
     */
    public Long lPush(String key, Object value);

    /**
     * 向List结构中添加属性
     */
    public Long lPush(String key, Object value, long time);

    /**
     * 向List结构中批量添加属性
     */
    public Long lPushAll(String key, Object... values);

    /**
     * 向List结构中批量添加属性
     */
    public Long lPushAll(String key, Long time, Object... values);

    /**
     * 从List结构中移除属性
     */
    public Long lRemove(String key, long count, Object value);
}
