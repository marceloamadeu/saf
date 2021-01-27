<template>
  <div class="fluid">
      
    <ListFruit/>
      
  </div>
</template>



<script>

//import UtilAPI from '@/api/UtilAPI';
import ListFruit from './components/ListFruit'

export default {
  name: 'fruit', 
  components: { ListFruit }   
}
/*
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
*/
</script>

<style>
</style>
