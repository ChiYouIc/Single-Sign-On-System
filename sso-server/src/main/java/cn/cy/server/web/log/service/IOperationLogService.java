package cn.cy.server.web.log.service;

import cn.cy.log.bo.OperationLog;
import cn.cy.log.service.ILogRecordService;

import java.util.List;

/**
 * @author: you
 * @date: 2022-04-21 21:36
 * @description: Log 业务
 */
public interface IOperationLogService extends ILogRecordService {

    /**
     * 查询操作日志
     *
     * @param log 参数
     * @return 操作日志列表
     */
    public List<OperationLog> selectOperationLogList(OperationLog log);
}
