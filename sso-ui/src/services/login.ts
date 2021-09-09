// @ts-ignore
/* eslint-disable */
import {request} from 'umi';
import requests from "@/utils/RequestUtil";

type CaptchaParamType = {
// query
  /** 手机号 */
  phone?: string
}

/** 发送验证码 POST /api/login/captcha */
export async function getFakeCaptcha(params: CaptchaParamType, options?: { [key: string]: any }) {
  return request<API.FakeCaptcha>('/api/login/captcha', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}


export async function getToken(code: String, options?: { [key: string]: any }) {
  return requests<{ code: number, data: string }>(`/api/callback/${code}`, {
    method: 'GET',
    ...(options || {})
  });
}
