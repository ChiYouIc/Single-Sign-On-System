package cn.cy.server.web.sys.service.impl;

import cn.cy.server.config.properties.SsoProperties;
import cn.cy.server.util.EncryptUtils;
import cn.cy.server.web.sys.entity.User;
import cn.cy.server.web.sys.mapper.UserMapper;
import cn.cy.server.web.sys.service.IUserService;
import cn.cy.sso.utils.UserUtil;
import cn.cy.web.exception.ApiAssert;
import cn.cy.web.exception.ApiException;
import cn.cy.web.response.ErrorCodeEnum;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    @Resource
    private SsoProperties ssoProperties;

    @Override
    public List<User> selectUserList(User info) {
        return userMapper.selectUserList(info);
    }

    @Override
    public String selectUserPasswordByUserId(String userId) {
        try {
            String password = userMapper.selectUserPasswordByUserId(userId);
            return new String(EncryptUtils.desDecode(Base64.decode(password)));
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            throw new ApiException(ErrorCodeEnum.BAD_REQUEST.overrideMsg("密码解密失败."));
        }
    }

    @Override
    public int insertUser(User info) {
        try {
            info.setCreatedBy(UserUtil.id());
            // 没有采用 md5 摘要是为了可以查看密码原文^_^
            info.setPassword(Base64.encode(EncryptUtils.desEncode(ssoProperties.getOriginPassword().getBytes(StandardCharsets.UTF_8))));
            return userMapper.insertUser(info);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            throw new ApiException(ErrorCodeEnum.BAD_REQUEST.overrideMsg("创建用户失败."));
        }
    }

    @Override
    public int updateUser(User info) {
        ApiAssert.notNull(ErrorCodeEnum.BAD_REQUEST.overrideMsg("缺少主键ID或用户ID."), info.getId(), info.getUserId());
        info.setUpdatedBy(UserUtil.id());
        int row = userMapper.updateUser(info);
        ApiAssert.isFalse(ErrorCodeEnum.BAD_SQL_UPDATE, row == 0);
        return row;
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
        try {
            // 没有采用 md5 摘要是为了可以查看密码原文^_^
            return userMapper.resetPassword(userId, Base64.encode(EncryptUtils.desEncode(ssoProperties.getOriginPassword().getBytes(StandardCharsets.UTF_8))));
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            throw new ApiException(ErrorCodeEnum.BAD_REQUEST.overrideMsg("重置密码失败."));
        }
    }
}
