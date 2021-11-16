package cn.cy.server.web.sso.mapper;

import cn.cy.server.web.sso.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: 开水白菜
 * @description: 用户信息Mapper
 * @create: 2021-08-08 15:36
 **/
@Mapper
@Repository
public interface UserInfoMapper {

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    public UserInfo selectUserInfo(String userId);

}
