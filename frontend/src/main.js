import Vue from 'vue'
import VueCookies from 'vue-cookies'
import App from './App.vue'

Vue.config.productionTip = false
Vue.use(VueCookies)

new Vue({
  render: h => h(App),
}).$mount('#app')
