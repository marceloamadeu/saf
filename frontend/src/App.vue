<template>
  <component v-bind:is="layout"></component>
  <!-- <admin /> -->
</template>

<script>
import Admin from '@/layout/Admin.vue'
import Loading from '@/layout/Loading'
import Blank from '@/layout/Blank'

export default {
  name: 'App',
  computed: {
    layout () {
      return this.$store.getters.getLayout
    }
  },
  components: {    
    'admin': Admin,
    'loading': Loading,
    'blank': Blank,
  }  
}
</script>

<style lang='scss'>
@import "@/assets/scss/style.scss";
</style>
