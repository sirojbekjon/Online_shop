<template>
  <v-container>
    <v-row>
      <v-col
          xs="12"
          sm="6"
          md="4"
          lg="3"
          v-for="(shoe, i) in shoes"
          :key="i"
      >
        <v-card
            :class="{ 'darkCard': darkMode }"
            class="mx-auto my-12 rounded-xl"
            max-width="374"

            xs="12" sm="8" md="6" lg="4"
        >
          <v-img v-if="shoe.fileUpload && shoe.fileUpload.contentType!=='video/mp4'" height="350" :src="`http:/192.168.202.23:5000/upload/${shoe.fileUpload.name}`"></v-img>
          <video  controls v-else-if="shoe.fileUpload && shoe.fileUpload.contentType === 'video/mp4'" width="100%"  height="350">
            <source  :src="`http:/192.168.202.23:5000/upload/${shoe.fileUpload.name}`" type="video/mp4">
          </video>

          <v-toolbar color="transparent" class="mt-n7" flat>
            <v-avatar color="white" rounded class="mr-2" style="background-color: #042a0f">
              <v-img v-if="shoe.fileUpload && shoe.fileUpload.contentType!=='video/mp4'" width="100px" height="50" :src="`http:/192.168.202.23:5000/upload/${shoe.fileUpload.name}`"></v-img>
              <video  controls v-else-if="shoe.fileUpload && shoe.fileUpload.contentType === 'video/mp4'" width="100%"  height="170">
                <source  :src="`http:/192.168.202.23:5000/upload/${shoe.fileUpload.name}`" type="video/mp4">
              </video>
            </v-avatar>
            <v-spacer></v-spacer>
            <v-avatar color="black" rounded class="mr-2" width="100px" dark>
              <div class="three">
                <div class="four">
                  <span class="white--text caption"  v-bind:class="{productprice:!shoe.status}">{{ shoe.status ? shoe.price+' so\'m' : 'Qolmagan'}}</span>
                </div>
                <div class="five">
                  <span class="green--text caption">Sotuvda</span>
                </div>
              </div>
            </v-avatar>
          </v-toolbar>

          <v-rating xs="12" sm="8" md="6" v-model="rating" :size="18" :dense="true" :half-increments="true" color="yellow" class="ml-3" background-color="grey lighten-2" ></v-rating>
          <span v-if="shoe.sale" style="font-size: 20px;font-family: 'Arial Black';" class="red--text ml-4" >SALE: {{shoe.sale}} %</span>
          <v-spacer></v-spacer>
          <span v-if="$store.state.role==='SUPERADMIN' || $store.state.role==='ADMIN'" :class="{'darcCard': darkMode }" class="ml-4">Admin uchun: {{shoe.salary}} so'm</span>


          <v-card-text :class="{ 'darkCard': darkMode }"  style="font-size: 20px; color: orange; font-family: Bitstream Vera Sans Mono,serif">
            {{shoe.name ? shoe.name : 'N/A' }}
          </v-card-text>

          <v-card-text class="mt-n4">
            <v-chip-group
                active-class="deep-purple accent-4 white--text"
                column
            >
              <div class="container">
                <div class="row">
                  <div>
                    <v-btn class="bybutton" :to="{ name: 'ShowDetails', params: { id: shoe.id } }">Sotib olish</v-btn>
                  </div>
                  <div v-if="$store.state.role==='ADMIN' || $store.state.role==='SUPERADMIN'">
                    <v-btn class="oqimbutton ml-1" :class="{ 'darkCard': darkMode }" :to="{ name: 'Flow', params: { id: shoe.id } }">Oqim</v-btn>
                  </div>
                  <v-spacer></v-spacer>
                  <div>
                    <h4 class="green--text mt-4" > {{
                        shoe.typeProduct.name
                      }}</h4>

                  </div>
                </div>
              </div>
            </v-chip-group>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <v-pagination
        v-model="page"
        :length="pageCount"
        @input="nextProductWithCurrentType"
        color="#6F0DFF"
    >
    </v-pagination>

  </v-container>
</template>


<script>


import axios from "axios";

export default {
  name: "Shoes",
  data: () => ({
    darkMode: false,
    page: 1,
    totalPages:0,
    itemsPerPage: 10,
    totalElement:'',
    selection: 1,
    drawer: true,
    props:['id'],
    typesId:'',
    rating:5,




    perPageChoices: [
      {text:'5 records/page' , value: 5},
      {text:'10 records/page' , value: 10},
      {text:'20 records/page' , value: 20},
    ],


    shoes: [{name: '', fileUpload: null,price:'',typeProduct:'',createdAt:'' }],
  }),
   //990595959 Qibray
  //990501881
  //soliq.uz
  //tolov gosudarsstvinniy nalog davlat soliq turi pnfl 1700.000


  mounted: function() {
    // setInterval(() => {
      this.nextProduct();
    // }, 10000000000); // Replace 1000 with the desired interval in milliseconds
  },

  watch:{
    '$store.state.typeId': {
      immediate: true,
      async handler() {
        await this.nextProduct(this.$store.state.typeId)
      }
    },
    '$store.state.searchQuery':{
      async handler() {
        await this.searchProduct(this.$store.state.searchQuery)
      }
    },
    darkMode(newVal) {
      this.$vuetify.theme.dark = newVal; // update the Vuetify theme
    },
  },

  methods:{
    async nextProductWithCurrentType() {
      await this.nextProduct(this.$store.state.typeId) // call nextProduct with current typeId
    },
    async nextProduct() {
      const token = 'Bearer ' + sessionStorage.getItem('token');
      const products = await axios.get('product/get', {
        params: {page: this.page-1, text: this.$store.state.searchQuery},
        headers: {'authorization': token}
      })
      this.totalElement = products.data.totalElements
      if (products.data.content.length >= 1) {
        this.shoes = products.data.content
        this.totalPages = products.data.totalPages
      }
    },


    async searchProduct(text){
      const response = await axios.get('product/get', {
        params: {page: this.page - 1, text: text}
      })
      if (text !== '' && text.length > 3 && response.data.length !== 0) {
        this.shoes = response.data.content
        this.totalPages = response.data.totalPages
      } else {
        this.shoes = response.data.content
        this.totalPages = response.data.totalPages
      }
      this.totalElement = response.data.totalElements
    }
  },

  computed:{
    ordersWithIndex(){
      return this.shoes.map(
          (items, index) => ({
            ...items,
            index: (this.page-1)*10 + index + 1
          }))
    },


    pageCount() {
      return this.totalPages
      // this.totalRecords / this.itemsPerPage
    },
  }
};
</script>
<style scoped>
.darkCard {
  background-color: #222;
  color: #000;
}

.three {
  width: 100px;
  height: 50px;
}

.four {
  width: 100px;
  height: 25px;
  background: black;
}
.five {
  width: 100px;
  height: 25px;
  background: #042a0f;
}
.six {
  width: 100px;
  height: 25px;
  background: #2c2107;
}
.oqimbutton{
  transition: 0.5s;
  font-size: 12px;
  font-family: "Bitstream Vera Sans Mono",serif;
}
.oqimbutton:hover{
  background-color: grey;
  color: white;
  font-size: 13px;
}
.bybutton{
  background-color: #6F0DFF !important;
  color: white;
  font-family: "Bitstream Vera Sans Mono",serif !important;
}
.productprice{
  color: red !important;
  font-family: "Bitstream Vera Sans Mono",serif !important;
  font-size: 18px !important;
}
</style>