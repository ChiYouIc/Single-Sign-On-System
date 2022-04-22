package cn.cy.server.web.log.mapper;

import cn.cy.log.bo.AuditLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/4/22 11:49
 * @Description: 审计日志 Mapper
 */
@Mapper
@Repository
public interface AuditLogMapper {

    /**
     * 新增审计日志列表
     *
     * @param list 审计日志列表
     * @return 结果
     */
    public int insertAuditLogList(List<AuditLog> list);

    /**
     * 根据 operationId 查询审计日志列表
     *
     * @param operationId 操作日志ID
     * @return 审计日志列表
     */
    public List<AuditLog> selectAuditLogListByOperationId(Long operationId);
}
