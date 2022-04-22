package cn.cy.server.web.log.mapper;

import cn.cy.log.bo.AuditLog;
import cn.cy.log.bo.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: you
 * @date: 2022-04-21 22:15
 * @description: Log mapper
 */
@Mapper
@Repository
public interface LogMapper {

    /**
     * 新增操作日志
     *
     * @param log 日志
     * @return 结果
     */
    public int insertOperationLog(OperationLog log);

    /**
     * 新增审计日志列表
     *
     * @param list 审计日志列表
     * @return 结果
     */
    public int insertAuditLogList(List<AuditLog> list);

}
