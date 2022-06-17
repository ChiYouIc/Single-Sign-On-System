package cn.cy.server.web.sys.service;

import cn.cy.sso.model.RequestPath;

import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/6/17 11:25
 * @Description: 应用请求路径Service
 */
public interface IAppRequestPathService {
    /**
     * 新增应用请求路径信息
     *
     * @param appCode  应用码
     * @param pathList 列表
     * @return 结果
     */
    public int insertAppRequestPath(String appCode, List<RequestPath> pathList);
}
