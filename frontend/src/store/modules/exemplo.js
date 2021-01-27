export default {

  state: {    
    fruits: [],
    fruit: {},    
    messages: Object
  },

  mutations: {
    setFruit(state, payload) {
      state.fruit = payload
    },
    setFruits(state, payload) {
      state.fruits = payload
    },    
    setMessages (state, payload) {
      state.messages = payload
    }
  },
  

  getters: {
    getFruit(state) {
      return state.fruit
    },
    getFruits(state) {
      return state.fruits
    },    
    getMessages (state) {
      return state.messages
    }
  },

  actions: {    
    newFruit (state) {
      state.commit('setFruit', {})
    },
    limparMensagens (state) {
      state.commit('setMessages', Object)
    }
  }

}
