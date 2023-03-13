import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:sessionStorage.getItem('token'),
    role:'',
    username:'',
    status:true,
    typeId:1,
    count:0,
    searchQuery: '',
    themcolor:'#0000'
    // typeProduct:[]
  },
  getters: {
    doubleCount: state => state.count * 2
  },
  mutations: {
    increment (state) {
      state.count++
    },

    changeRole(state,payload){
      return state.role = payload
    },
    setFullName(state,payload){
      return state.username = payload
    },
    setStatus(state,payload){
      return state.status = payload
    },
    setTypeId(state,payload){
      return state.typeId = payload
    },
    setSearchQuery(state, query) {
      state.searchQuery = query
    },
    setThemeColor(state, payload) {
      state.themcolor = payload
    }
    // setTypeProduct(state,payload){
    //   return state.typeProduct = payload
    // }
  },
  actions: {
    // incrementAsync ({ commit }) {
    //   setTimeout(() => {
    //     commit('increment')
    //   }, 10)
    // }
  },
  modules: {
  }
})
