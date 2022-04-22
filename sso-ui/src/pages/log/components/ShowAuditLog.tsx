import React from 'react';
import { Col, Drawer, Row, Timeline } from 'antd';
import TimelineItem from 'antd/es/timeline/TimelineItem';
import { AuditLogItem } from '@/services/log/types';
import './log.module.css';

export type ShowAuditLogProps = {
  visible: boolean;
  initForm: AuditLogItem[],
  onClose: Function
}

const timeLineColorMap = {
  'INFO': 'green',
  'WARN': 'orange',
  'ERROR': 'red',
  'DEBUG': 'gray',
};

class ShowAuditLog extends React.Component<ShowAuditLogProps, any> {

  public timeLineItem() {
    if (this.props.initForm?.length === 0) {
      return <TimelineItem>没有审计日志记录</TimelineItem>;
    } else {
      return this.props.initForm?.map(item => {
        return (
          <TimelineItem key={item.id} color={timeLineColorMap[item.level]}>
            <Row gutter={[16, 16]}>
              <Col span={2}>traceId：</Col>
              <Col span={22}>{item.traceId}</Col>
            </Row>
            <Row>
              <Col span={2}>审计内容：</Col>
              <Col span={22}>{item.auditContent}</Col>
            </Row>
            <Row>
              <Col span={2}>审计级别：</Col>
              <Col span={22}>{item.level}</Col>
            </Row>
            <Row>
              <Col span={2}>审计时间：</Col>
              <Col span={22}>{item.operationTime}</Col>
            </Row>
          </TimelineItem>
        );
      });
    }
  }

  render() {
    return (
      <Drawer title='查看审计日志' placement='right' width='50%' visible={this.props.visible} onClose={() => this.props.onClose()}>
        <Timeline>
          {this.timeLineItem()}
        </Timeline>
      </Drawer>
    );
  }
}

export default ShowAuditLog;
