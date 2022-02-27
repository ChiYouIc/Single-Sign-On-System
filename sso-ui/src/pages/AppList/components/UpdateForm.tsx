import React, {RefObject} from "react";
import {FormInstance} from "antd/es/form/hooks/useForm";
import {layout, tailLayout} from '@/../config/defaultSettings'
import {Button, Drawer, Form, Input, Select} from "antd";
import {AppListItem} from "@/services/app/types";

export type AppUpdateFormProps = {
  visible: boolean;
  initForm?: AppListItem;
  onClose: Function;
  onFinish: Function;
}

class UpdateForm extends React.Component<AppUpdateFormProps, any> {

  public formRef: RefObject<FormInstance> = React.createRef<FormInstance>()

  resetForm() {
    this.formRef.current!.resetFields()
  }

  onFinishFailed(values: any) {
    console.log(values)
  }

  render() {
    return (
      <Drawer title='添加应用' placement='right' width="50%" onClose={() => this.props.onClose()} visible={this.props.visible}>
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
            <Input/>
          </Form.Item>
          <Form.Item
            label='应用码'
            name='appCode'
            hidden
          >
            <Input/>
          </Form.Item>
          <Form.Item
            label='应用名'
            name='appName'
            rules={[{type: 'string', required: true, message: '请输入应用名.'}]}
          >
            <Input/>
          </Form.Item>
          <Form.Item
            label='排序'
            name='sort'
            rules={[{type: 'number', required: true, message: '请输入应用排序.'}]}
          >
            <Input/>
          </Form.Item>
          <Form.Item
            label='状态'
            name='status'
            initialValue={0}
            rules={[{type: 'number', required: true, message: '请选择状态.'}]}
          >
            <Select placeholder='请选择状态' allowClear>
              <Select.Option value={0}>未认证</Select.Option>
              <Select.Option value={1}>已认证</Select.Option>
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
