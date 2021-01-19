<template>
  <div class="sidebar-wrapper">
    <nav id="sidebar">
      <div class="sidebar-content">
         <SidebarSearch :maxlength="30" />
        <div class="sidebar-menu mt-2">
          <div role="tablist">
            <!-- <h3 class="header-menu">Opções</h3> -->
            <Item :menu="item" v-for="item in itens" :key="item.name" />
          </div>
        </div>
        <!-- sidebar-menu  -->
      </div>
      <!-- sidebar-content  -->
    </nav>
  </div>
</template>

<script>
import menu from '@/api/menu'
import Item from './Item'
import SidebarSearch from '@/layout/components/sidebar-menu/SidebarSearch'
export default {
  components: { Item, SidebarSearch },
  computed: {
    itens () {
      return this.$store.getters.getMenu
    }
  },
  beforeCreate () {
    this.$store.commit('setMenu', menu.itensAcesso(menu.itens))
    this.$store.commit('setMenuOriginal', menu.itensAcesso(menu.itens))
  }
}
</script>

<style>
</style>
