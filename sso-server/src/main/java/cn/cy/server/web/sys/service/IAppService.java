package cn.cy.server.web.sys.service;

import cn.cy.server.web.sys.entity.App;

import java.util.List;

/**
 * @author: 开水白菜
 * @description: 应用Service
 * @create: 2022-02-22 21:10
 **/
public interface IAppService {
    /**
     * 查询应用列表
     *
     * @param app 应用参数
     * @return 列表
     */
    public List<App> selectAppList(App app);

    /**
     * 查询应用
     *
     * @param id
     * @return
     */
    public App selectAppById(Long id);

    /**
     * 新增应用
     *
     * @param app 应用
     * @return 结果
     * @throws Exception
     */
    public int insertApp(App app) throws Exception;

    /**
     * 更新应用
     *
     * @param app 应用
     * @return 结果
     * @throws Exception
     */
    public int updateApp(App app) throws Exception;

    /**
     * 根据 id 删除应用
     *
     * @param ids 主键ID
     * @return 结果
     */
    public int deleteAppByIds(Long... ids);

    /**
     * 根据主键ID更新应用状态为已认证
     *
     * @param id 主键ID
     * @return 结果
     */
    public int openApp(Long id);

    /**
     * 根据主键ID更新应用状态为失效
     *
     * @param id 主键ID
     * @return 结果
     */
    public int closeApp(Long id);

    /**
     * 获取所有的 App 信息
     *
     * @return 所有 App
     */
    public List<App> selectAppAll();

}
