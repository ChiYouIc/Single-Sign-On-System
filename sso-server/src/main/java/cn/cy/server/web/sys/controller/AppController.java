package cn.cy.server.web.sys.controller;

import cn.cy.mybatis.web.controller.BaseController;
import cn.cy.mybatis.web.page.TableDataInfo;
import cn.cy.server.web.sys.entity.App;
import cn.cy.server.web.sys.service.IAppService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: 开水白菜
 * @description: 应用列表Controller
 * @create: 2022-02-22 20:59
 **/
@RestController
@RequestMapping("/sys/app")
public class AppController extends BaseController {

    @Resource
    public IAppService appService;

    /**
     * 分页查询应用列表
     */
    @GetMapping("/page")
    public TableDataInfo<App> page(App app) {
        startPage();
        List<App> appList = appService.selectAppList(app);
        return getDataTable(appList);
    }

    /**
     * 添加应用
     */
    @PostMapping("/add")
    public int add(@RequestBody App app) {
        return appService.insertApp(app);
    }

    /**
     * 更新应用
     */
    @PutMapping("/update")
    public int update(@RequestBody App app) {
        return appService.updateApp(app);
    }

    /**
     * 开启应用
     */
    @PutMapping("/open")
    public int openApp(@Validated @NotNull(message = "参数不能为空") Long id) {
        return appService.updateAppOpen(id);
    }

    /**
     * 关闭应用
     */
    @PutMapping("/close")
    public int closeApp(@Validated @NotNull(message = "参数不能为空") Long id) {
        return appService.updateAppClose(id);
    }

}
