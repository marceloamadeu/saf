import Vue from 'vue'
import Vuex from 'vuex'

Vue.config.devtools = true
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    layout: 'loading', // loading, admin, blank
    theme: localStorage.getItem(process.env.VUE_APP_CLIENT_ID + '-theme') || '',
   
    fruits: [],
    messages: Object

  },
  mutations: {
    setLayout (state, payload) {
      state.layout = payload
    },
    setTheme (state, payload) {
      state.theme = payload
      localStorage.setItem(process.env.VUE_APP_CLIENT_ID + '-theme', payload)
    },

    setFruit (state, payload) {
      state.fruit = payload
    },
    setFruits (state, payload) {
      state.fruits = payload
    }

  },

  getters: {
    getLayout (state) {
      return state.layout
    },
    getTheme (state) {
      return state.theme
    },
    
    getFruit (state) {
      return state.serie
    },
    getFruits (state) {
      return state.Fruits    
    }

  },


  actions: {


    limparMensagens (state) {
      state.commit('setMessages', Object)
    }

  },
  modules: {
  }
})
