package com.cy.sso.core.config.properties;

/**
 * @author: 开水白菜
 * @description: 当前服务类型（sso客户端 or sso服务端）
 * @create: 2021-08-17 23:04
 **/
public enum ServerType {

    /**
     * 服务端 or 客户端
     */
    SERVER("server"),
    CLIENT("client");

    public final String name;

    ServerType(final String name) {
        this.name = name;
    }
}
