import _import from '../router/_import' // 获取组件的方法

import defaultCom from '@/views/default-page'
import Layout from '@/layout/Layout.vue'
import Colalog from '@/components/calalog/index.vue'

/**
 * 生成路由
 * @param
 * @returns
 */

export function addRouter(routerlist) {
  const router = []
  routerlist.forEach(e => {
    if (typeof (e) === 'undefined' || e.type === '按钮') {
      return
    }
    let menu = {
      name: e.title,
      type: e.type,
      hidden: e.isShow === '否'
    }
    // 绑定组件
    // 查找是否存在该组件
    const component = _import(e.url.toLowerCase())
    // 当目录没有子菜单时 默认赋值一个
    if (e.parentId === 0 && (e.children.length === 0 || e.type === '菜单')) {
      menu = Object.assign({}, menu, { component: Layout })
      menu = Object.assign({}, menu, { path: '/' + e.url })
      const children = [{
        path: 'index',
        name: (e.title || '开发中'),
        component: component || defaultCom,
        meta: { title: (e.title || '开发中'), icon: 'xigua' }
      }
      ]
      menu = Object.assign({}, menu, { children: children })
      router.push(menu)
      return
    } else if (e.parentId === 0) {
      menu = Object.assign({}, menu, { component: Layout })
      menu = Object.assign({}, menu, { redirect: 'noredirect' })
    } else if (e.children.length > 0 && e.type === '目录') {
      menu = Object.assign({}, menu, { redirect: '/' + e.children[0].url })
      menu = Object.assign({}, menu, { component: Colalog })
      menu = Object.assign({}, menu, { affix: true })
    } else if (e.parentId !== 0) {
      menu = Object.assign({}, menu, { component: component })
      menu = Object.assign({}, menu, { affix: true })
    }
    if (e.children && e.children.length > 0) {
      menu = Object.assign({}, menu, { path: '/' + e.url })
      // 递归展示
      menu = Object.assign({}, menu, { children: addRouter(e.children) })
    } else {
      menu = Object.assign({}, menu, { path: '/' + e.url })
    }

    // 是否显示
    if (e.isShow === 1) {
      menu = Object.assign({}, menu, { hidden: true })
    }
    // 自定义title+icon
    if (e.title && e.icon) {
      menu = Object.assign({}, menu, {
        meta: { title: e.title, icon: e.icon }
      })
    } else {
      // 默认显示
      menu = Object.assign({}, menu, {
        // TODO 默认显示icon西瓜
        meta: { title: e.title, icon: 'xigua' }
      })
    }
    router.push(menu)
  })
  return router
}
