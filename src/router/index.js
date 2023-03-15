import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import MyLogin from '../components/MyLogin.vue'
import MyRegister from '../components/MyRegister.vue'
import TypeProduct from '../views/TypeProduct.vue'
import Upload from "@/views/Upload";
import Product from "@/views/Product";
import Shoes from "@/views/Shoes";
import Footer from "@/components/Footer";
import ShowDetails from "@/views/ShowDetails";
import Flow from "@/views/CreateFlow";
import FlowUrl from "@/views/FlowUrl";
import ShowFlow from "@/views/ShowFlow";
import Saller from "@/views/Saller";
import OrderProduct from "@/views/OrderProduct";

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
    path: '/footer',
    name: 'Footer',
    component: Footer
  },
  {
    path: '/saller',
    name: 'Saller',
    component: Saller
  },
  {
    path: '/order',
    name: 'OrderProduct',
    component: OrderProduct
  },
  {
    path: '/creatflow/:id',
    name: 'Flow',
    component: Flow,
    props:true
  },
  {
    path: '/flow_url',
    name: 'FlowUrl',
    component: FlowUrl,
    props:true
  },
  // show details of product
  {
    path: '/product/show/:id',
    name: 'ShowDetails',
    component: ShowDetails,
    props: true
  },
  {
    path: '/flow/:id',
    name: 'ShowFlow',
    component: ShowFlow,
    props: true
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
