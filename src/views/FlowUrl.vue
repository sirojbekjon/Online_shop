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
          Oqim yaratuvchisi: {{flow.user.username}}
        </div>
        <div class="text-h6 mb-1 ml-4" style="color: darkslategray">
          Oqim nomi: {{flow.name}}
        </div>
        <v-card-text class="urlbox" ref="urlbox">
          http://192.168.202.23:8080/flow/{{flow.id}}
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
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name:"FlowUrl",



  data(){
    return{
      flows:[''],
      props:["id"],
      token: 'Bearer ' + sessionStorage.getItem('token'),
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
        console.log('URL copied to clipboard');
      } catch (error) {
        console.error('Failed to copy URL: ', error);
      }
      document.body.removeChild(input);
    }
  },

  mounted() {
     axios.get('flow/get',{headers:{'authorization':this.token}}).then(response =>{
       this.flows = response.data
       console.log(response.data)
     })
  }
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