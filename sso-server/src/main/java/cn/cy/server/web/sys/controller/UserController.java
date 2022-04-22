package cn.cy.server.web.sys.controller;

import cn.cy.log.Log;
import cn.cy.log.OperationType;
import cn.cy.mybatis.web.controller.BaseController;
import cn.cy.mybatis.web.page.TableDataInfo;
import cn.cy.server.web.sys.entity.User;
import cn.cy.server.web.sys.service.IUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: 开水白菜
 * @description: 用户信息Controller
 * @create: 2021-10-08 22:17
 **/
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    @Log(success = "分页查询用户列表", error = "分页查询用户列表失败", operation = OperationType.SEARCH)
    public TableDataInfo<User> page(User info) {
        startPage();
        List<User> list = userService.selectUserList(info);
        return getDataTable(list);
    }

    /**
     * 添加用户
     */
    @PostMapping("/add")
    @Log(success = "添加用户成功，用户信息：#{info}", error = "添加用户失败，用户信息：#{info}", operation = OperationType.INSERT)
    public int add(@RequestBody User info) {
        return userService.insertUser(info);
    }

    /**
     * 更新用户
     */
    @PutMapping("/update")
    @Log(success = "更新用户成功，用户信息：#{info}", error = "更新用户失败，用户信息：#{info}", operation = OperationType.UPDATE)
    public int update(@RequestBody User info) {
        return userService.updateUser(info);
    }

    /**
     * 开户
     */
    @PutMapping("/open")
    @Log(success = "开启账户成功，开户 userId：#{userId}", error = "开启账户失败，开户 userId：#{userId}", operation = OperationType.UPDATE)
    public int openAccount(@Validated @NotNull(message = "参数不能为空") String userId) {
        return userService.openAccount(userId);
    }

    /**
     * 关闭账户
     */
    @PutMapping("/close")
    @Log(success = "关闭账户成功，开户 userId：#{userId}", error = "关闭账户失败，开户 userId：#{userId}", operation = OperationType.UPDATE)
    public int closeAccount(@Validated @NotNull(message = "参数不能为空") String userId) {
        return userService.closeAccount(userId);
    }

    /**
     * 重置密码
     */
    @PutMapping("/reset")
    @Log(success = "重置密码成功，开户 userId：#{userId}", error = "重置密码失败，开户 userId：#{userId}", operation = OperationType.UPDATE)
    public int resetPassword(@Validated @NotNull(message = "参数不能为空") String userId) {
        return userService.resetPassword(userId);
    }

    /**
     * 查看密码
     */
    @GetMapping("/show")
    @Log(success = "查看用户密码，用户 userId：#{userId}", error = "查看用户密码失败，用户 userId：#{userId}", operation = OperationType.SEARCH)
    public String showPassword(@Validated @NotNull(message = "参数不能为空") String userId) {
        return userService.selectUserPasswordByUserId(userId);
    }
}
