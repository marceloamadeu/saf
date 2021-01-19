<template>
  <div class="fluid">
      <b-card>

        <b-table
          show-empty
          stacked="sm"
          hover="hover"
          striped="striped"
          fixed
          :items="series"
          :fields="fields"
          :current-page="currentPage"
          :per-page="0"
          @sort-changed="sortingChanged"
          :busy="pesquisando"
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
            <b-link title="Excluir" @click="deletarSerie(data.item)" class="btn btn-outline-danger">
              <i class="fas fa-trash-alt"></i>
            </b-link>
            </div>
          </template>
        </b-table>

        <b-row v-if="series.length > 0" class="my-1 center-xy">
            <b-pagination
              v-model="currentPage"
              :total-rows="totalRows"
              :per-page="perPage"
              :length="currentPage"
              class="text-label"
              @input="changePage"
            ></b-pagination>
            <p
              class="VuePagination__count text-center col-md-12"
            >Mostrando {{currentPage * perPage - perPage +1}} a {{ (currentPage * perPage) > totalRows ? totalRows : currentPage * perPage}} de {{totalRows}} registros</p>
        </b-row>

      </b-card>
  </div>
</template>



<script>


import ApiComum from '@/api/FruitAPI'


export default {
  name: 'ListaFruit',
  data () {
    return {
      fields: [
        { label: 'Nome', key: 'nome', sortable: true, sortDirection: 'desc' },
        { key: 'acoes', label: 'Ações' }
      ],
      totalRows: 1,
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15],
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
    }
  },
  
  mounted () {
    this.listFruit()
  },
  methods: {    
    listFruit () {
      this.pesquisando = true
      ApiComum.getFruits(this.currentPage, this.perPage)
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
  }
}
</script>

<style>
</style>
