<template>
  <div class="fluid">
      <b-card>

        <b-table
          show-empty
          stacked="sm"
          hover="hover"
          striped="striped"
          fixed
          :items="fruits"
          :fields="fields"
          :current-page="currentPage"
          :per-page="0"
          @sort-changed="sortingChanged"
          :busy="pesquisando"
          :tbody-transition-props="transProps"
        >
          <div slot="empty" colspan="2" align="center">Não existe conteúdo a ser exibido</div>
          <div slot="table-busy" class="text-center text-danger my-2">
            <b-spinner class="align-middle"></b-spinner>
            <strong> Pesquisando...</strong>
          </div>    

          <template v-slot:cell(acoes)="data">
            <div class="d-flex justify-content-end">
              <b-link title="Alterar" @click="iniciarEditar(data.item)" class="btn btn-outline-info">
                <i class="fas fa-pencil-alt"></i>
              </b-link>&nbsp;
              <b-link title="Excluir" @click="deletarFruit(data.item)" class="btn btn-outline-danger">
                <i class="fas fa-trash-alt"></i>
              </b-link>
            </div>
          </template>
        </b-table>  

        <b-row v-if="fruits.length > 0" class="my-1 center-xy">
          <b-pagination
            v-model="currentPage"
            :total-rows="totalRows"
            :per-page="perPage"
            :length="currentPage"
            class="text-label"
            @input="changePage"
          />
          <p class="VuePagination__count text-center col-md-12">
            Mostrando {{currentPage * perPage - perPage +1}} a {{ (currentPage * perPage) > totalRows ? totalRows : currentPage * perPage}} de {{totalRows}} registros
          </p>
          
        </b-row>

    </b-card>
  </div>
</template>

<script>

import UtilAPI from '@/api/UtilAPI'
import events from '@/util/events'

export default {
  name: 'ListaFruit',
  data () {
    return {
      fields: [
        { label: 'Name', key: 'name', sortable: true, sortDirection: 'asc' },
        { key: 'acoes', label: 'Ações' }
      ],
      totalRows: 1,
      currentPage: 1,
      perPage: 10,
      pageOptions: [10, 20, 30],
      sortBy: null,
      sortDesc: false,
      sortDirection: 'asc',
      filter: null,
      pesquisando: false
    }
  },
  computed: {
    fruits: {
      get () {
        return this.$store.getters.getFruits
      },
      set () {
        this.$store.commit('setFruits', this.fruits)
      }
    },
    fruit: {
      get () {
        return this.$store.getters.getFruit
      },
      set () {
        this.$store.commit('setFruit', this.fruit)
      }
    },
    count: {
      get () {
        return this.$store.getters.getCount
      },
      set () {
        this.$store.commit('setCount', this.count)
      }
    }
  },
  created () {
    events.$on('fruitAlterada', () => {
      this.listFruit()
    })
  },
  mounted () {
    this.listFruit()
  },
  methods: {       
    iniciarEditar (fruit) {
      let obj = JSON.parse(JSON.stringify(fruit))            
      this.$store.commit('setFruit', obj)            
    },    
    deletarFruit(fruit) {
      UtilAPI.deletarFruit(fruit)
        .then(() => {
          this.currentPage = 1
          this.perPage = 5
          this.listFruit()
          this.$store.commit('setMessages', {
            message: 'Sucesso ao excluir Fruit',
            variant: 'success'
          })
        })
        .catch(err => {
          this.$store.commit('setMessages', err.response.data)
        })
    },
    listFruit () {
      this.pesquisando = true
      UtilAPI.getFruits(this.currentPage, this.perPage)
        .then(res => {
          this.$store.commit('setFruits', res.data)
          this.totalRows = res.headers['pagination-count']
        })
        .catch(err => {
          this.$store.commit('setFruits', [])
          this.$store.commit('setMessages', err.response.data)
        })
      this.pesquisando = false
    },   
    changePage () {
      UtilAPI.getFruits(
        this.currentPage,
        this.perPage,
        this.sortBy,
        this.sortDesc
      ).then(res => {
        this.$store.commit('setFruits', res.data)
        this.totalRows = res.headers['pagination-count']
      })
    },
    sortingChanged (ctx) {
      this.sortBy = ctx.sortBy
      this.sortDesc = ctx.sortDesc
      UtilAPI.getFruits(
        this.currentPage,
        this.perPage,
        this.sortBy,
        this.sortDesc
      ).then(res => {
        this.$store.commit('setFruits', res.data)
        this.totalRows = res.headers['pagination-count']
      })
    } 
  }
}
</script>

<style>
</style>
