<template>

    <div class="fluid">

    <h1 class="app-title">Dashboard</h1>
     
</div>

</template>

<script>
import VueApexCharts from 'vue-apexcharts'
import UtilAPI from '@/api/UtilAPI'

export default {
  name: 'Home',
  components: {
    VueApexCharts
  },
  data: function () {
    return {
      matriculas: [],
      // bar
      options: {
        xaxis: {
          categories: []
        },
        responsive: [
          {
            breakpoint: 10000,
            options: {
              plotOptions: {
                bar: {
                  horizontal: false
                }
              },
              legend: {
                position: 'bottom'
              }
            }
          }
        ]
      },
      series: [{
        name: '',
        data: [],
        labels: []
      }],
      // pie/donut
      donutChartOptions: {
        labels: [],
        responsive: [
          {
            breakpoint: 10000,
            options: {
              plotOptions: {
                pie: {
                  horizontal: false
                },
                donut: {
                  horizontal: false
                }
              },
              legend: {
                position: 'bottom'
              }
            }
          }
        ]
      },
      donutChartData: []
    }
  },
  created () {
    this.listFruits()
  },
  methods: {
    listFruits () {
      UtilAPI.getFruits()
        .then(res => {
          this.fruits = res.data
          this.prepareChats()
        })
        .catch(err => {
          this.$store.commit('setMessages', err.response.data)
        })
    },    
  }
}
</script>
