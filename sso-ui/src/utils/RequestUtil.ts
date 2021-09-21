import {extend, ResponseError} from 'umi-request'
import {getAuthenticationToken, removeAuthenticationToken} from "@/utils/Tools";
import {message} from "antd";

const redirect = `http://localhost:8500/sso?originUrl=${window.location.href}`

const httpStatus = new Map([
  [404, "服务接口不存在！"],
  [500, "服务器异常！"]
]);

/**
 * 请求错误处理器
 * @param error {ResponseError} 错误
 */
function errorHandler(error: ResponseError) {
  const {response, data} = error;
  if (response) {
    const {status} = response
    // 服务端错误
    if (status === 500) {
      message.error(data.msg);
    }
    // 未认证，认证失败
    else if (status === 400 || status === 401) {
      removeAuthenticationToken()
      window.location.href = redirect
    }
    // 资源路径不存在
    else if (status === 404) {
      message.error(error.request.url + httpStatus.get(status))
    }
  } else {
    // 请求初始化时出错或者没有响应返回的异常
    message.error(httpStatus.get(500));
  }

  throw error;
}

const requests = extend(
  {
    errorHandler,
    // 请求是否带上 cookie
    credentials: 'include',
  }
);

/**
 * 请求拦截器配置
 */
requests.interceptors.request.use((url: string, options: any) => {
    const token = getAuthenticationToken();
    const headers = {
      'Content-type': 'application/json',
      'Accept': 'application/json',
      'Authentication-Token': token
    }
    return ({
      url,
      options: {...options, headers}
    });
  }
);

/**
 * 响应拦截器
 */
requests.interceptors.response.use(async (response: Response) => {
  const data = await response.clone().json();
  if (data) {
    // 未登录 400
    if (data.code === 400) {
      removeAuthenticationToken()
      message.error("前往登录!")
      window.location.href = redirect
    }
  }

  return response;
});


export default requests;
