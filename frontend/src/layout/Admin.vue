<template>
  <div id="app" :class="getTheme">
    <AppHeader  />
    <div id="content-wrapper" :class="sidebar">
      <AppNav />
      <AppMain />
      <AppFooter />
    </div>
  </div>
</template>

<script>
import AppHeader from '@/layout/AppHeader.vue'
import AppNav from '@/layout/AppNav.vue'
import AppMain from '@/layout/AppMain.vue'
import AppFooter from '@/layout/AppFooter.vue'

export default {
  components: { AppHeader, AppNav, AppMain, AppFooter },
  computed: {
    sidebar () {
      return this.$store.getters.getSidebar
    },
    getTheme () {
      return this.$store.getters.getTheme
    }
  }
}
</script>
