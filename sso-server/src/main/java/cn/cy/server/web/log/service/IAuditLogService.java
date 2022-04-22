package cn.cy.server.web.log.service;

import cn.cy.log.bo.AuditLog;

import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/4/22 15:33
 * @Description: 审计日志 Service
 */
public interface IAuditLogService {
    /**
     * 根据 operationId 查询审计日志
     *
     * @param operationId 操作日志ID
     * @return 审计日志列表
     */
    public List<AuditLog> selectAuditLogListByOperationId(Long operationId);
}
