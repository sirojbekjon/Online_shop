<template>
  <v-container>
    <v-row class="mt-n14 mt-lg-n8">
  <v-col
      cols="12"
      xs="12"
      sm="6"
      md="4"
      lg="3"
      v-for="(flow,i) in flows"
      :key="i"
  >
  <v-card
      max-width="344"
      variant="outlined"
  >
    <v-card-item>
      <div>
        <div class="text-overline mb-1 ml-4">
          Oqim yaratuvchisi: {{flow.user ? flow.user.username : 'n/A'}}

        </div>
        <div class="text-h6 mb-1 ml-4" style="color: darkslategray">
          Oqim nomi: {{flow ? flow.name : 'N/A'}}
        </div>
        <v-card-text class="urlbox" ref="urlbox">
          https://arzongina.uz/flow/{{flow.id}}
        </v-card-text>
      </div>
    </v-card-item>
    <v-card-actions>
      <v-btn class="copybtn" @click="copyUrl">
        Nusxalash
      </v-btn>
    </v-card-actions>
  </v-card>
  </v-col>
    </v-row>
    <v-col cols="12" md="6">
      <v-pagination
          v-model="page"
          :length="pageCount"
          @input="nextperson"
          color="#6F0DFF"
      >
      </v-pagination>

    </v-col>
  </v-container>
</template>

<script>
import axios from "axios";
import { VCard, VCardItem } from 'vuetify/lib'


export default {

  name:"FlowUrl",
  components: {
    VCard,
    VCardItem,
  },


  data(){
    return{
      flows:[{name: '',username:null,id:''}],
      props:["id"],
      token: 'Bearer ' + sessionStorage.getItem('token'),

      page: 1,
      totalPages:0,
      itemsPerPage: 10,

      perPageChoices: [
        {text:'5 records/page' , value: 5},
        {text:'10 records/page' , value: 10},
        {text:'20 records/page' , value: 20},
      ],

    }
  },
  methods: {
    copyUrl(event) {
      const urlbox = event.currentTarget.closest('.v-card').querySelector('.urlbox');
      const url = urlbox.textContent;
      const input = document.createElement("input");
      input.style.position = "fixed";
      input.style.opacity = "0";
      input.value = url;
      document.body.appendChild(input);
      input.select();

      try {
        document.execCommand("copy");
      } catch (error) {
        console.error('Failed to copy URL: ', error);
      }
      document.body.removeChild(input);
    },


    async nextperson() {
      const response = await axios.get('flow/get', {
        params: {page: this.page - 1},
        headers: {'authorization': this.token}
      })
      this.totalElement = response.data.totalElements
      if (response.data.length !== 0) {
        this.flows = response.data.content
        this.totalPages = response.data.totalPages
        this.loading = false
      } else {
        this.flows = response.data.content
        this.totalPages = response.data.totalPages
        this.loading = false
      }

    },
  },

  computed:{
    pageCount() {
      return this.totalPages
      // this.totalRecords / this.itemsPerPage
    },
  },

  mounted: async function () {
    this.nextperson()
  },
}



</script>

<style>
.copybtn{
  background-color: #6F0DFF !important;
  color: white !important;
  transition: 0.5s;
}
.copybtn:hover{
  background-color: white !important;
  color: #6F0DFF !important;
}
.urlbox{
  margin-left: 12px;
  /*border: 1px solid gray;*/
  width: 90%;
  border-radius: 5px;
  box-shadow:
      inset 0 -3em 3em rgba(0,0,0,0.1),
      0 0  0 2px rgb(255,255,255),
      0.3em 0.3em 1em rgba(0,0,0,0.3);
}
</style>