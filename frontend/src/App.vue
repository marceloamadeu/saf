<template>
  <component v-bind:is="layout"></component>
  <!-- <admin /> -->
</template>

<script>
//import FruitApi from '@/api/FruitAPI';
//import FruitApi from '@/api/comum';
//import Fruit from '@/view/Fruit';
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
  /*
  ,
  data() {
    return {
      fruits: []
    }
  },
  created() {
    FruitApi.getFruits(fruits => {
      this.fruits = fruits.data.data.results;
    })
  }
  */
  
}
</script>