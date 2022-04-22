package cn.cy.server.log;

import cn.cy.log.OperationType;
import cn.cy.log.bo.*;
import cn.cy.server.web.log.service.IOperationLogService;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/4/22 10:13
 * @Description:
 */
@Slf4j
@SpringBootTest
public class LogServiceTest {

    @Autowired
    private IOperationLogService logService;

    @Test
    public void record() {
        Operator operator = new Operator("1", "池友");
        OperationLog log = new OperationLog();
        log.setTraceId("LogServiceTest.record")
                .setMethod("record")
                .setDescription("测试")
                .setOperationStatus(OperationStatus.SUCCESS)
                .setOperationTime(LocalDateTime.now())
                .setOperation(OperationType.SEARCH)
                .setParams(JSONUtil.toJsonStr(operator))
                .setOperator(operator)
                .setResult(JSONUtil.toJsonStr(operator))
                .setThrowable(JSONUtil.toJsonStr(new NullPointerException("空指针异常")));

        List<AuditLog> auditLogList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AuditLog auditLog = new AuditLog().setTraceId("LogServiceTest.record")
                    .setAuditContent("测试" + i)
                    .setOperationTime(LocalDateTime.now())
                    .setLevel(AuditLogLevel.INFO);

            auditLogList.add(auditLog);
        }

        log.setAuditLogList(auditLogList);

        logService.record(log);
    }

}
