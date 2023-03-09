import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import {createPinia} from 'pinia'

Vue.config.productionTip = false
Vue.use(createPinia())

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
