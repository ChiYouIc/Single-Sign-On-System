package cn.cy.server.web.sso.service.impl;

import cn.cy.server.cache.IUserCacheService;
import cn.cy.server.core.JwtHelper;
import cn.cy.server.util.EncryptUtils;
import cn.cy.server.web.sso.entity.LoginParam;
import cn.cy.server.web.sso.entity.UserInfo;
import cn.cy.server.web.sso.mapper.UserInfoMapper;
import cn.cy.server.web.sso.service.ISsoService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 单点登录验证业务 Service 实现
 *
 * @author: 开水白菜
 * @description: Sso 业务 Service
 * @create: 2021-08-08 15:29
 **/
@Service
public class SsoServiceImpl implements ISsoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private IUserCacheService userCacheService;

    @Override
    public boolean authentication(LoginParam param) {
        try {
            UserInfo info = userInfoMapper.selectUserInfo(param.getUsername());
            byte[] desEncode = EncryptUtils.desEncode(param.getPassword().getBytes(StandardCharsets.UTF_8));
            return ObjectUtil.isNotEmpty(info) && StrUtil.equals(Base64.encode(desEncode), info.getPassword());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String generateToken(LoginParam param, HttpSession session) {

        UserInfo info = userInfoMapper.selectUserInfo(param.getUsername());
        // 构建 Claims
        DefaultClaims claims = new DefaultClaims();
        claims.put("userId", info.getUserId());
        claims.put("id", info.getId());
        // 获取 token
        String token = JwtHelper.encode(claims);
        info.setToken(token);
        info.setAuthKey(session.getId());
        // 保存 sessionId - token
        userCacheService.setAuthKeyToken(session.getId(), token);
        userCacheService.setUserInfo(token, info);
        return generateCode(token);
    }

    @Override
    public String generateCode(String token) {
        String code = UUID.randomUUID().toString();
        userCacheService.setToken(code, token);
        return code;
    }
}
