package cn.cy.server.web.sso.entity;

import cn.cy.sso.model.RequestPath;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @Author: 友
 * @Date: 2022/6/17 11:05
 * @Description: 请求路径信息
 */
@Getter
@Setter
@ToString
@Alias("AppRequestPathInfo")
public class RequestPathInfo extends RequestPath {

    private Long id;

    private Long appId;

    private LocalDateTime createTime;

    private Integer version;

    private Integer status;

}
