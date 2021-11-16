package cn.cy.server.web.sys.service.impl;

import cn.cy.server.web.sys.entity.User;
import cn.cy.server.web.sys.mapper.UserMapper;
import cn.cy.server.web.sys.service.IUserService;
import cn.cy.sso.model.SsoUser;
import cn.cy.sso.utils.SsoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 开水白菜
 * @description: 用户信息Service实现
 * @create: 2021-10-08 22:58
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectUserList(User info) {
        return userMapper.selectUserList(info);
    }

    @Override
    public int insertUser(User info) {
        SsoUser user = SsoUtil.getInfo();
        info.setCreatedBy(user.getId());
        info.setPassword("123456");
        return userMapper.insertUser(info);
    }

    @Override
    public int openAccount(String userId) {
        return userMapper.updateStatusByUserId(userId, 1);
    }

    @Override
    public int closeAccount(String userId) {
        return userMapper.updateStatusByUserId(userId, 0);
    }

    @Override
    public int resetPassword(String userId) {
        return userMapper.resetPassword(userId, "123456");
    }
}
