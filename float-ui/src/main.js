import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false

Vue.prototype.$http = axios

Vue.use(ElementUI,axios)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
