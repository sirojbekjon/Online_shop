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
          class="pt-5 d-none d-flex"
          dark
          filled
          prepend-inner-icon="mdi-magnify"
          solo
          color="#4BAE4F"
          flat
          background-color="transparent"
          rounded
          outlined
          v-model="searchText"
      ></v-text-field>
      <v-spacer></v-spacer>



      <v-menu offset-y>
        <template #activator="{ on, attrs }">
          <v-avatar v-bind="attrs" v-on="on" color="white" class="mb-4">
            <img src="../assets/avatar2.png" alt="Avatar">
          </v-avatar>
        </template>
        <v-list v-if="$store.state.status">
          <v-list-item v-for="(item, index) in menuItems" :key="index" :to="item.path">
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
        <v-list v-else-if="!$store.state.status">
          <v-list-item v-for="(log,index) in logout" :key="index" @click="logoutFunction" :to="log.path">
            <v-list-item-icon>
              <v-icon>{{ log.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-title>{{ log.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
   </v-toolbar>

    <h1 class="white--text">Online_Shop</h1>
    <p class="grey--text">
      Ajoyib tanlov haridingiz uchun minnatdormiz
    </p>
    <v-app-bar dark color="rgba(0,0,0,0)" flat>
      <v-tabs color="#6F0DFF">
        <v-tabs-slider color="#6F0DFF"></v-tabs-slider>
        <v-tab class="withoutupercase" @click="`${$store.commit('setTypeId',type.id)}`" v-for="type in typeProduct" :key="type.id" :to="{name: 'Shoes',params:{id: type.id}}">
          {{ type.name }}
        </v-tab>

      </v-tabs>
      <v-spacer></v-spacer>
    </v-app-bar>

    <v-divider color="grey"></v-divider>
      </v-container>
</template>


<script>

import axios from "axios";

export default {
  data(){
    return {
      searchText:'',
      selectedTabId: null,
      token: 'Bearer ' + sessionStorage.getItem('token'),
      typeProduct:[],
      menuItems: [
        {title: 'Kirish',icon:'fas fa-sign-in-alt',path:'/login'},
        {title: 'Ro\'yxatdan o\'tish',icon: 'fas fa-address-card',path: '/register'}
      ],
      logout:[{title: 'Logout',icon: 'fas fa-sign-out-alt',path: '/login'}]
    };
  },
  mounted: async function () {
    await axios.get('typeProduct/get', {headers: {'authorization': this.token}}).then(response=>{
      this.typeProduct = response.data;
    })
  },

  methods:{
      drawer(){
      this.$emit('drawe')
    },

    logoutFunction(){
      sessionStorage.clear()
      this.$store.commit('setStatus',true)
    },
  },

  watch: {
    searchText(newVal) {
      console.log(newVal)
      this.$store.commit('setSearchQuery', newVal)
    }
  },
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
