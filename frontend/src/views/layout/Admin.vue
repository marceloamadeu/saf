<template>
  <div class="app" v-if="autenticado">
    <notifications group="notificacao" />
   
    <AppHeader fixed>
      <SidebarToggler class="d-lg-none" display="md" mobile />
      <b-link class="navbar-brand" to="/home">
        <img class="navbar-brand-full" src="@/assets/img/brand/logo.png" width="104" height="46" alt="Logo Paraná">
        <img class="navbar-brand-minimized" src="@/assets/img/brand/brasao.png" width="30" height="30" alt="Logo Paraná">
      </b-link>
      <SidebarToggler class="d-md-down-none" display="lg" />
      <ul class="identificacao">        
       <li class="nome-cliente">(DETRAN)</li>
       <li class="nome-sistema">Sistema Digital de Saúde</li>
      </ul>
  
      <b-navbar-nav class="ml-auto">
        <CertificadoDigital/>
      </b-navbar-nav>
       <b-navbar-nav>
          <ContaUsuario/>
      </b-navbar-nav>
    </AppHeader>

    <div class="app-body" >
      <!-- <loading :active.sync="isLoading" 
        :can-cancel="true" 
        :on-cancel="onCancel"
        :is-full-page="fullPage">
      </loading> -->

      <AppSidebar fixed>
        <SidebarHeader/>
        <SidebarForm/>
        
        <SidebarNav :navItems="nav"></SidebarNav>
        <SidebarFooter/>
        <SidebarMinimizer/>
      </AppSidebar>
      <main class="main">
        <Breadcrumb :list="list"/>
        <div class="container-fluid">
          <Messages :errors="errors" />
          <router-view></router-view>
        </div>
      </main>
    </div>
    
    <TheFooter>
      <div class="footer-celepar"> <span class="ml-1">Desenvolvido pela </span>
        <a href="http://www.celepar.pr.gov.br/" target="_blank"><img src="https://www.pr.gov.br/logos/logo_celepar_96x34.png"/></a>
      </div>
      <div class="ml-auto">
        <router-link to="/info"> Informações Legais </router-link>
      </div>
    </TheFooter>
  </div>
</template>

<script>
import menu from '@/api/menu'
import { 
  Header as AppHeader, 
   SidebarToggler, 
   Sidebar as AppSidebar, 
   SidebarFooter, 
   SidebarForm, 
   SidebarHeader, 
   SidebarMinimizer, 
   SidebarNav,
   Footer as TheFooter, 
   Breadcrumb} from '@coreui/vue';
import Messages from '@/views/shared/components/Messages';
import CertificadoDigital from "../certificadodigital/CertificadoDigital.vue";
import ContaUsuario from '@/views/layout/ContaUsuario';
// import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';

export default {
  name: 'Admin',
  components: {
    AppHeader,
    AppSidebar,
    TheFooter,
    Breadcrumb,
    SidebarForm,
    SidebarFooter,
    SidebarToggler,
    SidebarHeader,
    SidebarNav,
    SidebarMinimizer,
    Messages,
    CertificadoDigital,
    ContaUsuario
    // Loading
  },
  data () {
    return {
      isLoading: false,
      fullPage: true
    }
  },
  computed: {
    nav(){
      var menus = menu.itensAcesso(menu.itens)
      return menus
    },
    name () {
      return this.$route.name;
    },
    list () {
      return this.$route.matched.filter((route) => route.name || route.meta.label);
    },
    autenticado(){
      return this.$store.getters.isAuthenticated;
    }
  }
}
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

<style>
.radio-toolbar input[type="radio"] {
    display: none;
}

.radio-toolbar label {
    display: inline-block;
    padding: 4px 11px;
    font-family: Arial;
    font-size: 16px;
    cursor: pointer;
    color: #007bff;
    border-color: #007bff;
    border: 1px solid;
    border-radius: 0.3rem;
}
.radio-toolbar > span:nth-child(1) > span:first-child > label:nth-child(2) {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
}
.radio-toolbar > span:nth-child(1) > span:not(:first-child):not(:nth-last-child(2)) > label:nth-child(2) {
   border-radius: 0;
}
.radio-toolbar > span:nth-child(1) > span:nth-last-child(2) > label:nth-child(2) {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
}
.radio-toolbar input[type="radio"]:checked + label {
    color: #fff;
    background-color: #007bff;
    border-color: #007bff;
}
</style>

<style lang="scss">
  // CoreUI Icons Set
  @import '~@coreui/icons/css/coreui-icons.min.css';
  /* Import Font Awesome Icons Set */
  $fa-font-path: '~font-awesome/fonts/';
  @import '~font-awesome/scss/font-awesome.scss';
  /* Import Simple Line Icons Set */
  $simple-line-font-path: '~simple-line-icons/fonts/';
  @import '~simple-line-icons/scss/simple-line-icons.scss';
  /* Import Flag Icons Set */
  @import '~flag-icon-css/css/flag-icon.min.css';
  /* Import Bootstrap Vue Styles */
  @import '~bootstrap-vue/dist/bootstrap-vue.css';
  // Import Main styles for this application
  @import '../../assets/scss/style';

</style>

