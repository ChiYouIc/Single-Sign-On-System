import { getToken } from "@/services/login";
import { getAuthenticationToken, setAuthenticationToken } from "@/utils/Tools";
import { history, useModel } from "umi";

const redirect = `http://localhost:8500/sso?app=Sso-Server&originUri=${window.location.pathname}&originUrl=${window.location.href}`;

/**
 * 使用 code 与后端交换 token
 *
 * @param {string} code code码
 * @param {string} originUri 原始地址
 */
const codeCallback = async (code: string, originUri: string) => {
  const { refresh } = useModel('@@initialState');

  await getToken(code).then((res: any) => {
    setAuthenticationToken(res.data);
    refresh();
    history.push(originUri);
  })
}

const Callback: React.FC<{}> = (props: any) => {

  const code = props.location.query.code || null;
  const originUri = props.location.query.originUri || "/welcome";

  if (code == null && getAuthenticationToken() == null) {
    window.location.href = redirect;
  } else {
    codeCallback(code, originUri);
  }
  return null;
}

export default Callback;


