<template>
  <v-app :style="{ background: $vuetify.theme.themes.dark.background }">
    <v-main>
        <SideBar />
      <router-view :selected_type_id="typeId2"/>
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

  data: () => ({

  }),
  mounted: function () {
    console.log(this.typeId)
      if (sessionStorage.getItem('token')){
        this.$store.commit('setStatus',false)
      }
      // axios.get('client/get/1', {headers: {'authorization': this.token}}).then(response => {
      //   console.log(response)
      // });
  }
};
</script>
