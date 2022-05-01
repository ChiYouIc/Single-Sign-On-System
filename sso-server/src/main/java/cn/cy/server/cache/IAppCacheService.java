package cn.cy.server.cache;

/**
 * @author: you
 * @date: 2022-05-01 12:40
 * @description: 应用信息缓存Service
 */
public interface IAppCacheService {

    /**
     * 使用 hash 表缓存所有的 App 信息
     *
     * @param appCode 应用码
     * @param appName 应用名称
     */
    public void setAppInfo(String appCode, String appName);

    /**
     * 获取 App 信息
     *
     * @param appCode 应用码
     * @return 应用名称
     */
    public String getAppInfo(String appCode);

}
