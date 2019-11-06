import { login, logout, getInfo, getResource } from '@/api/user'
import { getCookie, setCookie, removeCookie, TokenKey } from '@/utils/Cookis'
import { constantRoutes } from '@/router'

const state = {
  token: getCookie(window.sessionStorage.getItem('username') + '-' + TokenKey),
  name: '',
  avatar: '',
  roles: [],
  resource: [],
  routerList: [], // 动态路由
  routerStatus: false // 路由状态默认为 未加载
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_RESOURCE: (state, resource) => {
    state.resource = resource
  },
  SET_ROUTER: (state, routerList) => {
    state.routerList = routerList
  },
  SET_ROUTERSTATUS: (state, routerStatus) => {
    state.routerStatus = routerStatus
  }
}

const actions = {
  // 登录
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        setCookie(data.user.username + '-' + TokenKey, data.token, 1)
        window.sessionStorage.setItem('username', data.user.username)
        //  setCookie(MenusKey, data.menu)
        // 存储用户名称
        commit('SET_NAME', data.user.username)
        commit('SET_TOKEN', data.token)
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取用户资源
  getResource({ commit, state }) {
    return new Promise(
      (resolve, reject) => {
        getResource(state.token).then(response => {
          const { data } = response
          if (!data) {
            reject('该用户没有资源')
            // TODO 没有资源就看首页
            return
          }
          // 刷新cookie时间
          const token = getCookie(window.sessionStorage.getItem('username') + '-' + TokenKey)
          if (token) {
            setCookie(window.sessionStorage.getItem('username') + '-' + TokenKey, token, 5)
          }
          // 存储vuex一份
          commit('SET_RESOURCE', data)
          commit('SET_ROLES', data)
          // setCookie(MenusKey, data)
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      }
    )
  },
  // 获取用户信息
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        if (!data) {
          reject('=-= 没有获取到用户信息')
          removeCookie(TokenKey)
          return
        }
        const { name, avatar } = data
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 用户登出
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        removeCookie(TokenKey)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 删除token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      removeCookie(TokenKey)
      resolve()
    })
  },
  // 动态设置路由 此为设置设置途径
  setRouterList({ commit }, routerList) {
    commit('SET_ROUTER', constantRoutes.concat(routerList)) // 进行路由拼接并存储
  },
  // 存储颗粒话权限
  setroles({ commit }, roleList) {
    commit('SET_ROLES', roleList)
  },
  // 存储路由状态
  setRouterStatus({ commit }, routerStatus) {
    commit('SET_ROUTERSTATUS', routerStatus)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

