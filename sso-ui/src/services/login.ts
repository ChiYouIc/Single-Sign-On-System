import requests from "@/utils/RequestUtil";

type CaptchaParamType = {
  // query
  /** 手机号 */
  phone?: string
}

/**
 * 发送验证码
 * @param params 
 * @param options 
 * @returns 
 */
export async function getFakeCaptcha(params: CaptchaParamType, options?: { [key: string]: any }) {
  return requests<API.FakeCaptcha>('/api/login/captcha', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/**
 * 获取当前的用户
 * @param options 
 * @returns 
 */
export async function currentUser(options?: { [key: string]: any }) {
  return requests<{ code: number, data: API.CurrentUser }>('/api/currentUser', {
    method: 'GET',
    ...(options || {}),
  });
}

/**
 * 退出登录接口
 * @param options 
 * @returns 
 */
export async function outLogin(options?: { [key: string]: any }) {
  return requests<API.Result>('/api/logout', {
    method: 'POST',
    ...(options || {}),
  });
}

/**
 * 登录接口
 * @param body 
 * @param options 
 * @returns 
 */
export async function login(body: API.LoginParams, options?: { [key: string]: any }) {
  return requests<API.LoginResult>('/api/login', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

/**
 * 获取 token
 * @param code 
 * @param options 
 * @returns 
 */
export async function getToken(code: String, options?: { [key: string]: any }) {
  return requests<{ code: number, data: string }>(`/api/callback/${code}`, {
    method: 'GET',
    ...(options || {})
  });
}
