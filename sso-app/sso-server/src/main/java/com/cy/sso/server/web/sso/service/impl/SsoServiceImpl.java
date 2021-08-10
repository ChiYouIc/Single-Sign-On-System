package com.cy.sso.server.web.sso.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cy.sso.server.web.sso.domain.UserInfo;
import com.cy.sso.server.web.sso.mapper.UserInfoMapper;
import com.cy.sso.server.web.sso.service.ISsoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: 开水白菜
 * @description: Sso 业务 Service
 * @create: 2021-08-08 15:29
 **/
@Service
public class SsoServiceImpl implements ISsoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean authentication(UserInfo userInfo) {
        UserInfo info = userInfoMapper.selectUserInfo(userInfo.getUsername());
        return ObjectUtil.isNotEmpty(info) && StrUtil.equals(userInfo.getPassword(), info.getPassword());
    }
}
