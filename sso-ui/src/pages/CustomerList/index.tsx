import React, {RefObject} from "react";
import {PlusOutlined} from '@ant-design/icons';
import {Button, message} from 'antd';
import type {ActionType, ProColumns} from '@ant-design/pro-table';
import ProTable, {TableDropdown} from '@ant-design/pro-table';
import {addUserInfo, closeAccount, openAccount, resetPassword, showPassword, updateUserInfo, userInfoPage, UserListItem} from "@/services/customer";
import UpdateForm from "./components/UpdateForm";
import {PageContainer} from "@ant-design/pro-layout";
import ShowPassword from "@/pages/CustomerList/components/ShowPassword";

export type CustomerListState = {
  visible: boolean;
  pwdVisible: boolean;
  initForm?: UserListItem;
  pwdInitForm?: { [key: string]: string }
}

class CustomerList extends React.Component<any, CustomerListState> {

  public columns: ProColumns<UserListItem>[] = [
    {
      title: '序号',
      dataIndex: 'index',
      valueType: 'indexBorder',
      width: 48,
    },
    {
      title: '账号',
      dataIndex: 'userId',
    },
    {
      title: '姓名',
      dataIndex: 'username',
    },
    {
      title: '邮箱',
      dataIndex: 'email',
    },
    {
      title: '电话',
      dataIndex: 'phone',
    },
    {
      title: '状态',
      dataIndex: 'status',
      valueType: 'select',
      initialValue: undefined,
      valueEnum: {
        1: {
          text: '已开户',
          status: 'Processing',
        },
        0: {
          text: '已注销',
          status: 'Default',
        },
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
          node = <a key='close' onClick={() => this.closeAccount(entity.userId)}>注销</a>
        } else if (entity.status === 0) {
          node = <a key='open' onClick={() => this.openAccount(entity.userId)}>开户</a>
        }

        return [
          node,
          <a key='update' onClick={() => this.updateUser(entity)}>编辑</a>,
          <TableDropdown
            key="actionGroup"
            menus={[
              {key: 'resetPassword', name: '重置密码', onClick: () => this.resetPassword(entity.userId)},
              {key: 'showPassword', name: '查看密码', onClick: () => this.showPassword(entity.userId)},
            ]}
          />
        ]
      }
    }
  ];

  public actionRef: ActionType | undefined;

  public updateFormRef: RefObject<UpdateForm> = React.createRef<UpdateForm>();

  public constructor(props: any, context: any) {
    super(props, context);

    this.state = {visible: false, pwdVisible: false};
  }

  /**
   * 开启添加用户窗口
   * @param {boolean} visible 可见性
   * @param {UserListItem} initForm 表单参数
   */
  setDrawerVisible(visible: boolean, initForm?: UserListItem) {
    this.setState({visible, initForm});
  }

  /**
   * 添加用户完成
   * @param {UserListItem} user 用户信息
   */
  onFinish(user: UserListItem) {

    if (user.id) {
      updateUserInfo(user).then(() => {
        this.actionRef?.reload();
        this.setDrawerVisible(false);
        return message.success('更新成功!');
      })
    }
    // 新增
    else {
      addUserInfo(user).then(() => {
        // 意思：告诉编译器，this.actionRef 一定存在，不会为 null 或者 undefined，也就是表取反
        this.actionRef!.reload();
        this.setDrawerVisible(false);
        return message.success('添加成功!');
      });
    }

  }

  /**
   * 开启账户
   * @param {string} userId 用户ID
   */
  openAccount(userId: string) {
    openAccount(userId).then(() => message.success('开启成功.'));
    // 意思：当且当 action && action.reload() === true 时，执行 action.reload()，否则返回 undefined
    this.actionRef?.reload();
  }

  /**
   * 关闭账户
   * @param {string} userId 用户ID
   */
  closeAccount(userId: string) {
    closeAccount(userId).then(() => message.success('关闭成功.'));
    this.actionRef?.reload();
  }

  /**
   * 重置密码
   * @param {string} userId 用户ID
   */
  resetPassword(userId: string) {
    resetPassword(userId).then(() => message.success('密码重置成功！'));
  }

  /**
   * 查看密码
   * @param userId 用户ID
   */
  showPassword(userId: string) {
    showPassword(userId).then(res => {
      this.setState({pwdVisible: true, pwdInitForm: {password: String(res.data)}})
    })
  }

  handleOk() {
    this.setState({pwdVisible: false, pwdInitForm: {password: ''}})
  };

  handleCancel() {
    this.setState({pwdVisible: false, pwdInitForm: {password: ''}})
  };

  updateUser(user: UserListItem) {
    this.setDrawerVisible(true, user)
  }

  render() {

    return (
      <PageContainer>
        <ProTable<UserListItem, API.PageParams>
          columns={this.columns}
          actionRef={(actionRef) => this.actionRef = actionRef}
          request={userInfoPage}
          editable={{type: 'multiple',}}
          rowKey="id"
          search={{labelWidth: 'auto',}}
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
          pagination={{pageSize: 5,}}
          dateFormatter="string"
          headerTitle="用户列表"
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
          onFinish={(values: UserListItem) => this.onFinish(values)}/>}
        {this.state.pwdVisible && <ShowPassword
          visible={this.state.pwdVisible}
          initForm={this.state.pwdInitForm}
          handleOk={() => this.handleOk()}
          handleCancel={() => this.handleCancel()}
        />}
      </PageContainer>
    );
  }
}

export default CustomerList;
