package cn.cy.server.web.sys.mapper;

import cn.cy.server.web.sso.entity.RequestPathInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/6/17 11:46
 * @Description: 应用请求路径Mapper
 */
@Mapper
@Repository
public interface AppRequestPathMapper {

    /**
     * 查询应用请求路径列表
     *
     * @param appId 应用ID
     * @return 应用请求路径列表
     */
    public List<RequestPathInfo> selectAllRequestPathByAppId(Long appId);


    /**
     * 批量新增应用路径信息
     *
     * @param pathInfos 请求路径信息
     * @return 新增结果
     */
    public int insertRequestPathBatch(List<RequestPathInfo> pathInfos);

    /**
     * 批量更新应用路径信息
     *
     * @param needDeleteList 应用路径信息列表
     * @return 更新行数
     */
    public int updateRequestPathBatch(List<RequestPathInfo> needDeleteList);

    /**
     * 根据主键IDs更新应用请求路径的status字段
     *
     * @param ids    主键IDs
     * @param status 状态
     * @return 更新行数
     */
    public int updateRequestPathStatusByIds(@Param("list") Object ids, @Param("status") Integer status);
}
