export default {

  state: {    
    fruits: [],
    fruit: {}, 
    count: 0,   
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
    },
    setCount (state) {
      state.count++
    }
  },
  

  getters: {
    getFruit(state) {
      return state.fruit
    },
    getFruits(state) {
      return state.fruits
    },    
    getMessages(state) {
      return state.messages
    },
    getCount(state) {
      return state.count
    }
  },

  actions: {    
    newFruit (state) {           
      state.commit('setFruit', {})      
    },
    increment (state) {           
      state.commit('setCount');
    },
    limparMensagens (state) {
      state.commit('setMessages', Object)
    }
  },

}
