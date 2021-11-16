package cn.cy.server.web.sys.entity;

import cn.cy.mybatis.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * @author: 开水白菜
 * @description: 用户信息
 * @create: 2021-10-08 22:30
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Alias("User")
public class User extends BaseEntity {

    private static final long serialVersionUID = -6857823954684193112L;

    private String id;

    private String userId;

    private String username;

    private String phone;

    private String email;

    /**
     * 用户状态（1 可用，0 注销）
     */
    private Integer status;

    private String password;

}


