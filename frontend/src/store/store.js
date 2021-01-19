import Vue from 'vue'
import Vuex from 'vuex'

Vue.config.devtools = true
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  
    fruits: [],
    messages: Object

  },
  mutations: {

    setFruit (state, payload) {
      state.fruit = payload
    },
    setFruits (state, payload) {
      state.fruits = payload
    }

  },

  getters: {
    
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
