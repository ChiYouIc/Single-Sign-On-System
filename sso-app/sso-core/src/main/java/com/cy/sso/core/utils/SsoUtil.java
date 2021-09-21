package com.cy.sso.core.utils;

import com.cy.sso.core.model.SsoUser;

/**
 * @author: 开水白菜
 * @description: 单点工具；使用 ThreadLocal 将当前线程用户信息保存
 * @create: 2021-09-14 22:00
 **/
public class SsoUtil {

    private static final ThreadLocal<SsoUser> contextHolder = new ThreadLocal();

    public static SsoUser getInfo() {
        return contextHolder.get();
    }

    public static void setInfo(SsoUser ssoUser) {
        contextHolder.set(ssoUser);
    }

    public static void remove() {
        contextHolder.remove();
    }

}
