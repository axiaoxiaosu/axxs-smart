import request from '@/utils/request'
export function getTaskList(params) {
  return request({
    url: '/quartz/jobs/getTaskList',
    method: 'post',
    data: params
  })
}

export function deleteTask(params) {
  return request({
    url: '/quartz/jobs/deleteTask',
    method: 'delete',
    params
  })
}

export function addTask(params) {
  return request({
    url: '/quartz/jobs/addTask',
    method: 'post',
    data: params
  })
}
