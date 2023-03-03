<template>
  <v-app :style="{ background: $vuetify.theme.themes.dark.background }">
    <v-main>
        <SideBar :drawer="drawer" />
      <router-view/>
    </v-main>

  </v-app>
</template>

<script>
import axios from "axios";
import SideBar from './components/SideBar';


export default {
  name: 'App',

  components:{
    SideBar,

  },

  data: () => ({
    token:'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlckFkbWluIiwiaWF0IjoxNjc3NzQwNzAxLCJleHAiOjE2Nzc3NzY3MDEsInJvbGUiOiJTVVBFUkFETUlOIn0.zGFFEiohC59AsxDXsvLiJh0e9MsmKYeZjk961vmkDOZVXd2zgjNqfKkJch8mcqQSZeHOsOtNsiCAqxr5AQnHNA'
  }),

  mounted: function () {
      axios.get('client/get/1', {headers: {'authorization': this.token}}).then(response => {
        console.log(response)
      });
  }
};
</script>
