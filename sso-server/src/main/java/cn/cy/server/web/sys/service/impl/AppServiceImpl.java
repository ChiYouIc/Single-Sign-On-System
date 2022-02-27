package cn.cy.server.web.sys.service.impl;

import cn.cy.server.util.EncryptUtils;
import cn.cy.server.web.sys.entity.App;
import cn.cy.server.web.sys.mapper.AppMapper;
import cn.cy.server.web.sys.service.IAppService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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
    public App selectAppById(Long id) {
        return appMapper.selectAppById(id);
    }

    @Override
    public int insertApp(App app) throws Exception {
        String appName = app.getAppName();
        byte[] desEncode = EncryptUtils.desEncode(appName.getBytes(StandardCharsets.UTF_8));
        app.setAppCode(Base64.encode(desEncode));
        return appMapper.insertApp(app);
    }

    @Override
    public int updateApp(App app) throws Exception {
        App oldApp = this.selectAppById(app.getId());
        String appName = oldApp.getAppName();
        if (app.getAppName() != null && !app.getAppName().equals(appName)) {
            byte[] desEncode = EncryptUtils.desEncode(appName.getBytes(StandardCharsets.UTF_8));
            app.setAppCode(Base64.encode(desEncode));
        }
        return appMapper.updateApp(app);
    }

    @Override
    public int openApp(Long id) {
        return appMapper.updateStatusById(id, 1);
    }

    @Override
    public int closeApp(Long id) {
        return appMapper.updateStatusById(id, 0);
    }

    @Override
    public int deleteAppByIds(Long... ids) {
        return appMapper.deleteAppByIds(ids);
    }
}
