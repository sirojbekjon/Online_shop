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

export default {
  name: 'App',
  components:{
    SideBar,
    Footer
  },
  mounted: function () {
      if (sessionStorage.getItem('token')){
        this.$store.commit('setStatus',false)
      }
   }
};
</script>
