package cn.cy.server.web.log.service.impl;

import cn.cy.log.bo.OperationLog;
import cn.cy.server.web.log.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: you
 * @date: 2022-04-21 21:37
 * @description: log 业务接口实现类
 */
@Slf4j
@Service
public class LogServiceImpl implements ILogService {

    @Override
    public void record(OperationLog operationLog) {
        log.info(String.valueOf(operationLog));
    }

}
