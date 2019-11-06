<template>
  <el-menu class="navbar" mode="horizontal">
    <!--收缩侧边-->
    <hamburger :toggle-click="toggleSideBar" :is-active="sidebar.opened" class="hamburger-container"/>
    <!--面包屑-->
    <breadcrumb/>
    <el-dropdown class="avatar-container" trigger="click">
      <div class="avatar-wrapper">
        <img src="https://i.loli.net/2019/11/06/JOXPwEHs2oaunr5.jpg" class="user-avatar">
        <i class="el-icon-caret-bottom"/>
      </div>
      <el-dropdown-menu slot="dropdown" class="user-dropdown">
        <router-link class="inlineBlock" to="/">
          <el-dropdown-item>
            首页
          </el-dropdown-item>
        </router-link>
        <el-dropdown-item divided>
          <span style="display:block;" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </el-menu>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import { removeCookie, TokenKey } from '@/utils/Cookis'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  data: function() {
    return {
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('ToggleSideBar')
      console.log(this.$store.state.app.sidebar.opened)
    },
    logout() {
      this.$store.dispatch('user/logout').then(() => {
        removeCookie(window.sessionStorage.getItem('username') + '-' + TokenKey)
        this.$router.push({ path: '/login' })
        // location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    }
  }
}
</script>

<style scoped>
  .navbar {
    height: 50px;
    line-height: 50px;
    border-radius: 0px !important;
  }

  .navbar .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }

  .navbar .screenfull {
    position: absolute;
    right: 90px;
    top: 16px;
    color: red;
  }

  .navbar .avatar-container {
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 35px;
  }

  .navbar .avatar-container .avatar-wrapper {
    cursor: pointer;
    margin-top: 5px;
    position: relative;
  }

  .navbar .avatar-container .avatar-wrapper .user-avatar {
    width: 40px;
    height: 40px;
    border-radius: 10px;
  }

  .navbar .avatar-container .avatar-wrapper .el-icon-caret-bottom {
    position: absolute;
    right: -20px;
    top: 25px;
    font-size: 12px;
  }
</style>

