import Vue from 'vue'
import App from './App.vue'
import router from '@/router/router'
import store from '@/store/store'
import BootstrapVue from 'bootstrap-vue'
import * as VeeValidate from 'vee-validate';
import { ValidationProvider, ValidationObserver } from 'vee-validate';
import ptBR from 'vee-validate/dist/locale/pt_BR'
import VueI18n from 'vue-i18n'
import VueTheMask from 'vue-the-mask'
import PortalVue from 'portal-vue'
import BackToTop from 'vue-backtotop'

Vue.use(VueTheMask)
Vue.use(PortalVue)

Vue.component('ValidationProvider', ValidationProvider);
Vue.component('ValidationObserver', ValidationObserver);

Vue.use(VueI18n)
const i18n = new VueI18n({
  locale: 'ptBR'
})

Vue.use(VeeValidate, {
  fieldsBagName: 'veeFields',
  i18n,
  dictionary: {
    ptBR
  }
})

Vue.use(BootstrapVue)
Vue.use(BackToTop)

Vue.config.productionTip = false
Vue.config.devtools = true

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
