package com.cy.sso.core.model;

import java.util.StringJoiner;

/**
 * @author: 开水白菜
 * @description: Sso-Server 返回的数据格式
 * @create: 2021-08-15 23:41
 **/
public class SsoResult {
    private String result;

    private SsoUserInfo userInfo;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public SsoUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SsoUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isSuccess() {
        return "success".equals(this.result);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SsoResult.class.getSimpleName() + "[", "]")
                .add("result='" + result + "'")
                .add("userInfo=" + userInfo)
                .toString();
    }
}
