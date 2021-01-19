<template>
  <header>
    <div class="app-header">
      <b-link class="navbar-brand" to="/home">
        <div class="navbar-brand-full"></div>
        <div class="print-header print" >
          <img src="https://www.pr.gov.br/logos/governo2019_145x63px.png"  alt />
          <img src="https://www.pr.gov.br/logos/governo2019_145x63px.png" alt />
        </div>
      </b-link>

      <b-button id="toggle-sidebar-lg" variant="outline" @click="toggleSidebar">
        <i class="fas fa-bars"></i>
      </b-button>
      <b-button id="toggle-sidebar-sm" variant="primary" @click="toggleSidebarSm">
        <i class="fas fa-bars"></i>
      </b-button>

      <ul id="identification" class="identification">
        <li v-if="AppInfo.customer.showInHeader" class="client-name">{{AppInfo.customer.name}}</li>
        <li class="application-name">{{AppInfo.name}}</li>
      </ul>

      <b-navbar-nav class="ml-auto">
        <UserAccount />
      </b-navbar-nav>
    </div>
  </header>
</template>

<script>

export default {  

  methods: {
    toggleSidebar () {
      let sidebar =
        this.$store.getters.getSidebar === 'sidebar-expanded'
          ? 'sidebar-contracted'
          : 'sidebar-expanded'
      this.contracted = sidebar === 'sidebar-contracted'
      this.$store.commit('setSidebar', sidebar)
    },
    toggleSidebarSm () {}
  }
}
</script>

<style scoped>
</style>
