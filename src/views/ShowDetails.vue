<template>
  <v-card class="product-card" :class="{ 'dark-mode': darkMode }">
    <v-img
        class="product-image"
        :src="`https://vds.arzongina.uz/upload/${product.fileUpload.name}`"
        v-if="product.fileUpload.contentType !== 'video/mp4'"
        width="100%"
        height="100%"
        contain
    ></v-img>
    <video
        class="product-video"
        controls
        v-else-if="product.fileUpload && product.fileUpload.contentType === 'video/mp4'"
        :src="`https://vds.arzongina.uz/upload/${product.fileUpload.name}`"
        width="100%"
        height="100%"
    ></video>

    <v-card-title class="product-title">
      <h2>{{ product.name }}</h2>
    </v-card-title>
    <v-card-subtitle class="product-category">
      <h4 class="category font-italic">{{ category.categoryName }}</h4>
    </v-card-subtitle>

    <v-card-text class="product-details">
      <h3 class="product-price">{{ product.price }} so'm</h3>
      <p class="product-about">{{ product.about }}</p>
    </v-card-text>

    <v-divider></v-divider>

    <v-card-actions class="product-actions">
      <v-form ref="submit" class="buy-form">
        <v-text-field
            label="Ism familyangizni kiriting"
            v-model="data.name"
            prepend-icon="person"
            type="text"
            color="white"
        ></v-text-field>

        <v-text-field
            id="phone"
            v-model="data.phoneNumber"
            prepend-icon="phone"
            type="tel"
            color="white"
            v-mask="'+998 ## ###-##-##'"
            label="Telefon raqamingizni kiriting"
        ></v-text-field>

        <div class="text-center">
          <v-btn @click.prevent="submit" :style="{ width: '100%' }" color="#6F0DFF" dark>
            Buyurtma berish
          </v-btn>
        </div>
        <br>
        <v-alert
            v-if="alert"
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
      </v-form>


    </v-card-actions>

  </v-card>
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
      darkMode: false,
      product: {},
      category: {},
      quantity: 1,
      token: 'Bearer ' + sessionStorage.getItem('token'),
      data: {
        name: "",
        phoneNumber: '+998',
      },
      alert: false,
      alert_color:'',
      message: '',
    };
  },
  props: ["baseURL", "products", "categories","id"],

  async mounted() {
    await axios.get(`product/get/${this.id}`).then(response =>{
      this.product = response.data
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
       axios.post(`client/add/client/${this.id}`, this.data, {headers: {'authorization': this.token}})
        .then(response =>{
          if (response.status===202){
            this.alert = true
            this.alert_color = 'green'
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
<style scoped>

.darkCard {
  background-color: #222;
  color: #000;
}


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





.product-card {
  width: 400px;
  max-width: 100%;
  margin: auto;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.product-image {
  border-radius: 10px 10px 0 0;
}

.product-video {
  border-radius: 10px 10px 0 0;
  outline: none;
}

.product-title {
  text-align: center;
  padding-top: 20px;
  margin-bottom: 0;
}

.product-category {
  text-align: center;
  margin-top: 5px;
}

.product-price {
  text-align: center;
  color: green;
  font-size: 20px;
  margin-bottom: 10px;
}

.product-about {
  margin-bottom: 20px;
}

.product-actions {
   padding: 20px;
}

.buy-form {
  background-color: #d7d7d7;
  border-radius: 5px;
  padding: 10px;
  width: 100%;
}
</style>