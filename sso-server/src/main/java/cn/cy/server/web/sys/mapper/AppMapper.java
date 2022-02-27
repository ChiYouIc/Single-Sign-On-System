package cn.cy.server.web.sys.mapper;

import cn.cy.server.web.sys.entity.App;
import org.apache.ibatis.annotations.Mapper;
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
     * 根据主键ID更新应用状态为已认证
     *
     * @param id 主键ID
     * @return 结果
     */
    public int updateAppOpen(Long id);

    /**
     * 根据主键ID更新应用状态为失效
     *
     * @param id 主键ID
     * @return 结果
     */
    public int updateAppClose(Long id);

    /**
     * 根据 id 删除应用
     *
     * @param ids 主键ID
     * @return 结果
     */
    public int deleteAppByIds(Long... ids);
}
