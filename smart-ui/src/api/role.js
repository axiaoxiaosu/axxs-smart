import request from '@/utils/request'

const urlPath = '/role'

const actionType = {
  post: 'post',
  get: 'get'
}

export function getRoles(params) {
  return request({
    url: urlPath + '/getRoles',
    method: actionType.post,
    params
  })
}

export function addRole(params) {
  return request({
    url: urlPath + '/addRole',
    method: actionType.post,
    params
  })
}

export function roleBindMent(params) {
  return request({
    url: urlPath + '/roleBindMent',
    method: actionType.post,
    params
  })
}

export function getRole(params) {
  return request({
    url: urlPath + '/getRole',
    method: actionType.post,
    params
  })
}

export function updateRole(params) {
  return request({
    url: urlPath + '/updateRole',
    method: actionType.post,
    params
  })
}

export function deleteRole(params) {
  return request({
    url: urlPath + '/deleteRole',
    method: actionType.post,
    params
  })
}

export function mensByRole(params) {
  return request({
    url: urlPath + '/mensByRole',
    method: actionType.post,
    params
  })
}
