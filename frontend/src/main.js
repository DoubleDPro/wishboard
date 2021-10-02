import Vue from 'vue'
import VueCookies from 'vue-cookies'
import App from './Home.vue'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false
Vue.use(VueCookies)

new Vue({
  vuetify,
  render: h => h(App),
}).$mount('#home')
