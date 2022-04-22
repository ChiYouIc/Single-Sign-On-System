package cn.cy.server.web.sys.controller;

import cn.cy.log.Log;
import cn.cy.log.bo.OperationType;
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
    @Log(success = "分页查询应用列表", error = "分页查询应用列表失败", operation = OperationType.SEARCH)
    public TableDataInfo<App> page(App app) {
        startPage();
        List<App> appList = appService.selectAppList(app);
        return getDataTable(appList);
    }

    /**
     * 添加应用
     */
    @PostMapping("/add")
    @Log(success = "添加应用：{#app.toString}", error = "添加应用失败，应用信息 {#app.toString}", operation = OperationType.INSERT)
    public int add(@RequestBody App app) throws Exception {
        return appService.insertApp(app);
    }

    /**
     * 更新应用
     */
    @PutMapping("/update")
    @Log(success = "更新应用成功，应用信息：{#app.toString}", error = "更新应用失败，应用信息 {#app.toString}", operation = OperationType.UPDATE)
    public int update(@RequestBody App app) throws Exception {
        return appService.updateApp(app);
    }

    /**
     * 开启应用
     */
    @PutMapping("/open")
    @Log(success = "开启应用成功，应用 id：{#id}", error = "开启应用失败，应用 id：{#id}", operation = OperationType.UPDATE)
    public int openApp(@Validated @NotNull(message = "参数不能为空") Long id) {
        return appService.openApp(id);
    }

    /**
     * 关闭应用
     */
    @PutMapping("/close")
    @Log(success = "关闭应用成功，应用 id：{#id}", error = "关闭应用失败，应用 id：{#id}", operation = OperationType.UPDATE)
    public int closeApp(@Validated @NotNull(message = "参数不能为空") Long id) {
        return appService.closeApp(id);
    }

}
