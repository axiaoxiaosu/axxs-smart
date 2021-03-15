import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getCookie, TokenKey } from '@/utils/Cookis'

// axios实例
const service = axios.create({
  baseURL: process.env.BASE_URL, // url = base url + request url
  timeout: 25000, // 超时时间
  withCredentials: true

})

// request 拦截器
service.interceptors.request.use(
  config => {
    if (window.sessionStorage.getItem('username')) {
      // 携带的token
      config.headers['token'] = getCookie(window.sessionStorage.getItem('username') + '-' + TokenKey)
    }
    /* spring 国际化*/
    config.headers['Accept-Language'] = 'zh-CN'
    return config
  },
  error => {
    Message({
      message: '响应超时,请稍后重试',
      type: 'error',
      duration: 1000
    })

    // 处理错误请求
    return Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    const res = response.data

    if (response.status === 200 & res.code === 200) {
      return res
    }
    if (res.code === 403) {
      store.dispatch('user/setRouterStatus', false)
      store.dispatch('user/setRouterList', [])
      window.location.href = `${window.location.origin}/smart/#/login`
    }
    if (res.code === 400) {
      store.dispatch('user/setRouterStatus', false)
      store.dispatch('user/setRouterList', [])
    }
    const errorMsg = res.msg || '未知异常'
    Message({
      message: errorMsg,
      type: 'error',
      duration: 1000
    })
    return res
  },
  error => {
    if (error.status === 403 || error.response.status === 403 || (error && error.code && error.code === 'ECONNABORTED')) {
      console.log('现在的登陆已失效请重新登陆')
      // Message({ message: '现在的登陆已失效请重新登陆', type: 'error', duration: 3 * 1000 })
      window.location.href = `${window.location.origin}/smart/#/login`
      return
    }

    if (error.message === 'Network Error') {
      const cookie = getCookie(window.sessionStorage.getItem('username') + '-' + TokenKey)
      if (!cookie) {
        Message({
          message: '服务器抽风了请稍后访问',
          type: 'error',
          duration: 2 * 1000
        })
      } else {
        Message({
          message: '请求被拒绝了',
          type: 'error',
          duration: 2 * 1000
        })
      }

      return Promise.reject(error)
    }
    Message({
      message: error.message,
      type: 'error',
      duration: 2 * 1000
    })
    return error// Promise.reject(error)
  }
)

export default service
