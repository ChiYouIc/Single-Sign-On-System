/**
 * 获取 Url 上的参数值
 * @param name {string} 参数名称
 * @param url {string} 资源地址
 */
export function getUrlParam(name: string, url: string): any {
  const reg = new RegExp(`(^|&)${name}=([^$]*)(&|$)`)
  const r = url.substr(1).match(reg);
  if (r != null) {
    return decodeURIComponent(r[2]);
  }

  return null;
}
