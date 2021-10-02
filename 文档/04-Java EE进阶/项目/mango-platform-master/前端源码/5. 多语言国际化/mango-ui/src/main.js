import Vue from 'vue'
import App from './App'
import router from './router'
import api from './http'
import i18n from './i18n'
import global from '@/utils/global'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'

Vue.use(ElementUI)  // 注册使用Element
Vue.use(api)  // 注册使用API模块

Vue.prototype.global = global // 挂载全局配置模块

new Vue({
  el: '#app',
  i18n,
  router,
  render: h => h(App)
})