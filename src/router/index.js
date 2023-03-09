import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import MyLogin from '../components/MyLogin.vue'
import MyRegister from '../components/MyRegister.vue'
import TypeProduct from '../views/TypeProduct.vue'
import Upload from "@/views/Upload";
import Product from "@/views/Product";
import Shoes from "@/views/Shoes";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'MyLogin',
    component: MyLogin
  },
  {
    path: '/register',
    name: 'MyRegister',
    component: MyRegister
  },
  {
    path: '/typeProduct',
    name: 'TypeProduct',
    component: TypeProduct
  },
  {
    path: '/upload',
    name: 'Upload',
    component: Upload
  },
  {
    path: '/product',
    name: 'Product',
    component: Product
  },
  {
    path: '/shoes/:id',
    name: 'Shoes',
    component: Shoes,
    props:true
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },

  // {
  //   path:"/product/:id",
  //   component: Product,
  //   props:true,
  // }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
