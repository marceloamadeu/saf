<template>
  <div class="d-flex align-items-stretch">
      <b-row class="justify-content-center">
        <b-col md="6">
          <div class="clearfix">
            <h1 class="float-left display-3 mr-4">403</h1>
            <h4 class="pt-3">Acesso Negado!</h4>
            <p class="text-muted">O recurso requisitado não pode ser acessado.</p>
            <b-button @click="goHome" variant="info">Página inicial</b-button>
          </div>
        </b-col>
      </b-row>
  </div>
</template>

<script>
export default {
  name: 'Deny',
  methods: {
    goHome () {
      window.location.href = '/'
    }
  }
}
</script>
<style scoped lang="css">
  h1 {
    font-size: 150px;
    line-height: 150px;
    font-weight: 700;
    color: #252932;
    text-shadow: rgba(61, 61, 61, 0.3) 1px 1px, rgba(61, 61, 61, 0.2) 2px 2px, rgba(61, 61, 61, 0.3) 3px 3px;
  }
</style>
