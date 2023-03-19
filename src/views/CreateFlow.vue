<template>
  <div class="container" style="color: #b9b9b9">
    <div class="row pt-5">
      <div class="col-md-1"></div>
      <!--            display image-->
      <div class="col-md-4 col-8">
        <v-img v-if="product.fileUpload.contentType!=='video/mp4'" width="100%" height="auto" :src="`http://192.168.1.4:8088/upload/${product.fileUpload.name}`"></v-img>
        <video  controls v-else-if="product.fileUpload.contentType === 'video/mp4'" width="100%"  height="auto">
          <source  :src="`http://192.168.1.4:8088/upload/${product.fileUpload.name}`" type="video/mp4">
        </video>
      </div>
      <!--            display product details-->
      <div class="col-md-6 col-12 pt-3 pt-md-0">
        <h1>{{ product.name }}</h1>
        <h4 class="font-weight-bold" style="color: green;font-size: 20px">Mahsulot: {{ product.price }}</h4>
        <div class="features pt-3">
          <h3><strong>Mahsulot haqida</strong></h3>
          <h4>{{product.about}}</h4>
        </div>
        <v-form ref="submit"  class="mt-6 col-md-6" style="background-color: #635F68;border-radius: 5px">
          <v-text-field
              label="Oqimga nom bering"
              v-model="data.name"
              prepend-icon="list"
              type="text"
              color="white"
          />
          <div class="text-center mb-5">
            <v-btn @click.prevent="submit" color="#6F0DFF" dark>saqlash</v-btn>
          </div>
        </v-form>
        <v-alert
            class="mt-6 col-md-6"
            :value="alert"
            :color="alert_color"
            dark
            border="left"
            transition="scale-transition"
            @input="alert = false"
        >
          <div class="d-flex align-center">
            <v-icon class="mr-2">mdi-alert-circle-outline</v-icon>
            <span>{{ message }}</span>
          </div>
        </v-alert>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  components:{
    // VueInputMask
  },
  data() {

    return {
      product: {},
      token: 'Bearer ' + sessionStorage.getItem('token'),
      data: {
        name: "",
        archived:false,
        canceled:false,
        delivered:false,
        hold:false,
        neww:false,
        onway:false,
        ready:false
      },
      alert: false,
      alert_color:'',
      message: '',
    };
  },
  props: ["id"],

  async mounted() {
    await axios.get(`product/get/${this.id}`).then(response =>{
      this.product = response.data
    })
  },

  methods:{
    submit(){
      if (this.data.name ===''){
        this.alert = true
        this.alert_color='red'
        this.message = 'Oqimga nom bering'
        setTimeout(() => {
          this.alert = false;
        }, 5000);
      }else {
        axios.post(`flow/add/${this.id}`, this.data, {headers: {'authorization': this.token}})
            .then(response =>{
              console.log(response.data)
              if (response.status===202){
                this.alert = true
                this.alert_color = 'success'
                this.message='Sizga url manzil beriladi!'
                this.data = '',
                    this.$router.push({ name: 'FlowUrl', params: { id: response.data } });

              }else{
                this.alert = true
                this.alert_color='red'
                this.message = 'Oqimga nom bering'
                setTimeout(() => {
                  this.alert = false;
                }, 5000);
              }
            })

      }
    },

  },






};
</script>
<style>
.scale-transition-enter-active {
  animation: scale-in 0.6s;
}

.scale-transition-leave-active {
  animation: scale-out 0.6s;
}

@keyframes scale-in {
  0% {
    transform: scale(0);
  }
  100% {
    transform: scale(1);
  }
}

@keyframes scale-out {
  0% {
    transform: scale(1);
  }
  100% {
    transform: scale(0);
  }
}
</style>