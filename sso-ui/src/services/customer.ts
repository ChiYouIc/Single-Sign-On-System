import requests from "@/utils/RequestUtil";
import user from "mock/user";

export type UserListItem = {
  id?: number;
  username?: string;
  userId: string;
  email?: string;
  avatar?: string
  status?: number;
  phone?: string;
}

export type UserList = {
  data?: UserListItem[];
  /** 列表的内容总数 */
  total?: number;
  success?: boolean;
}

/**
 * 分页获取用户
 * @param params 参数
 * @param options 配置
 * @returns 用户列表
 */
export async function userInfoPage(params: API.PageParams, options?: { [key: string]: any }) {
  return requests<UserList>('/api/sys/user/page', {
    method: 'GET',
    params,
    ...(options || {})
  });
}

/**
 * 添加用户
 * @param {UserListItem} data 参数
 * @param {any} options 配置
 * @returns 添加结果
 */
export async function addUserInfo(data: UserListItem, options?: { [key: string]: any }) {
  return requests<API.Result>('/api/sys/user/add', {
    method: 'POST',
    data,
    ...(options || {})
  });
}

/**
 * 更新用户信息
 * @param data 数据
 * @param options 配置
 * @returns 更新结果
 */
export async function updateUserInfo(data: UserListItem, options?: { [key: string]: any }) {
  return requests<API.Result>('/api/sys/user/update', {
    method: 'PUT',
    data,
    ...(options || {})
  })
}

/**
 * 开启账户
 * @param userId 用户ID
 * @returns 开户结果
 */
export async function openAccount(userId: string) {
  return requests<API.Result>('/api/sys/user/open', {
    method: 'PUT',
    params: { userId: userId },
  });
}

/**
 * 关闭账户
 * @param userId 用户ID
 * @returns 关闭结果
 */
export async function closeAccount(userId: string) {
  return requests<API.Result>('/api/sys/user/close', {
    method: 'PUT',
    params: { userId: userId },
  });
}

/**
 * 重置密码
 * @param userId 用户ID
 * @returns 结果
 */
export async function resetPassword(userId: string) {
  return requests<API.Result>('/api/sys/user/reset', {
    method: 'PUT',
    params: { userId: userId }
  });
}