package cn.cy.server.web.sys.service.impl;

import cn.cy.log.LogUtils;
import cn.cy.server.util.EncryptUtils;
import cn.cy.server.web.sys.entity.App;
import cn.cy.server.web.sys.mapper.AppMapper;
import cn.cy.server.web.sys.service.IAppService;
import cn.hutool.json.JSONUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: 开水白菜
 * @description: 应用Service实现
 * @create: 2022-02-22 21:10
 **/
@Slf4j
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
    public List<App> selectAppAll() {
        return appMapper.selectAppAll();
    }

    @Override
    public int insertApp(App app) throws Exception {
        String appName = app.getAppName();
        byte[] desEncode = EncryptUtils.desEncode(appName.getBytes(StandardCharsets.UTF_8));
        String appCode = Base64.encode(desEncode);
        app.setAppCode(appCode);
        LogUtils.info(log, "新增应用，应用名称：【{}】，应用 appCode：【{}】", appName, appCode);
        return appMapper.insertApp(app);
    }

    @Override
    public int updateApp(App app) throws Exception {
        App oldApp = this.selectAppById(app.getId());
        String appName = oldApp.getAppName();
        LogUtils.info(log, "修改前应用信息：{}", JSONUtil.toJsonStr(oldApp));
        if (app.getAppName() != null && !app.getAppName().equals(appName)) {
            byte[] desEncode = EncryptUtils.desEncode(appName.getBytes(StandardCharsets.UTF_8));
            String appCode = Base64.encode(desEncode);
            app.setAppCode(appCode);
            LogUtils.warn(log, "应用 appCode 发生改变，由 【{}】 变为 【{}】", oldApp.getAppCode(), appCode);
        }
        LogUtils.info(log, "修改后应用信息为：{}", JSONUtil.toJsonStr(app));
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
