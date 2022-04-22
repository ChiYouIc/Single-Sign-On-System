export type OperationLogItem = {
  id: number;
  traceId: string;
  operator: object;
  method: string;
  params: string;
  result: string;
  description: string;
  operation: string;
  operationStatus: string;
  throwable?: string;
  operationTime: string;
  auditLogList?: AuditLogItem[];
}

export type AuditLogItem = {
  id: number;
  operationId: number;
  traceId: string;
  auditContent: string;
  operationTime: string;
  level: string;
}

export type OperationLogList = {
  data?: OperationLogItem[];
  total?: number;
  success?: boolean;
}

export type AuditLogList = {
  data?: AuditLogItem[];
  total?: number;
  success?: boolean;
}
