<template>
  <AppHeaderDropdown right no-caret>
    <template slot="header">
       <span class="nome-usuario">  {{nomeUsuario()}} </span>
       <img src="@/assets/img/avatar.png" class="img-avatar" alt="admin@pr.gov.br.com">
    </template>

    <template slot="dropdown">
      <b-dropdown-header tag="div" class="text-center">
        <strong>Conta</strong>
      </b-dropdown-header>

      <b-dropdown-item @click="perfil">
        <i class="fa fa-user"/> Perfil
      </b-dropdown-item>

      <b-dropdown-divider/>
      <b-dropdown-item @click="sair">
        <i class="fa fa-lock"/> Sair
      </b-dropdown-item>
    </template>
  </AppHeaderDropdown>
</template>

<script>
import { HeaderDropdown as AppHeaderDropdown } from "@coreui/vue";
//import centralSeguranca from "@/api/centralseguranca";

export default {
  name: "ContaUsuario",
  components: {
    AppHeaderDropdown
  },
  methods:{
    perfil(){
      window.open(process.env.VUE_APP_PERFIL_URL, '_blank');
    },
    sair(){
      this.$store.commit("limparUserState");
      this.$store.commit("limparCidadao");
      //window.location.href = centralSeguranca.authorizeUrl+'&force_login=true';
      window.location.href = "index.htm"
    },
    nomeUsuario() {
      this.$store.dispatch("obterCidadao");
      let cidadao = (this.$store.getters.getCidadao);
      return cidadao.nome;
    }
  }
};
</script>

<style scoped>
   @media (max-width: 586px) {                  
    .nome-usuario {
        display: none;
    }
   }
</style>

