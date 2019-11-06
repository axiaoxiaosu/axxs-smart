import Cookies from 'js-cookie'

// 要对应后端的token-key
export const TokenKey = 'smart-token'
export const MenusKey = 'smart-menus'

export function getCookie(key) {
  return Cookies.get(key)
}

export function setCookie(key, token, minute) {
  // 默认cookie有效时间为3分钟
  if (minute === null || typeof (minute) === 'undefined') minute = 3
  return Cookies.set(key, token, { expires: new Date(new Date().getTime() + minute * 60 * 1000) })
}

export function removeCookie(key) {
  return Cookies.remove(key)
}
