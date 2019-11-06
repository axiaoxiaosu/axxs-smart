import request from '@/utils/request'

export function getIp(params) {
  return request({
    url: '/report/ip',
    method: 'get',
    params
  })
}
