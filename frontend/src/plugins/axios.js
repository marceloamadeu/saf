import axios from 'axios'
import store from '@/store/store'

const http = axios.create({
      baseURL: process.env.VUE_APP_API
  })

http.interceptors.request.use(req =>{
    return checkAuthorization(req)
}, error =>{
  Promise.reject(error)
})

http.interceptors.response.use(resp =>{
  return resp
}, error =>{
  checkError(error)
  Promise.reject(error)
})


function checkAuthorization(req){   
  console.log('checkAuthorization')
  const loggedIn = store.state.login.user!==undefined
  if (loggedIn){
    req.headers.Authorization =  `Bearer ${store.state.login.user.accessToken}`;
  }
  return req
}

function checkError(error){   
  switch(error.response.status){
    case 401:
      store.commit('notify', 'É necessário realizar autenticação')
    break
    case 403:
      store.commit('notify', 'Não autorizado.')
    break
    case 404:
      store.commit('notify', 'Recurso não encontrado. Verifique o console para mais detalhes')
    break
  }
}

export default http