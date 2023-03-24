<template>
  <v-app :style="{ background: $vuetify.theme.themes.dark.background }">
    <v-main>
      <SideBar />
      <router-view />
    </v-main>
    <Footer style="z-index: 10"/>
  </v-app>
</template>

<script>
import SideBar from './components/SideBar';
import Footer from './components/Footer';
import axios from "axios";


export default {
  name: 'App',
  data(){
    return{
      token: 'Bearer ' + sessionStorage.getItem('token'),
    }
  },
  components:{
    SideBar,
    Footer
  },

  mounted: function () {
    if (sessionStorage.getItem('token')) {
      this.$store.commit('setStatus',false)
      axios.get('auth/userme', {headers: {'authorization': this.token}}).then(response => {
        this.$store.commit('setFullName',response.data.fullName)
        sessionStorage.setItem("role",response.data.role.name)
        this.$store.commit('changeRole',response.data.role.name)
      })}}
};
</script>
