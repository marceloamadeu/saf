import Vue from 'vue'
import store from '@/store/store'
import VueRouter from 'vue-router'
import Home from '@/views/home/Home.vue'
import Fruit from '@/views/fruit/Fruit.vue'

// error pages
import AccessDenied from '@/layout/errors/Deny'
import NotFound from '@/layout/errors/NotFound'

Vue.use(VueRouter)


const routes = [  
  {
    path: '/',
    name: 'inicial',
    component: Home,
  },  
  {
    path: '/home',
    name: 'home',
    component: Home,
    meta: {
      breadcrumb: true,
      label: 'Página Inicial'
    }
  },
  {
    path: '/fruits',
    name: 'fruit',
    component: Fruit
  },
  // error pages
  {
    path: '/accessdenied',
    name: 'AccessDenied',
    component: AccessDenied,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/*',
    name: 'NotFound',
    components: {
      secundario: NotFound,
      default: NotFound
    },
    meta: {
      requiresAuth: false
    }
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,  
  linkActiveClass: 'open active',
  scrollBehavior: () => ({ y: 0 }),
  routes
})

store.commit('setLayout', 'admin')


export default router
