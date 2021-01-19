<template>

  <div class="app d-flex flex-row justify-content-center align-items-center loading">
    <b-spinner style="width: 3rem; height: 3rem;" variant="primary" label="Carregando a aplicação"></b-spinner>
  </div>

</template>

<script>
export default {
  name: 'Loading'
}
</script>

<style >
.loading{
  position: absolute;
  height: 100%;
  width: 100%;
  background: $body-bg;
}

</style>
