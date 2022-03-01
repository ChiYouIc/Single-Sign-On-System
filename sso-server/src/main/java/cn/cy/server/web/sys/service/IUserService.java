package cn.cy.server.web.sys.service;

import cn.cy.server.web.sys.entity.User;

import java.util.List;

/**
 * @author: 开水白菜
 * @description: 用户信息Service接口
 * @create: 2021-10-08 22:57
 **/
public interface IUserService {
    /**
     * 查询用户信息
     *
     * @param info 用户信息
     * @return 用户列表
     */
    public List<User> selectUserList(User info);

    /**
     * 根据用户ID查询用户密码
     *
     * @param userId 用户ID
     * @return 密码
     */
    public String selectUserPasswordByUserId(String userId);

    /**
     * 新增用户信息
     *
     * @param info 用户信息
     * @return 结果
     */
    public int insertUser(User info);

    /**
     * 更新用户信息
     *
     * @param info 用户信息
     * @return 结果
     */
    public int updateUser(User info);

    /**
     * 开启账户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int openAccount(String userId);

    /**
     * 关闭账户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int closeAccount(String userId);

    /**
     * 重置密码
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int resetPassword(String userId);
}
