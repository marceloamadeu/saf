<template>
<div class="app-main">

  <main>
      <div v-if="messages">
        <Messages />
      </div>

      <router-view />
  </main>
  <back-to-top bottom="0" right="0">
      <button type="button" class="btn btn-info btn-to-top">
        <i class="fa fa-chevron-up"></i>
      </button>
    </back-to-top>
</div>

</template>

<script>
import Messages from '@/views/shared/components/Messages'
export default {
  components: { Messages },
  computed: {
    messages: {
      get () {
        return this.$store.getters.getMessages
      }
    }
  }
}
</script>

<style>
</style>
