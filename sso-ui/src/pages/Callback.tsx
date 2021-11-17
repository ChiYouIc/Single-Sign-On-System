import { Component } from 'react';
import { getToken } from "@/services/login";
import { history } from "umi";
import { getAuthenticationToken, setAuthenticationToken } from "@/utils/Tools";

const redirect = `http://localhost:8500/sso?originUrl=${window.location.href}`;

const callback = async (code: string, origin: string) => {
  getToken(code).then((res: any) => {
    setAuthenticationToken(res.data);
    history.push(origin);
  })
}

class Callback extends Component<any, any> {

  constructor(props: any, context: any) {
    super(props, context);
    this.state = {
      code: props.location.query.code || null,
      origin: props.location.query.originUrl || "/welcome"
    };
  }


  render() {
    // 如果 code 和 token 都为 null 则直接跳转
    if (this.state.code == null && getAuthenticationToken() == null) {
      window.location.href = redirect;
    } else {
      callback(this.state.code, this.state.origin);
    }

    return null;
  }
}

export default Callback;
