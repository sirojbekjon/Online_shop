<template>
  <v-footer style="background-color: #151515" class="white--text">
    <v-row class="mt-2" justify="center" no-gutters>
      <v-btn
          v-for="link in links"
          :key="link.name"
          color="#999999"
          variant="text"
          class="ma-2"
          small
          active-class="text"
      >
        {{ link.name }}
           <v-icon class="" color="#6F0DFF">
          {{link.icon}}
        </v-icon>
      </v-btn>

      <v-col class="text-center mt-4" cols="12">
        {{ new Date().getFullYear() }} — <strong>Online_shop</strong>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
  export default {
    data(){
      return{
        links: [
          {name:'Facebook',icon:'mdi-facebook'},
          {name:'Instagram',icon: 'mdi-instagram'},
          {name:'Contact Us',icon:'mdi-phone'},
        ],


      }
    }

  }
</script>


<style scoped>

.text{
  color: white;
}

</style>