import type {RefObject} from "react";
import React from "react";
import type {AppListItem} from "@/services/app/types";
import type {ActionType, ProColumns} from "@ant-design/pro-table";
import ProTable from '@ant-design/pro-table';
import UpdateForm from "@/pages/AppList/components/UpdateForm";
import {PageContainer} from "@ant-design/pro-layout";
import {addApp, appPage, updateApp, updateAppClose, updateAppOpen} from "@/services/app/app";
import {Button, message} from "antd";
import {PlusOutlined} from "@ant-design/icons";

export type AppListState = {
  visible: boolean,
  initForm?: AppListItem
}

class AppList extends React.Component<any, AppListState> {

  public columns: ProColumns<AppListItem>[] = [
    {
      title: '序号',
      dataIndex: 'index',
      valueType: 'indexBorder',
      width: 48,
    },
    {
      title: '应用码',
      dataIndex: 'appCode',
    },
    {
      title: '应用名称',
      dataIndex: 'appName',
    },
    {
      title: '排序',
      dataIndex: 'sort',
      search: false,
    },
    {
      title: '状态',
      dataIndex: 'status',
      valueType: 'select',
      initialValue: undefined,
      valueEnum: {
        1: {
          text: '已认证',
          status: 'Processing'
        },
        0: {
          text: '未认证',
          status: 'Default'
        }
      }
    },
    {
      title: '操作',
      key: 'option',
      width: 180,
      valueType: 'option',
      render: (_, entity) => {
        let node;

        if (entity.status === 1) {
          node = <a key='close' onClick={() => this.closeApp(entity.id)}>失效</a>
        } else {
          node = <a key='open' onClick={() => this.openApp(entity.id)}>生效</a>
        }

        return [
          node,
          <a key='update' onClick={() => this.updateApp(entity)}>编辑</a>
        ]
      }
    }
  ];

  public actionRef: ActionType | undefined;

  public updateFormRef: RefObject<UpdateForm> = React.createRef<UpdateForm>();

  public constructor(props: any, context: any) {
    super(props, context);
    this.state = {visible: false};
  }

  closeApp(id: number) {
    updateAppClose(id).then(() => {
      this.actionRef?.reload()
      return message.success("关闭成功.")
    })
  }

  openApp(id: number) {
    updateAppOpen(id).then(() => {
      this.actionRef?.reload()
      return message.success("开启成功.")
    })
  }

  /**
   * 开启添加用户窗口
   * @param {boolean} visible 可见性
   * @param {AppListItem} initForm 表单数据
   */
  setDrawerVisible(visible: boolean, initForm?: AppListItem) {
    this.setState({visible, initForm});
  }

  /**
   * 添加用户完成
   * @param {AppListItem} app 用户信息
   */
  onFinish(app: AppListItem) {

    if (app.id) {
      updateApp(app).then(() => {
        this.actionRef?.reload();
        this.setDrawerVisible(false);
        return message.success('更新成功!');
      })
    }
    // 新增
    else {
      addApp(app).then(() => {
        // 意思：告诉编译器，this.actionRef 一定存在，不会为 null 或者 undefined，也就是表取反
        this.actionRef!.reload();
        this.setDrawerVisible(false);
        return message.success('添加成功!');
      });
    }

  }

  updateApp(app: AppListItem) {
    this.setDrawerVisible(true, app)
  }

  render() {
    return (
      <PageContainer>
        <ProTable<AppListItem, API.PageParams>
          columns={this.columns}
          actionRef={(ref) => this.actionRef = ref}
          request={appPage}
          rowKey="id"
          search={{labelWidth: 'auto'}}
          form={{
            // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
            syncToUrl: (values, type) => {
              if (type === 'get') {
                return {
                  ...values,
                  created_at: [values.startTime, values.endTime],
                };
              }
              return values;
            },
          }}
          pagination={{pageSize: 5}}
          dateFormatter="string"
          headerTitle="应用列表"
          toolBarRender={() => [
            <Button key="button" icon={<PlusOutlined/>} type="primary" onClick={() => this.setDrawerVisible(true)}>
              新建
            </Button>
          ]}
        />
        {this.state.visible && <UpdateForm
          ref={this.updateFormRef}
          visible={this.state.visible}
          initForm={this.state.initForm}
          onClose={() => this.setDrawerVisible(false)}
          onFinish={(values: AppListItem) => this.onFinish(values)}/>}
      </PageContainer>
    );
  }
}

export default AppList;
