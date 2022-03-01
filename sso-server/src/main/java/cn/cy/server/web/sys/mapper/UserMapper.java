package cn.cy.server.web.sys.mapper;

import cn.cy.server.web.sys.entity.User;
import cn.cy.server.web.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 开水白菜
 * @description: 用户信息Mapper
 * @create: 2021-10-08 22:57
 **/
@Mapper
@Repository
public interface UserMapper {

    /**
     * 查询用户信息列表
     *
     * @param infoParam 查询条件
     * @return 用户信息
     */
    public List<User> selectUserList(User infoParam);

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
     * @param info 用户信息
     * @return 结果
     */
    public int updateUser(User info);

    /**
     * 根据用户ID更新用户状态
     * @param userId 用户ID
     * @param status 状态
     * @return 结果
     */
    public int updateStatusByUserId(@Param("userId") String userId, @Param("status") int status);

    /**
     * 重置密码
     * @param userId 用户ID
     * @param password 密码
     * @return 结果
     */
    public int resetPassword(@Param("userId") String userId, @Param("password") String password);
}
