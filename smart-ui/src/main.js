// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import comment from './utils/smart-util.js'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import './icons' // icon
import animated from 'animate.css'
import '@/styles/css/index.scss' // global css
import './utils/directives'
import './permission' // permission control
import './utils/Cookis'
import 'font-awesome/css/font-awesome.css'

/* import '@/styles/css/base.css' // global css*/
Vue.config.productionTip = false

Vue.prototype.$util = comment
Vue.use(animated)
Vue.use(ElementUI, { locale })
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
