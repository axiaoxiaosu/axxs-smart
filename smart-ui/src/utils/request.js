import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getCookie, TokenKey } from '@/utils/Cookis'

// axios实例
const service = axios.create({
  baseURL: process.env.BASE_URL, // url = base url + request url
  timeout: 25000 // 超时时间
})

// request 拦截器
service.interceptors.request.use(
  config => {
    // do something before request is sent
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
    // code!=0 出现错误
    if (res.code !== 0) {
      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }

      Message({
        message: res.msg || '未知异常',
        type: 'error',
        duration: 1000
      })
      // 异常无需回调
      // return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      if (response.data === false) {
        return Promise.reject(new Error('操作失败'))
      }
      return res
    }
  },
  error => {
    if (error.message === 'Network Error') {
      console.log(error)
      Message({
        message: '请求被拒绝了',
        type: 'error',
        duration: 2 * 1000
      })
      return Promise.reject(error)
    }
    if (error.status === 403) {
      Message({
        message: '非法的访问',
        type: 'error',
        duration: 2 * 1000
      })
      return Promise.reject(error)
    }

    Message({
      message: error.message,
      type: 'error',
      duration: 2 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
