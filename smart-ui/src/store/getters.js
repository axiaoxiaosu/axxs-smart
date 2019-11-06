const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  resource: state => state.user.resource,
  routerList: state => state.user.routerList,
  routerStatus: state => state.user.routerStatus,
  cachedViews: state => state.tagsView.cachedViews,
  visitedViews: state => state.tagsView.visitedViews
}
export default getters
