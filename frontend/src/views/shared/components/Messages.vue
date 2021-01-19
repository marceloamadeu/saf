<template>
  <div id="messages" v-if="messages !== null">
    <br v-if="messages.message" />
    <b-alert
      v-model="showDismissibleAlert"
      :variant="defineVariant"
      dismissible
    >
      <i :class="getIcon"></i>
      {{ messages.message }}

      <p class="mb-0" v-if="messages.errors" />

      <hr v-if="messages.variant == 'danger' || messages.errors != undefined" />
      <p v-for="e in messages.errors" :key="e.message">
        {{ e.message }} {{ e.code ? "(" + e.code + ")" : "" }}
      </p>
    </b-alert>
  </div>
</template>
<script>
export default {
  name: 'Messages',
  computed: {
    messages: {
      get () {
        return this.$store.getters.getMessages
      }
    },
    defineVariant () {
      if (this.messages.variant != null) {
        return this.messages.variant
      } else {
        return 'danger'
      }
    },
    showDismissibleAlert: {
      get () {
        if (this.messages.message) {
          return true
        } else {
          return false
        }
      },
      set () {
        this.$store.commit('setMessages', '')
        return true
      }
    },
    getIcon () {
      var icon
      switch (this.messages.variant) {
        case 'warning':
          icon = 'fas fa-exclamation-triangle'
          break
        case 'danger':
          icon = 'fas fa-exclamation-circle'
          break
        case 'info':
          icon = 'fas fa-info-circle'
          break
        case 'success':
          icon = 'fas fa-check-circle'
          break
        default:
          icon = 'fas fa-exclamation-circle'
      }
      return icon
    }
  }
}
</script>
