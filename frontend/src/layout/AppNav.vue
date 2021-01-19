<template>
  <!-- <nav class="sidebar-nav" :class="sidebar"> -->
    <!-- <TreeMenu :node="menu" @onClick="nodeWasClicked"></TreeMenu> -->
  <!-- </nav> -->

<SidebarMenu />
</template>

<script>
import SidebarMenu from './components/sidebar-menu/SidebarMenu.vue'

export default {
  components: {
    SidebarMenu
  },
  data () {
    return {
      // menu
    }
  },
  methods: {
    nodeWasClicked (node) {
      // node.
      alert(node.title);
    }
  }
}
</script>

<style>
</style>
