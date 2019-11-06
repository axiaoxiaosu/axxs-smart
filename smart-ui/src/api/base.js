import request from '@/utils/request'

const post = (url, params) => {
  return request({
    url: url,
    method: 'post',
    data: params
  })
}

const get = (url, params) => {
  return request({
    url: url,
    method: 'get',
    params
  })
}

export default {
  post: post,
  get: get
}
