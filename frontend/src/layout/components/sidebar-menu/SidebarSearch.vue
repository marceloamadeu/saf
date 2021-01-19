<template>
          <div id="sidebar-search">
          <b-form inline>
            <span class="sidebar-search-icon"><i class="fas fa-search"></i></span>
            <b-input id="txt-sidebar-search" :maxlength="maxlength" autocomplete="off" v-model="searchTerm"  placeholder="Filtrar opções" @input="filtrar()"></b-input>
            <span id="btn-sidebar-search-erase" v-if="searchTerm.length > 0" @click="searchTerm = '',filtrar()"><i class="fas fa-times"></i></span>
            <!-- <button id="btn-sidebar-search-erase" v-if="searchTerm.length > 0"><i class="fas fa-times"></i></button> -->
          </b-form>
        </div>
</template>

<script>
// import SearchIcon from

import menu from '@/api/menu'

export default {
  props: {
    maxlength: Number
  },
  data () {
    return {
      searchTerm: ''
    }
  },
  methods: {

    filtrar () {
      this.$store.commit('setMenu', menu.filtrar(this.$store.getters.getMenuOriginal, this.searchTerm))
    }
  }

}
</script>

<style>

</style>
