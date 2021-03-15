export default file => {
  return map[file] || (() => import('@/views/default-page'))
}

// 新的组件需要在该处先定义
const map = {
  role: () => import('@/views/role/role-inedx'),
  menu: () => import('@/views/menu/menu-index'),
  user: () => import('@/views/user/user-index.vue'),
  job: () => import('@/views/job-page/job-index.vue')
}
