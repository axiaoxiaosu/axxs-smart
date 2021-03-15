/* eslint-disable no-unused-vars */

import router from './router'
import store from './store'
import { Message } from 'element-ui'
import { generateRouter } from './utils/addRouter'
import Layout from '@/layout/Layout.vue'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'
import smartUtil from '@/utils/smart-util' // Progress 进度条样式

// async function getResource() {
//   return store.dispatch('user/getResource')
// }

router.beforeEach(async(to, from, next) => {
  NProgress.start()
  //  oauth是服务端跳转的跳转 说明用户未登陆
  if (to.path === '/oauth') {
    next()
    return
  }

  // 用户没有登陆访问需要登陆的路由
  if (to.path !== '/login' && (window.sessionStorage.getItem('userLogin') === 'false' || window.sessionStorage.getItem('userLogin') === null)) {
    next('login')
    NProgress.done()
    return
  }

  // 加载路由
  if (to.path !== '/login' && to.path !== '/oauth' && store.getters.routerList && store.getters.routerList.length === 0) {
    try {
      initRouterList()
      next()
    } catch (error) {
      console.error('路由加载失败e==>', error)
      Message({
        message: '路由加载失败请联系管理员', type: 'error', onClose: () => {
          window.sessionStorage.setItem('userLogin', 'false')
        }
      })
      next('/login')
    }
    return
  }
  // 正常通过
  next()
})
router.afterEach(() => {
  // 结束Progress
  NProgress.done()
})

async function initRouterList() {
  let menus, asyncRouter
  await store.dispatch('user/getResource').then(response => {
    // tree 转换
    menus = response
  })

  // 生成对应路由
  if (menus) {
    asyncRouter = generateRouter(menus)
  }
  console.log('生成后的路由', asyncRouter)
  // 404路由
  // {
  //   path: '/',
  //   component: Layout,
  //   redirect: '/home',
  //   children: [
  //     {
  //       path: 'home',
  //       name: 'home',
  //       component: () => import('@/views/Home.vue'),
  //       meta: { title: '首页', icon: 'xigua' },
  //       affix: true
  //     }
  //   ]
  // }

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
  window.sessionStorage.setItem('userMenus', 'true')
}
