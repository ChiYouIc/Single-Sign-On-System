package cn.cy.server.web.log.service.impl;

import cn.cy.log.bo.AuditLog;
import cn.cy.log.bo.OperationLog;
import cn.cy.server.web.log.mapper.AuditLogMapper;
import cn.cy.server.web.log.mapper.OperationLogMapper;
import cn.cy.server.web.log.service.IOperationLogService;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: you
 * @date: 2022-04-21 21:37
 * @description: log 业务接口实现类
 */
@Slf4j
@Service
public class OperationLogServiceImpl implements IOperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    @Resource
    private AuditLogMapper auditLogMapper;

    @Override
    public void record(OperationLog operationLog) {
        operationLogMapper.insertOperationLog(operationLog);
        List<AuditLog> auditLogList = operationLog.getAuditLogList();
        if (CollUtil.isNotEmpty(auditLogList)) {
            auditLogList.forEach(o -> o.setOperationId(operationLog.getId()));
            auditLogMapper.insertAuditLogList(auditLogList);
        }
    }

    @Override
    public List<OperationLog> selectOperationLogList(OperationLog log) {
        return operationLogMapper.selectOperationLogList(log);
    }
}
