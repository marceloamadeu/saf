<template>
  <div class="fluid">
      
    TESTE
      
  </div>
</template>



<script>

import UtilAPI from '@/api/UtilAPI';

export default {
  name: 'fruit',    
  data() {
    return {
      fruits: []
    }
  },
  created() {
    this.listFruits()    
  },
  mounted () {
    this.listFruits()
  },

  methods: {
    listFruits () {
      UtilAPI.getFruits(fruits => {
        this.fruits = fruits.data.data.results;
      })
      .catch(err => {
        this.$store.commit('setMessages', err.response.data)
      })
    },
  }
}
</script>

<style>
</style>
