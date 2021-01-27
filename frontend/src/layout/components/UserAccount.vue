/* eslint-disable no-unused-vars */
<template>
  <div class="dont-print cel-user-account">
    <div v-click-outside="hide">
      <img
        :src="user.photo"
        alt="Foto do usuário"
        class="user-photo"
        @click="openMenu"
      />
      <span class="sr-only">Menu do usuário</span>

      <transition name="slide-fade">
        <div class="user-menu" v-if="showMenu">
          <div class="header no-gutters">
            <img
              :src="user.photo"
              alt="Foto do usuário"
              class="user-photo-menu"
            />
            <div class="id-container">
              <div class="username">{{ nomeUsuario() }}</div>
            </div>
          </div>
          <div class="user-menu-body">
            <ul>
              <li>
                <a href="#" @click="perfil">
                  <div class="i-block">
                    <i class="fas fa-id-badge"></i>
                  </div>
                  Perfil
                </a>
              </li>
              <li>
                <a href="#" @click="dark">
                  <div class="i-block" v-show="!darkTheme">
                    <i class="fas fa-moon"></i>
                  </div>
                  <div class="i-block" v-show="darkTheme">
                    <i class="fas fa-sun"></i>
                  </div>
                  Tema {{ toggleThemeLabel }}
                </a>
              </li>
              <li>
                <a href="#" @click="sair">
                  <div class="i-block">
                    <i class="fas fa-sign-out-alt"></i>
                  </div>
                  Sair
                </a>
              </li>
            </ul>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import ClickOutside from 'vue-click-outside'
// eslint-disable-next-line no-unused-vars
import store from '@/store/store'

import { mediaBreakpoint } from '@/assets/defs.js'

export default {
  //name: 'UserAccount',
  data () {
    return {
      darkTheme: this.$store.getters.getTheme === 'dark',
      showMenu: false,
      user: {
        name: 'Ana Carolina Rodrigues de Almeida Peixoto Albuquerque',
        profile: 'Administradores',
        photo: require('@/assets/images/user-photo.png')
      }
    }
  },
  methods: {
    print () {
      window.print()
    },
    openMenu () {
      if (screen.width <= mediaBreakpoint.sm) {
        this.$store.commit('setSidebar', 'sidebar-contracted')
        this.$store.commit('setUserMenu', true)
      }
      this.showMenu = !this.showMenu
    },
    hide () {
      this.showMenu = false
    },
    sair () {
      this.showMenu = false
      this.$store.commit("limparJwtTokenState")
      window.location.href = 'index.html'
      //window.location.href = apiCentralSeguranca.authorizeUrl + '&force_login=true'
      
    },
    dark () {
      let theme = this.$store.getters.getTheme === '' ? 'dark' : ''
      this.darkTheme = theme === 'dark'
      this.$store.commit('setTheme', theme)
      this.hide()
    },
    perfil () {
      window.open(process.env.VUE_APP_PERFIL_URL, '_blank')
    },
    nomeUsuario () {
      return 'Eu...'
      //apiCentralSeguranca.decodeJwtToken(this.$store.getters.getJwtToken).name
    }
  },
  computed: {
    toggleThemeLabel () {
      return this.darkTheme ? 'Padrão' : 'Escuro'
    },
    toggleThemeIcon () {
      return this.darkTheme ? 'fas fa-sun' : 'fas fa-moon'
    }
  },
  directives: {
    ClickOutside
  }
}
</script>

<style scoped></style>
