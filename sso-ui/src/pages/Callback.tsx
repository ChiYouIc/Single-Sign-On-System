import {Component} from 'react';
import {getToken} from "@/services/login";
import {history} from "umi";
import {message} from "antd";
import {getAuthenticationToken, setAuthenticationToken} from "@/utils/Tools";

const redirect = `http://localhost:8500/sso?originUrl=${window.location.href}`

const callback = async (code: string, origin: string) => {
  debugger
  getToken(code).then((res: any) => {
    setAuthenticationToken(res.data)
    history.push(origin)
  }).catch(() => {
    message.error("code 已经失效！")
    window.location.href = redirect
  })
}

class Callback extends Component<any, any> {

  constructor(props: any, context: any) {
    super(props, context);

    this.state = {
      code: props.location.query.code,
      origin: props.location.query.originUrl || "/welcome"
    }
  }


  render() {
    // 如果 code 和 token 都为 null 则直接跳转
    if (this.state.code == null && getAuthenticationToken() == null) {
      window.location.href = redirect
    }
    callback(this.state.code, this.state.origin)
    return null;
  }
}

export default Callback;
