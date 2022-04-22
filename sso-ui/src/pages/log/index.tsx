import React from 'react';
import ProTable, { ActionType, ProColumns } from '@ant-design/pro-table';
import { OperationLogItem } from '@/services/log/types';
import { PageContainer } from '@ant-design/pro-layout';
import { auditLogList, operationLogPage } from '@/services/log/log';
import { Tag } from 'antd';
import ShowAuditLog from '@/pages/log/components/ShowAuditLog';


export type OperationLogState = {
  visible: boolean;
  initForm?: any,
}


class LogList extends React.Component<any, OperationLogState> {
  public columns: ProColumns<OperationLogItem>[] = [
    {
      title: '序号',
      dataIndex: 'index',
      valueType: 'indexBorder',
      width: 48,
    },
    {
      title: 'traceId',
      dataIndex: 'traceId',
      ellipsis: true,
    },
    {
      title: '执行方法',
      dataIndex: 'method',
      search: false,
    },
    {
      title: '执行参数',
      dataIndex: 'params',
      search: false,
      ellipsis: true,
    },
    {
      title: '返回结果',
      dataIndex: 'result',
      search: false,
      ellipsis: true,
    },
    {
      title: '日志描述',
      dataIndex: 'description',
      search: false,
      ellipsis: true,
    },
    {
      title: '操作类型',
      dataIndex: 'operation',
      ellipsis: true,
      valueType: 'select',
      valueEnum: {
        'SEARCH': '查询数据',
        'INSERT': '新增数据',
        'UPDATE': '更新数据',
        'DELETE': '删除数据',
        'IMPORT': '导入数据',
        'EXPORT': '导出数据',
      },
    },
    {
      title: '操作状态',
      dataIndex: 'operationStatus',
      valueType: 'select',
      valueEnum: {
        'SUCCESS': '成功',
        'ERROR': '失败',
      },
      render: (_, { operationStatus }) => {
        let node;
        if (operationStatus === 'SUCCESS') {
          node = <Tag color='#87d068'>成功</Tag>;
        } else {
          node = <Tag color='#f50'>失败</Tag>;
        }
        return node;
      },
    },
    {
      title: '异常消息',
      dataIndex: 'throwable',
      search: false,
      ellipsis: true,
    },
    {
      title: '操作时间',
      dataIndex: 'operationTime',
      valueType: 'dateTime',
    },
    {
      title: '操作',
      key: 'option',
      width: 90,
      valueType: 'option',
      render: (_, entity) => {
        return <a key='audit' onClick={() => this.showAuditLog(entity.id)}>审计日志</a>;
      },
    },
  ];

  public actionRef: ActionType | undefined;


  constructor(props: any, context: any) {
    super(props, context);
    this.state = { visible: false };
  }

  /**
   * 显示审计日志
   * @param operationId 操作日志ID
   */
  public showAuditLog(operationId: number) {
    auditLogList(operationId).then(r => {
      this.setState({ visible: true, initForm: r.data });

    });
  }

  public closeAuditLog() {
    this.setState({ visible: false, initForm: [] });
  }

  render() {
    return (
      <PageContainer>
        <ProTable<OperationLogItem, API.PageParams>
          columns={this.columns}
          actionRef={(ref: ActionType | undefined) => this.actionRef = ref}
          request={operationLogPage}
          rowKey='id'
          search={{ labelWidth: 'auto' }}
          pagination={{ pageSize: 10 }}
          dateFormatter='string'
          headerTitle='日志列表'
        />
        <ShowAuditLog visible={this.state.visible} initForm={this.state.initForm} onClose={() => this.closeAuditLog()} />
      </PageContainer>
    );
  }
}


export default LogList;
