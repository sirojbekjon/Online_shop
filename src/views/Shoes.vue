<template>
  <v-container>
    <v-row class="mt-n14">
      <v-col
          cols="12"
          xs="12"
          sm="6"
          md="4"
          lg="3"
          v-for="(shoe, i) in shoes"
          :key="i"
      >
        <v-card
            class="mx-auto my-12 rounded-xl"
            max-width="374"
            color="#151515"
        >
          <v-img v-if="shoe.fileUpload && shoe.fileUpload.contentType!=='video/mp4'" height="350" :src="`http://192.168.202.23:8088/upload/${shoe.fileUpload.name}`"></v-img>
          <video  controls v-else-if="shoe.fileUpload && shoe.fileUpload.contentType === 'video/mp4'" width="100%"  height="350">
            <source  :src="`http://192.168.202.23:8088/upload/${shoe.fileUpload.name}`" type="video/mp4">
          </video>

          <v-toolbar color="transparent" class="mt-n7" flat>
            <v-avatar color="white" rounded class="mr-2" style="background-color: #042a0f">
              <v-img v-if="shoe.fileUpload && shoe.fileUpload.contentType!=='video/mp4'" width="100px" height="50" :src="`http://192.168.202.23:8088/upload/${shoe.fileUpload.name}`"></v-img>
              <video  controls v-else-if="shoe.fileUpload && shoe.fileUpload.contentType === 'video/mp4'" width="100%"  height="170">
                <source  :src="`http://192.168.202.23:8088/upload/${shoe.fileUpload.name}`" type="video/mp4">
              </video>
            </v-avatar>
            <v-spacer></v-spacer>
            <v-avatar color="black" rounded class="mr-2" width="100px" dark>
              <div class="three">
                <div class="four">
                  <span class="white--text caption">{{ shoe.price }}</span>
                </div>
                <div class="five">
                  <span class="green--text caption">Sotuvda</span>
                </div>
              </div>
            </v-avatar>
          </v-toolbar>
          <v-card-title class="grey--text caption" > {{
              shoe.typeProduct.name
            }}</v-card-title>
          <v-card-text class="green--text" style="font-size: 25px;font-family: 'Albertus MT'">
            {{shoe.name ? shoe.name : 'N/A' }}
          </v-card-text>
          <v-card-title class="grey--text text-grey-darken-1 caption mt-n6">Sotuvga chiqarilgan vaqti: {{
              shoe.createdAt.substring(0,10)
            }}</v-card-title>
<!--          <v-card-text class="white&#45;&#45;text text-grey-darken-1 mt-n2">{{-->
<!--              shoe.about-->
<!--            }}</v-card-text>-->
          <v-card-text class="mt-n4">
            <v-chip-group
                active-class="deep-purple accent-4 white--text"
                column
            >
              <v-chip label color="black" text-color="white" disabled>{{ shoe.produced }}</v-chip>
              <v-btn  color="green" width="30%" height="80%" class="mt-1" :to="{name: 'ShowDetails', params: {id : shoe.id}}">Sotib olish</v-btn>
              <v-btn  color="orange" width="30%" height="80%" class="ml-1 mt-1" :to="{name: 'Flow', params: {id : shoe.id}}">Oqim</v-btn>
              <v-spacer></v-spacer>
              <v-avatar size="40">
                <v-img :src="shoe.avatar"></v-img>
              </v-avatar>
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
    page: 1,
    totalPages:0,
    itemsPerPage: 10,
    totalElement:'',
    selection: 1,
    drawer: true,
    props:['id'],
    typesId:'',



    perPageChoices: [
      {text:'5 records/page' , value: 5},
      {text:'10 records/page' , value: 10},
      {text:'20 records/page' , value: 20},
    ],


    shoes: [{name: '', fileUpload: null,price:'',typeProduct:'',createdAt:'' }],
  }),



  mounted: async function () {
    this.nextProduct(this.$store.state.typeId)
    // this.setSearchIconColor('#6F0DFF');
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
    }

  },

  methods:{
    async nextProductWithCurrentType() {
      await this.nextProduct(this.$store.state.typeId) // call nextProduct with current typeId
    },
    async nextProduct(typeId) {
      const token = 'Bearer ' + sessionStorage.getItem('token');
      const products = await axios.get(`product/byType/${typeId}`, {
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
</style>