import requests from "@/utils/RequestUtil";
import {AppList, AppListItem} from "@/services/app/types";

/**
 * 分页获取应用
 * @param params 参数
 * @param options 配置
 * @return 应用列表
 */
export async function appPage(params: API.PageParams, options?: { [key: string]: any }) {
  return requests<AppList>('/api/sys/app/page', {
    method: 'GET',
    params,
    ...(options || {})
  })
}

/**
 * 添加应用
 * @param {AppListItem} data 参数
 * @param {any} options 参数
 * @return {API.Result} 添加结果
 */
export async function addApp(data: AppListItem, options?: { [key: string]: any }) {
  return requests<API.Result>('/api/sys/app/add', {
    method: 'POST',
    data,
    ...(options || {})
  })
}

/**
 * 更新应用
 * @param {AppListItem} data 参数
 * @param {any} options 参数
 * @return {API.Result} 更新结果
 */
export async function updateApp(data: AppListItem, options?: { [key: string]: any }) {
  return requests<API.Result>('/api/sys/app/update', {
    method: 'PUT',
    data,
    ...(options || {})
  })
}

/**
 * 开启应用
 * @param id 参数
 */
export async function updateAppOpen(id: number) {
  return requests<API.Result>('/api/sys/app/open', {
      method: 'PUT',
    params: {id}
    }
  )
}

/**
 * 关闭应用
 * @param id 参数
 */
export async function updateAppClose(id: number) {
  return requests<API.Result>('/api/sys/app/close', {
    method: 'PUT',
    params: {id}
  })
}

