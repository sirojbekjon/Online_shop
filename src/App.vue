<template>
  <v-app :style="{ background: $vuetify.theme.themes.dark.background }">
    <v-main>
        <SideBar :drawer="drawer" />
      <router-view/>
    </v-main>

  </v-app>
</template>

<script>
// import axios from "axios";
import SideBar from './components/SideBar';


export default {
  name: 'App',

  components:{
    SideBar,

  },

  data: () => ({
  }),

  mounted: function () {
      if (sessionStorage.getItem('token')){
        this.$store.commit('setStatus',false)
      }
      // axios.get('client/get/1', {headers: {'authorization': this.token}}).then(response => {
      //   console.log(response)
      // });
  }
};
</script>
