package cn.cy.server.log;

import cn.cy.log.bo.OperationLog;
import cn.cy.server.web.log.service.IOperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/4/22 11:59
 * @Description:
 */
@Slf4j
@SpringBootTest
public class OperationServiceTest {

    @Resource
    private IOperationLogService operationLogService;

    @Test
    public void selectOperationLogList() {
        List<OperationLog> logList = operationLogService.selectOperationLogList(new OperationLog());
        for (OperationLog operationLog : logList) {
            log.info(operationLog.toString());
        }
    }

}
