<template>
  <router-view></router-view>
</template>

<script>
export default {
  name: 'Blank',
  created () {
    this.$store.commit('setLayout', 'blank')
  }
}
</script>

<style>
</style>
