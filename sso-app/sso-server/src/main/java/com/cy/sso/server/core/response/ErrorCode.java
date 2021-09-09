package com.cy.sso.server.core.response;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/4 15:09
 * @Description: 异常消息状态码
 */

@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ErrorCode {
    /**
     * http 状态码
     */
    @Builder.Default
    private int code = 200;
    /**
     * 错误描述
     */
    private String msg;
    /**
     * 路径
     */
    private String path;
    /**
     * 异常信息
     */
    private String exception;

}
