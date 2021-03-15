import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'get'
  })
}

export function oauthLogin(data) {
  return request({
    url: `${process.env.OAUTH_SERVE_URL}/login`,
    method: 'post',
    data
  })
}

// 获取用户信息
export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

// 获取用户资源
export function getResource() {
  return request({
    url: '/menus/getMenus',
    method: 'post'
  })
}

// 登出
export function oauthLogout() {
  return request({
    url: `${process.env.OAUTH_SERVE_URL}/logout`,
    method: 'post'
  })
}

// 分页获取全部用户数据
export function getUsersPage(params) {
  return request({
    url: '/user/getUsersPage',
    method: 'post',
    params
  })
}

// 删除用户信息
export function deleteUser() {
  return request({
    url: '/user/getUser',
    method: 'post'
  })
}

// 删除用户信息
export function adduser(params) {
  return request({
    url: '/user/addUser',
    method: 'post',
    params
  })
}

// 获取按钮资源
export function getBtu(params) {
  return request({
    url: '/user/getBtu',
    method: 'post',
    data: params
  })
}

