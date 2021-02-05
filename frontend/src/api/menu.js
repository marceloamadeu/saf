/* eslint-disable no-array-constructor */

export default {
  itens: [

    {
      name: 'Inicial',
      url: '/home',
      icon: 'fas fa-chart-line',
    },

    
    {
      name: 'Frutas',
      url: '/fruits',
      icon: 'fas fa-chalkboard',      
    },

    {
      name: 'Frutas Teste',
      url: '/fruits_test',
      icon: 'fas fa-chalkboard',      
    },
    

  ],



  itensAcesso (itens) {
    var i
    var permitidos = new Array()

    for (i = 0; i < itens.length; i++) {
      permitidos.push(itens[i])
    }

    return permitidos
  },

}
