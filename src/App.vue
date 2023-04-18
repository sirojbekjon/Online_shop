<template>
  <v-app  :class="{ 'darkCard': darkMode }">
    <v-main>
      <SideBar/>
      <router-view />
    </v-main>
    <template>
<!--      <v-switch v-model="darkMode" label="Dark Mode"></v-switch>-->
      <!-- your other application content goes here -->
    </template>
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

      darkMode: false,
      token: 'Bearer ' + sessionStorage.getItem('token'),
    }
  },

  watch: {
    darkMode(newVal) {
      this.$vuetify.theme.dark = newVal; // update the Vuetify theme
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


<style scoped>
.darkCard {
  background-color: #F5F5FB;
  color: #000;
}
</style>