import Vue from 'vue'
import Router from 'vue-router'

import Layout from '@/layout/Layout.vue'

Vue.use(Router)
/* name*/
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/views/page-main/index.vue'),
        meta: { title: '首页', icon: 'xigua' },
        affix: true
      }
    ]
  }
]

const createRouter = () => new Router({
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router

