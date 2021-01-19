<template>

    <b-card-body class="submenu">
      <b-card-text>
        <ul>
          <li v-for="item in itens" :key="item.name" @click="hideSidebar">
            <router-link :to="item.url">{{item.name}}</router-link>
          </li>
        </ul>
      </b-card-text>
    </b-card-body>

</template>

<script>
import { mediaBreakpoint } from '@/assets/defs.js'
export default {
  props: {
    itens: Array
  },
  methods: {
    hideSidebar () {
      if (screen.width <= mediaBreakpoint.sm) {
        this.$store.commit('setSidebar', 'sidebar-contracted')
      }
    }
  }

}
</script>

<style>
</style>
