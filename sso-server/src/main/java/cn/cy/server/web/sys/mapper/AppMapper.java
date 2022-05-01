package cn.cy.server.web.sys.mapper;

import cn.cy.server.web.sys.entity.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 开水白菜
 * @description: 应用Mapper
 * @create: 2022-02-22 21:11
 **/
@Mapper
@Repository
public interface AppMapper {
    /**
     * 查询应用列表
     *
     * @param app 应用参数
     * @return 列表
     */
    public List<App> selectAppList(App app);

    /**
     * 根据主键ID查询应用
     *
     * @param id 主键ID
     * @return 应用
     */
    public App selectAppById(Long id);

    /**
     * 获取所有 App 信息
     *
     * @return 列表
     */
    public List<App> selectAppAll();

    /**
     * 新增应用
     *
     * @param app 应用
     * @return 结果
     */
    public int insertApp(App app);

    /**
     * 更新应用
     *
     * @param app 应用
     * @return 结果
     */
    public int updateApp(App app);

    /**
     * 根据ID更新应用状态
     *
     * @param id     主键ID
     * @param status 状态
     * @return
     */
    public int updateStatusById(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 根据 id 删除应用
     *
     * @param ids 主键ID
     * @return 结果
     */
    public int deleteAppByIds(Long... ids);

}
