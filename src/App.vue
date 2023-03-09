<template>
  <v-app :style="{ background: $vuetify.theme.themes.dark.background }">
    <v-main>
        <SideBar @changeTypeId="giveTypeId"/>
<!--        <Navbar />-->
      <router-view :selected_type_id="typeId2"/>
    </v-main>
  </v-app>
</template>

<script>
// import axios from "axios";
import SideBar from './components/SideBar';
// import Navbar from './components/MyNavbar'

export default {
  name: 'App',
  components:{
    SideBar,
    // Navbar
  },

  data: () => ({
    typeId2:null
  }),
  methods:{
    giveTypeId(typeId){
      console.log("Appp")
      console.log(typeId)
      this.typeId2 = typeId
    }
  },

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
