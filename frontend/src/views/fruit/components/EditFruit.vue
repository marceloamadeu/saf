<template>
  <div class="fluid">
    <h1 v-if="fruit.id" class="app-title">Alterar Fruta</h1>
    <h1 v-else class="app-title">Cadastrar Fruta</h1>
    
    <b-row>
      <b-col md="12">
        <b-card>
          <div slot="header">
            <b>Informações da Fruta</b>
            <b-badge variant="mute" class="float-right small">(*) Obrigatório</b-badge>
          </div>
          
          <b-form >
            <!-- Nome -->
            <b-row>
              <b-col lg="6" sm="12">
                <b-form-group
                  id="name-group"
                  label="Nome:"
                  label-for="name"
                  class="text-label required"                  
                >
                
                  <b-form-input
                    id="name"
                    v-model="fruit.name"                 
                    placeholder="Digite o Nome da Fruta"                      
                    required
                  ></b-form-input>                                     
                  
                
                </b-form-group>
              </b-col>
            </b-row>

          </b-form>
          

          <div slot="footer" class="center-xy">
            <b-button v-if="fruit.id" @click="alterarFruit()" variant="primary" size="md">
              <i class="far fa-save"></i> Alterar
            </b-button>

            <b-button v-else @click="salvarFruit()" variant="primary" size="md">
              <i class="far fa-save"></i> Salvar
            </b-button>

            <b-button outline @click="clear()" size="md" variant="secondary">Limpar</b-button>
          </div>

        </b-card>
      </b-col>
    </b-row>

  </div>
</template>

<script>

import UtilAPI from '@/api/UtilAPI'
import events from '@/util/events'

export default { 
  name: 'EditarFruit',
  data () {
    return {
      valid: false,
      error: Object
    }
  },
  computed: {
    fruit: {
      get () {
        return this.$store.getters.getFruit
      },
      set () {
        this.$store.commit('setFruit', this.fruit)
      }
    }
  },
  methods: {
    salvarFruit () {      
      UtilAPI.salvarFruit(this.fruit)
        .then(() => {
          events.$emit('fruitAlterada', this.fruit)
          this.clear()
          this.$store.commit('setMessages', { 
            message: 'Sucesso ao cadastrar a Fruta', 
            variant: 'success' 
          })              
        }).catch(err => {
          this.$store.commit('setMessages', err.response.data)
        })
    },
    alterarFruit () {                      
      UtilAPI.alterarFruit(this.fruit)
        .then(() => {
          events.$emit('fruitAlterada', this.fruit)
          this.clear()
          this.$store.dispatch('newFruit')
          this.$store.commit('setMessages', { 
            message: 'Sucesso ao alterar fruta', 
            variant: 'success' })
        }).catch(err => {
          this.$store.commit('setMessages', err.response.data)
        })      
    },
    clear() {      
      this.$store.dispatch('newFruit')
      this.$store.dispatch('limparMensagens')
    }
  }
}

</script>

<style>
</style>
