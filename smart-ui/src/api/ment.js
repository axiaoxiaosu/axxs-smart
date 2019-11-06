import request from '@/utils/request'
const urlPath = '/menus'
export function getMenus(params) {
  return request({
    url: urlPath + '/getMenus',
    method: 'post',
    data: params
  })
}

export function getMenusPage(params) {
  return request({
    url: urlPath + '/getMenusPage',
    method: 'post',
    data: params
  })
}

export function addMenu(params) {
  return request({
    url: urlPath + '/addMenu',
    method: 'post',
    data: params
  })
}

export function deleteMenu(params) {
  return request({
    url: urlPath + '/deleteMenu',
    method: 'post',
    params
  })
}

export function updateMenu(params) {
  return request({
    url: urlPath + '/updateMenu',
    method: 'post',
    data: params
  })
}

