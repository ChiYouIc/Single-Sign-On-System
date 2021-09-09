/**
 * 从 localStorage 中获取 token
 */
export function getAuthenticationToken() {
  return window.localStorage.getItem("Authentication-Token")
}

/**
 * 将 token 保存到 localStorage
 * @param token {string} token
 */
export function setAuthenticationToken(token: string) {
  window.localStorage.setItem("Authentication-Token", token)
}

/**
 * 删除token
 */
export function removeAuthenticationToken() {
  window.localStorage.removeItem("Authentication-Token")
}
