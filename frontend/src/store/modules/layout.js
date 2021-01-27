import listaMenu from '@/api/menu'

export default {

  state: {
    layout: 'loading', // loading, admin, blank
    theme: localStorage.getItem(process.env.VUE_APP_CLIENT_ID + '-theme') || '',
    sidebar: '',
    menu: listaMenu.intens,
    menuOriginal: []
  },

  mutations: {
    setLayout (state, payload) {
      state.layout = payload
    },
    setTheme (state, payload) {
      state.theme = payload
      localStorage.setItem(process.env.VUE_APP_CLIENT_ID + '-theme', payload)
    },
    setSidebar (state, payload) {
      state.sidebar = payload
    },
    setMenu (state, payload) {
      state.menu = payload
    },
    setMenuOriginal (state, payload) {
      state.menuOriginal = payload
    }

  },

  getters: {
    getLayout (state) {
      return state.layout
    },
    getTheme (state) {
      return state.theme
    },
    getSidebar (state) {
      return state.sidebar
    },
    getMenu (state) {
      return state.menu
    },
    getMenuOriginal (state) {
      return state.menuOriginal
    }
  }

}
