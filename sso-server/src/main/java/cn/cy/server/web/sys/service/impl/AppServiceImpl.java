package cn.cy.server.web.sys.service.impl;

import cn.cy.server.web.sys.entity.App;
import cn.cy.server.web.sys.mapper.AppMapper;
import cn.cy.server.web.sys.service.IAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 开水白菜
 * @description: 应用Service实现
 * @create: 2022-02-22 21:10
 **/
@Service
public class AppServiceImpl implements IAppService {

    @Resource
    public AppMapper appMapper;

    @Override
    public List<App> selectAppList(App app) {
        return appMapper.selectAppList(app);
    }

    @Override
    public int insertApp(App app) {
        // 创建应用码
        return appMapper.insertApp(app);
    }

    @Override
    public int updateApp(App app) {
        return appMapper.updateApp(app);
    }

    @Override
    public int updateAppOpen(Long id) {
        return appMapper.updateAppOpen(id);
    }

    @Override
    public int updateAppClose(Long id) {
        return appMapper.updateAppClose(id);
    }

    @Override
    public int deleteAppByIds(Long... ids) {
        return appMapper.deleteAppByIds(ids);
    }
}
