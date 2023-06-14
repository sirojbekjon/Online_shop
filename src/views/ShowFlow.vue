<template>
  <div class="container" style="color: #b9b9b9">
    <div class="row pt-5">
      <div class="col-md-1"></div>
      <!--            display image-->
      <div class="col-md-4 col-8">
        <v-img v-if="flow_product.product.fileUpload.contentType!=='video/mp4'" width="100%" height="auto" :src="`https://arzongina.uz/upload/${flow_product.product.fileUpload.name}`"></v-img>
        <video  controls v-else-if="flow_product.fileUpload && flow_product.fileUpload.contentType === 'video/mp4'" width="100%"  height="350">
          <source  :src="`https://arzongina.uz/upload/${flow_product.fileUpload.name}`" type="video/mp4">
        </video>
      </div>
      <!--            display flow_product details-->
      <div class="col-md-6 col-12 pt-3 pt-md-0">
        <h1>{{ flow_product.product.name }}</h1>
        <h4 class="font-weight-bold" style="color: green;font-size: 20px">Mahsulot: {{ flow_product.product.price }}</h4>
        <div class="features pt-3">
          <h3><strong>Mahsulot haqida</strong></h3>
          <h4>{{flow_product.product.about}}</h4>
        </div>
        <v-form ref="submit"  class="mt-6 col-md-6" style="background-color: #635F68;border-radius: 5px">
          <v-text-field
              label="Ism familyangizni kiriting"
              v-model="data.name"
              prepend-icon="person"
              type="text"
              color="white"

          />

          <v-text-field
              id="phone"
              v-model="data.phoneNumber"
              prepend-icon="phone"
              type="tel"
              color="white"
              v-mask="'+998 (##) ###-##-##'"
              label="Telifon raqamingizni kiriting"
          />
          <div class="text-center mb-5">
            <v-btn @click.prevent="submit" color="#6F0DFF" dark>Buyurtma berish</v-btn>
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
// import VueInputMask from 'vue-inputmask';

export default {
  components:{
    // VueInputMask
  },
  data() {

    return {
      flow_product: [''],
      quantity: 1,
      token: 'Bearer ' + sessionStorage.getItem('token'),
      data: {
        name: "",
        phoneNumber: '+998()',
      },
      alert: false,
      alert_color:'',
      message: '',
    };
  },
  props: ["id"],

  async mounted() {
    await axios.get(`flow/${this.id}`).then(response =>{
      this.flow_product = response.data
      console.log(response.data.product)
    })
  },

  methods:{
    submit(){
      if (this.data.name ==='' || this.data.phoneNumber.substring(6)===''){
        this.alert = true
        this.alert_color='red'
        this.message = 'Ma\'lumotlarni to\'liq to\'ldiring'
        setTimeout(() => {
          this.alert = false;
        }, 5000);
      }else {
        axios.post(`client/add/${this.id}`, this.data)
            .then(response =>{
              if (response.status===202){
                this.alert = true
                this.alert_color = 'success'
                this.message='Sizning buyurtmangiz qabul qilindi sizga tez orada aloqaga chiqishadi!'
                this.data = '',
                    setTimeout(() => {
                      this.alert = false;
                    }, 5000);
              }else{
                this.alert = true
                this.alert_color='red'
                this.message = 'Ma\'lumotlarni to\'liq to\'ldiring'
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