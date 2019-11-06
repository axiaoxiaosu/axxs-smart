import router from './router'
import store from './store'
import { getCookie, removeCookie, MenusKey, TokenKey } from './utils/Cookis'

import { Message } from 'element-ui'
import { addRouter } from './utils/addRouter'

import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'
import smartUtil from '@/utils/smart-util' // Progress 进度条样式

async function getResource() {
  await store.dispatch('user/getResource').then(response => {
  }).catch(() => {
  })
}
router.beforeEach(async(to, from, next) => {
  NProgress.start()
  // 使用session保证一个浏览器可以登陆多个用户
  const cookie = getCookie(window.sessionStorage.getItem('username') + '-' + TokenKey)
  if (cookie) {
    if (to.path !== '/login') {
      // 路由加载标记
      if (store.getters.routerStatus) {
        // 更新资源
        getResource()
        next()
      } else {
        gotoRouter(to, from, next)
      }
    } else {
      Message({ message: '您已经登录', type: 'info' })
      next()
    }
  } else {
    store.dispatch('user/setRouterStatus', false)
    if (to.path !== '/login') {
      next('/login')
    } else {
      next()
    }
  }
})
router.afterEach(() => {
  // 结束Progress
  NProgress.done()
})

async function gotoRouter(to, from, next) {
  // 请求的资源
  let asyncRouter = []
  const token = getCookie(window.sessionStorage.getItem('username') + '-' + TokenKey)
  try {
    if (token) {
      let menus = null
      // 每次切换都重新请求资源
      await store.dispatch('user/getResource').then(response => {
        // tree转换
        menus = smartUtil.treeDispose(response)
      }).catch(e => {
      })
      // 解析路由  根据表机构可自行维护
      if (menus) {
        asyncRouter = addRouter(menus)
      }
      const pageNoFind = {
        path: '*',
        component: () => import('@/views/error-page/404'),
        hidden: true
      }
      asyncRouter.push(pageNoFind)
      // 加载新路由
      router.addRoutes(asyncRouter)
      // 记录路由获取状态
      store.dispatch('user/setRouterStatus', true)
      store.dispatch('user/setRouterList', asyncRouter)
      // replace 为 true 不会留下路由记录
      next({ ...to, replace: true })
    } else {
      // 用戶未登陆
      next('/login')
    }
  } catch (e) {
    console.log('加载动态路由失败' + e)
    removeCookie(TokenKey)
    removeCookie(MenusKey)
    if (from.path !== '/login') {
      Message({
        message: '路由加载失败请联系管理员', type: 'error', onClose: (e) => {
          next('/login')
        }
      })
    } else {
      Message({
        message: '路由加载失败请联系管理员', type: 'error', onClose: (e) => {
          location.reload()
        }
      })
    }
  }
}
