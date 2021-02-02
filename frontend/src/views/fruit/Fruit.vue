<template>
  <div class="fluid">
      
    <EditFruit/>
    <ListFruit/>
     
  </div>
</template>


<script>

import EditFruit from './components/EditFruit'
import ListFruit from './components/ListFruit'

export default {
  name: 'Fruit', 
  components: { ListFruit, EditFruit }   
}
</script>

<style>
</style>
