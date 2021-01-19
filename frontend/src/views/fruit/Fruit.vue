<script>

import FruitApi from '@/api/FruitAPI';

export default {
  name: 'fruit',    
  created() {
    FruitApi.getFruits()
  }
}
</script>

<style>
</style>
