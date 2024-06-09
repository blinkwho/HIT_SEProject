import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueRouter from "vue-router"
import router from '/router'
import axios from "axios"

Vue.config.productionTip = false
Vue.prototype.$axios = axios;
Vue.prototype.$urlHost = "http://localhost:8090"

Vue.use(ElementUI)
Vue.use(VueRouter)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
