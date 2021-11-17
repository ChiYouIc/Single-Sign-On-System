import { UserListItem } from "@/services/customer";
import { Button, Drawer, Form, Input, Select } from "antd";
import { FormInstance } from "antd/es/form/hooks/useForm";
import React, { RefObject } from "react";

const tailLayout = {
  wrapperCol: { offset: 8, span: 16 },
  labelCol: { offset: 8, span: 8 }
};

const layout = {
  labelCol: { span: 2 },
  wrapperCol: { span: 16 },
};

export type CustomerUpdateFormProps = {
  visible: boolean;
  initForm?: UserListItem;
  onClose: Function;
  onFinish: Function;
}

class UpdateForm extends React.Component<CustomerUpdateFormProps, UserListItem> {

  public formRef: RefObject<FormInstance> = React.createRef<FormInstance>()

  constructor(props: CustomerUpdateFormProps, context: any) {
    super(props, context);
  }

  componentDidUpdate() {
    if (!this.props.visible) {
      this.formRef.current!.resetFields()
    }
  }

  resetForm() {
    this.formRef.current!.resetFields()
  }

  onFinishFailed(values: any) {
    console.log(values)
  }

  onFill(user: UserListItem) {
    this.formRef.current!.setFieldsValue(user);
  };

  render() {

    return (
      <Drawer title='添加用户' placement='right' width="50%" onClose={() => this.props.onClose()} visible={this.props.visible}>
        <Form
          {...layout}
          ref={this.formRef}
          name='basic'
          initialValues={this.props?.initForm}
          onFinish={(values) => this.props.onFinish(values)}
          onFinishFailed={this.onFinishFailed}
          autoComplete='off'
        >
          <Form.Item
            label='ID'
            name='id'
            hidden
          >
            <Input />
          </Form.Item>
          <Form.Item
            label='账号'
            name='userId'
            rules={[{ type: 'string', min: 4, required: true, message: '请输入账号.' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label='姓名'
            name='username'
            rules={[{ type: 'string', required: true, message: '请输入姓名.' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label='邮箱'
            name='email'
            rules={[{ type: 'email', required: true, message: '请输入邮箱.' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label='电话'
            name='phone'
            rules={[{ type: 'string', len: 11, required: true, message: '请输入电话.' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label='状态'
            name='status'
            initialValue={0}
            rules={[{ required: true, message: '请选择状态.' }]}
          >
            <Select placeholder='请选择状态' allowClear>
              <Select.Option value={0}>已注销</Select.Option>
              <Select.Option value={1}>已开户</Select.Option>
            </Select>
          </Form.Item>
          <Form.Item {...tailLayout}>
            <Button type='primary' htmlType='submit'>提交</Button>
            <Button htmlType='button' onClick={() => this.resetForm()}>重置</Button>
          </Form.Item>
        </Form>
      </Drawer>
    );
  }

}

export default UpdateForm;