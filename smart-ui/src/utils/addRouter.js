import _import from '../router/_import' // 获取组件的方法

// import defaultCom from '@/views/default-page'
import Layout from '@/layout/Layout.vue'
import Menu from '@/utils/Menu'
import Colalog from '@/components/calalog/index.vue'

/**
 * 生成路由
 * @param 用户的菜单
 * @returns 封装后的路由
 */
export function generateRouter(routerlist) {
  const router = []
  routerlist.forEach(e => {
    // 按钮
    if (!e || e.type === 3) {
      return
    }

    // 当目录没有子菜单时 创建一个
    if (e.parentId === 0 && e.children.length === 0) {
      const children = []
      children.push({
        path: '/' + e.url,
        name: (e.menuName || '开发中'),
        component: _import(e.url.toLowerCase()),
        meta: { title: (e.menuName || '开发中'), icon: e.icon || 'xigua' }
      })
      router.push(new Menu(e.menuName, '/' + e.url, false, children, Layout))
      return
    } else {
      const children = generateRouter(e.children)
      const meta = { title: e.menuName, icon: e.icon || 'xigua' }
      let component = Layout
      let redirect = null
      if (!e.children || e.children.length === 0) {
        component = _import(e.url.toLowerCase())
      }

      if (e.children && e.children.filter(e => e.type !== 3).length !== 0) {
        redirect = 'noredirect'
      }

      if (e.parentId !== 0 && e.children.length !== 0) {
        component = Colalog
      }
      router.push(new Menu(e.menuName, '/' + e.url, false, children, component, meta, redirect))
    }
  })
  return router
}
