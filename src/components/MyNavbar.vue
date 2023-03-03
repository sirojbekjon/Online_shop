<template>
  <v-container>
    <v-toolbar flat color="rgba(0,0,0,0)">
      <v-btn
          icon
          dark
          class="d-lg-none d-xl-flex"
          @click="drawer"
      >
        <v-icon>mdi-menu</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <v-text-field
          label="Search on maufarm"
          class="pt-5 d-none d-sm-flex"
          dark
          filled
          prepend-inner-icon="mdi-magnify"
          solo
          flat
          background-color="transparent"
          rounded
          outlined
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn dark class="mr-2">
        <v-icon left>fas fa-filter</v-icon>
        Filtre
      </v-btn>
      <v-btn dark class="mr-2">
        <v-icon left>fas fa-upload</v-icon>
        Export
      </v-btn>
    </v-toolbar>
    <h1 class="white--text">Online_Shop</h1>
    <p class="grey--text">
      Ajoyib tanlov haridingiz uchun minnatdormiz
    </p>
    <v-app-bar dark color="rgba(0,0,0,0)" flat class="">
      <v-tabs color="#6F0DFF">
        <v-tabs-slider color="#6F0DFF"></v-tabs-slider>

        <v-tab class="withoutupercase">Fultillment Status View</v-tab>
        <v-tab class="withoutupercase">Real-time Trackning</v-tab>
        <v-tab class="withoutupercase">Sales View</v-tab>
        <v-tab class="withoutupercase">Balances</v-tab>
        <v-tab class="withoutupercase">Transactions</v-tab>
        <v-tab class="withoutupercase">Engagement Rate</v-tab>
      </v-tabs>
      <v-spacer></v-spacer>
      <v-btn dark text class="d-none d-sm-flex">
        <v-icon left>mdi-magnify</v-icon>
        SEARCH
      </v-btn>
      <v-btn rounded color="white" class="black--text">
        List
        <v-icon right>mdi-format-list-bulleted</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-apps</v-icon>
      </v-btn>
    </v-app-bar>
    <v-divider color="grey"></v-divider>
    <v-toolbar flat color="rgba(0,0,0,0)">
      <v-divider vertical color="green" inset></v-divider>
      <v-toolbar-title class="white--text ml-4">Alert</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-chip
          class="ma-1 d-none d-sm-flex"
          color=""
          label
          text-color="white"
          dark
      >
        <v-avatar color="white" rounded class="mr-2">
          <v-img src="1.png" contain></v-img>
        </v-avatar>
        Nike new golf
      </v-chip>
      <v-chip
          class="ma-1 d-none d-sm-flex"
          color=""
          label
          text-color="white"
          dark
      >
        <v-avatar color="white" rounded class="mr-2">
          <v-img src="2.png" contain></v-img>
        </v-avatar>
        Sneakers printemps
      </v-chip>

      <v-spacer></v-spacer>
      <v-btn dark class="mr-2 withoutupercase d-none d-sm-flex">
        See All (10)
      </v-btn>
      <v-btn dark class="mr-2" outlined>
        <v-icon left>mdi-chevron-left</v-icon>
        <v-icon right>mdi-chevron-right</v-icon>
      </v-btn>
    </v-toolbar>
  </v-container>
</template>


<script>

export default {
  data(){

  },
  methods:{
    drawer(){
      this.$emit('drawe')
    }
  }
}
</script>


<style scoped>
.v-tab.withoutupercase {
  text-transform: none !important;
}
.v-tabs {
  width: 50% !important;
}
.v-btn.withoutupercase {
  text-transform: none !important;
}
/*div{
  display:inline-block;
  float:left;
  color:#fff;
  font-size:10px;
}*/

.three {
  width: 50px;
  height: 50px;
}

.four {
  width: 50px;
  height: 25px;
  background: black;
}
.five {
  width: 50px;
  height: 25px;
  background: #042a0f;
}
.six {
  width: 50px;
  height: 25px;
  background: #2c2107;
}
</style>