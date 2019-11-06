<template>
  <scroll-bar>
    <!--项目logo-->
    <logo :collapse="isCollapse" />
    <el-menu
      :show-timeout="200"
      :default-active="$route.path"
      :collapse="isCollapse"
      mode="vertical"
      background-color="#304156"
      text-color="#bfcbd9"
      active-text-color="#409EFF">
      <sidebar-item v-for="(route,index) in routes" :key="route.path+index" :item="route" :base-path="route.path" />
    </el-menu>
  </scroll-bar>
</template>

<script>
import { mapGetters } from 'vuex'
import ScrollBar from '@/components/ScrollBar'
import SidebarItem from './SidebarItem'
import Logo from './Logo'
export default {
  name: 'Sidebar',
  components: { ScrollBar, SidebarItem, Logo },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      return this.$store.getters.routerList
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>

<style scoped>

</style>
