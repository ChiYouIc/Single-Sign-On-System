package cn.cy.server.web.sys.entity;

import cn.cy.mybatis.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * @author: 开水白菜
 * @description: 应用实体类
 * @create: 2022-02-22 21:08
 **/
@Setter
@Getter
@ToString
@Alias("App")
@Accessors(chain = true)
public class App extends BaseEntity {
    private Long id;

    private String appCode;

    private String appName;

    private Integer sort;

    private Integer status;
}
