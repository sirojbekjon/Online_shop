import Vue from 'vue'
import VueRouter from 'vue-router'


    Vue.use(VueRouter)
const routes = [
  {
    path: '/',
    name: 'Home',
    component: ()=> import("@/views/Home")
  },
  {
    path: '/login',
    name: 'MyLogin',
    component: ()=> import("@/components/MyLogin")
  },
  {
    path: '/register',
    name: 'MyRegister',
    component: ()=> import("@/components/MyRegister")
  },
  {
    path: '/typeProduct',
    name: 'TypeProduct',
    component: ()=> import("@/views/TypeProduct")
  },

  {
    path: '/product',
    name: 'Product',
    component: ()=> import("@/views/Product")
  },
  {
    path: '/footer',
    name: 'Footer',
    component: ()=> import("@/components/Footer")
  },
  {
    path: '/flows',
    name: 'Flows',
    component: ()=> import("@/views/Flows"),
  },
  {
    path: '/order',
    name: 'OrderProduct',
    component: ()=> import("@/views/OrderProduct")
  },
  {
    path: '/statistic',
    name: 'Statistic',
    component: ()=> import("@/views/Statistic")
  },
  {
    path: '/payment',
    name: 'Payment',
    component: ()=> import("@/views/Payment"),
  },
  {
    path: '/market',
    name: 'Market',
    component: ()=> import("@/views/Market"),
  },
  {
    path: '/user',
    name: 'AdminUser',
    component: ()=> import("@/views/AdminUser"),
  },
  {
    path: '/creatflow/:id',
    name: 'Flow',
    component: ()=> import("@/views/CreateFlow"),
    props:true
  },
  {
    path: '/flow_url',
    name: 'FlowUrl',
    component: ()=> import("@/views/FlowUrl"),
    props:true
  },
  // show details of product
  {
    path: '/product/show/:id',
    name: 'ShowDetails',
    component: ()=> import("@/views/ShowDetails"),
    props: true
  },
  {
    path: '/flow/:id',
    name: 'ShowFlow',
    component: ()=> import("@/views/ShowFlow"),
    props: true
  },
  {
    path: '/shoes/:id',
    name: 'Shoes',
    component: ()=> import("@/views/Shoes"),
    props:true
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '@/views/About.vue')
  },


]

const router = new VueRouter({
  mode: 'history',
  base: '/',
  routes
})

export default router
