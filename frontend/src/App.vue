<template>
  <component v-bind:is="layout"></component>
  <!-- <admin /> -->
</template>

<script>
//import FruitApi from '@/api/FruitAPI';
import FruitApi from '@/api/comum';
import Fruit from '@/components/Fruit';

export default {
  name: 'App',
  components: {
    Fruit
  },
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
}
</script>