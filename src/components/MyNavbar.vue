<template>
  <v-container>
    <v-toolbar flat color="rgba(0,0,0,0)">
      <v-btn
          icon
          :class="{ 'darkCard': darkMode }"
          @click="drawer"
      >
        <v-icon>mdi-menu</v-icon>
      </v-btn>
      <v-spacer></v-spacer>

      <v-text-field
          label="Search on maufarm"
          class="pt-5 px-2  d-none d-flex"
          :class="{ 'darkCard': darkMode }"
          filled
          prepend-inner-icon="mdi-magnify"
          solo
          color="#4BAE4F"
          flat
          background-color="transparent"
          rounded
          outlined
          v-model="searchText"
      />

      <v-spacer></v-spacer>
      <v-menu offset-y>
        <template #activator="{ on, attrs }">
          <v-avatar v-bind="attrs" v-on="on" color="white" class="ml-3 mb-2">
            <img src="../assets/avatar2.png" alt="Avatar">
          </v-avatar>
            <v-icon @click="toggleDarkMode" dark-mode="darkMode">{{ darkMode ? 'mdi-weather-sunny' : 'mdi-weather-night' }}</v-icon>
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


    <h1 :class="{ 'darkCard': darkMode }" class="mt-4">ArzonginaUz</h1>

    <p class="grey--text">
      Ajoyib tanlov haridingiz uchun minnatdormiz
    </p>
    <v-app-bar color="transparent" flat :class="{'darkCard': darkMode}">
      <v-tabs color="#6F0DFF">
        <v-tabs-slider color="#6F0DFF"></v-tabs-slider>
        <v-tab :class="{'darkCard': darkMode}" @click="`${$store.commit('setTypeId',type.id)}`" v-for="type in typeProduct" :key="type.id" :to="{name: 'Shoes',params:{id: type.id}}">
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
      darkMode: false,
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
    toggleDarkMode() {
      this.darkMode = !this.darkMode;
      // Perform any other actions you want to happen when the icon is clicked
    },
      drawer(){
        this.$emit('drawe')
      },

    logoutFunction(){
      sessionStorage.clear()
      this.$store.commit('setStatus',true)
      this.$store.commit('changeRole','')
    },
  },

  watch: {
    searchText(newVal) {
      this.$store.commit('setSearchQuery', newVal)
    },
    darkMode(newVal) {
      this.$vuetify.theme.dark = newVal; // update the Vuetify theme
    }
  },
}
</script>


<style scoped>
.darkCard {
  background-color: #121212;
  color: #6F0DFF;
}
.v-tab.withoutupercase {
  text-transform: none !important;
}
.v-tabs {
  width: 50% !important;
}
.v-btn.withoutupercase {
  text-transform: none !important;
}

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
