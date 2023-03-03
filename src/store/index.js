import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:sessionStorage.getItem('token'),
    role:'',
    username:'',
    status:true
  },
  getters: {
  },
  mutations: {
    changeRole(state,payload){
      return state.role = payload
    },
    setFullName(state,payload){
      return state.username = payload
    },
    setStatus(state,payload){
      return state.status = payload
    }
  },
  actions: {
  },
  modules: {
  }
})
