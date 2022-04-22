package cn.cy.server.web.log.mapper;

import cn.cy.log.bo.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 友
 * @Date: 2022/4/22 11:48
 * @Description: 操作日志Mapper
 */
@Mapper
@Repository
public interface OperationLogMapper {
    /**
     * 新增操作日志
     *
     * @param log 日志
     * @return 结果
     */
    public int insertOperationLog(OperationLog log);


    /**
     * 查询操作日志列表
     *
     * @param log 参数
     * @return 操作日志列表
     */
    public List<OperationLog> selectOperationLogList(OperationLog log);
}
