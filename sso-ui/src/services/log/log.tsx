import requests from '@/utils/RequestUtil';
import { OperationLogList } from '@/services/log/types';


/**
 * 分页获取日志信息
 * @param params 参数
 * @param options 配置
 * @return 日志列表
 */
export async function operationLogPage(params: API.PageParams, options?: { [key: string]: any }) {
  return requests<OperationLogList>('/api/log/operation/page', {
    method: 'GET',
    params,
    ...(options || {}),
  });
}

/**
 * 分页查询审计日志
 * @param operationId
 */
export async function auditLogList(operationId: number) {
  return requests<API.Result>(`/api/log/audit/list/${operationId}`, {
    method: 'GET',
  });
}
