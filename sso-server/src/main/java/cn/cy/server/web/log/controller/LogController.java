package cn.cy.server.web.log.controller;

import cn.cy.log.bo.AuditLog;
import cn.cy.log.bo.OperationLog;
import cn.cy.mybatis.web.controller.BaseController;
import cn.cy.mybatis.web.page.TableDataInfo;
import cn.cy.server.web.log.service.IAuditLogService;
import cn.cy.server.web.log.service.IOperationLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/4/22 11:43
 * @Description: 操作日志 controller
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    @Resource
    private IOperationLogService operationLogService;

    @Resource
    private IAuditLogService auditLogService;

    /**
     * 分页查询操作日志
     */
    @GetMapping("/operation/page")
    public TableDataInfo<OperationLog> page(OperationLog log) {
        startPage();
        List<OperationLog> list = operationLogService.selectOperationLogList(log);
        return getDataTable(list);
    }

    /**
     * 分页查询操作日志
     */
    @GetMapping("/audit/list/{operationId}")
    public TableDataInfo<AuditLog> getAuditLogList(@PathVariable("operationId") Long operationId) {
        startPage();
        List<AuditLog> list = auditLogService.selectAuditLogListByOperationId(operationId);
        return getDataTable(list);
    }

}
