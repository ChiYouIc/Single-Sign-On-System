import React, {RefObject} from "react";
import {FormInstance} from "antd/es/form/hooks/useForm";
import {Form, Input, Modal} from "antd";
import {layout} from '@/../config/defaultSettings'


export type CustomerShowPasswordProps = {
  visible: boolean;
  initForm?: { [key: string]: string },
  handleOk: Function,
  handleCancel: Function
}

class ShowPassword extends React.Component<CustomerShowPasswordProps, any> {

  public formRef: RefObject<FormInstance> = React.createRef<FormInstance>()

  render() {
    return (
      <Modal title="查看密码" visible={this.props.visible} onOk={() => this.props.handleOk()} onCancel={() => this.props.handleCancel()}>
        <Form
          {...layout}
          ref={this.formRef}
          name='basic'
          initialValues={this.props?.initForm}
          autoComplete='off'
        >
          <Form.Item
            label='密码'
            name='password'
          >
            <Input/>
          </Form.Item>
        </Form>
      </Modal>
    );
  }

}


export default ShowPassword;
