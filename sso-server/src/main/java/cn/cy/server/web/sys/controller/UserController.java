package cn.cy.server.web.sys.controller;

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
    public TableDataInfo<User> page(User info) {
        startPage();
        List<User> list = userService.selectUserList(info);
        return getDataTable(list);
    }

    /**
     * 添加用户
     */
    @PostMapping("/add")
    public int add(@RequestBody User info) {
        return userService.insertUser(info);
    }

    @PutMapping("/update")
    public int update(@RequestBody User info) {
        return userService.updateUser(info);
    }

    /**
     * 开户
     */
    @PutMapping("/open")
    public int openAccount(@Validated @NotNull(message = "参数不能为空") String userId) {
        return userService.openAccount(userId);
    }

    /**
     * 关闭账户
     */
    @PutMapping("/close")
    public int closeAccount(@Validated @NotNull(message = "参数不能为空") String userId) {
        return userService.closeAccount(userId);
    }

    /**
     * 重置密码
     */
    @PutMapping("/reset")
    public int resetPassword(@Validated @NotNull(message = "参数不能为空") String userId) {
        return userService.resetPassword(userId);
    }

    /**
     * 查看密码
     */
    @GetMapping("/show")
    public String showPassword(@Validated @NotNull(message = "参数不能为空") String userId){
        return userService.selectUserPasswordByUserId(userId);
    }
}
