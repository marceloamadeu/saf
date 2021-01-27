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
          :busy="pesquisando"
        >
          <div slot="empty" colspan="2" align="center">Não existe conteúdo a ser exibido</div>
             <div slot="table-busy" class="text-center text-danger my-2">
          <b-spinner class="align-middle"></b-spinner>
          <strong> Pesquisando...</strong>
        </div>
         
        </b-table>        

      </b-card>
  </div>
</template>



<script>


import UtilAPI from '@/api/UtilAPI'


export default {
  name: 'ListaFruit',
  data () {
    return {
      fields: [
        { label: 'Name', key: 'name', sortable: true, sortDirection: 'name' },
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
  }
}
</script>

<style>
</style>
