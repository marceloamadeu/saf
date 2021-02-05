import Vue from 'vue'
import Vuex from 'vuex'
import exemplo from './modules/exemplo.js'
import layout from './modules/layout.js'

Vue.config.devtools = true
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    exemplo,
    layout
  }
})