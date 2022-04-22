package cn.cy.server.web.log.service.impl;

import cn.cy.log.bo.AuditLog;
import cn.cy.server.web.log.mapper.AuditLogMapper;
import cn.cy.server.web.log.service.IAuditLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/4/22 15:33
 * @Description: 设计日志 Service 实现
 */
@Service
public class AuditLogServiceImpl implements IAuditLogService {

    @Resource
    private AuditLogMapper auditLogMapper;

    @Override
    public List<AuditLog> selectAuditLogListByOperationId(Long operationId) {
        return auditLogMapper.selectAuditLogListByOperationId(operationId);
    }
}
