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
          <v-img v-if="shoe.fileUpload.contentType!=='video/mp4'" height="250" :src="`http://192.168.202.23:8088/upload/${shoe.fileUpload.name}`"></v-img>
          <video  controls v-else-if="shoe.fileUpload.contentType === 'video/mp4'" width="100%"  height="250">
            <source  :src="`http://192.168.202.23:8088/upload/${shoe.fileUpload.name}`" type="video/mp4">
          </video>

          <v-toolbar color="transparent" class="mt-n7" flat>
            <v-avatar color="white" rounded class="mr-2" style="background-color: #042a0f">
              <v-img v-if="shoe.fileUpload.contentType!=='video/mp4'" width="100px" height="50" :src="`http://192.168.202.23:8088/upload/${shoe.fileUpload.name}`"></v-img>
              <video  controls v-else-if="shoe.fileUpload.contentType === 'video/mp4'" width="100%"  height="170">
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
          <v-card-title class="grey--text caption" :typesId="shoe.typeProduct.id"> {{
              shoe.typeProduct.name
            }}</v-card-title>
          <v-card-text class="green--text" style="font-size: 25px;font-family: 'Albertus MT'">
            {{shoe.name}}
          </v-card-text>
          <v-card-title class="grey--text text-grey-darken-1 caption mt-n6">Kiritilgan vaqti: {{
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
              <v-chip label color="gray" text-color="#883DEF" disabled>{{ shoe.produced }}</v-chip>
              <v-chip label dark>{{ shoe.brand }}</v-chip>
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
        @input="nextperson"
        color="#6F0DFF"
    >
    </v-pagination>

<!--    <v-toolbar flat color="rgba(0,0,0,0) " class="mt-n14">-->
<!--      <v-divider vertical color="orange" inset></v-divider>-->
<!--      <v-toolbar-title class="white&#45;&#45;text ml-4"-->
<!--      >Recent Orders</v-toolbar-title-->
<!--      >-->
<!--      <v-spacer></v-spacer>-->



<!--      <v-chip-->
<!--          class="ma-1 d-none d-sm-flex"-->
<!--          color=""-->
<!--          label-->
<!--          text-color="white"-->
<!--          dark-->
<!--      >-->
<!--        <v-avatar color="white" rounded class="mr-2">-->
<!--          <v-img src="2.png" contain></v-img>-->
<!--        </v-avatar>-->
<!--        Nike new-->
<!--      </v-chip>-->
<!--      <v-chip-->
<!--          class="ma-1 d-none d-sm-flex"-->
<!--          color=""-->
<!--          label-->
<!--          text-color="white"-->
<!--          dark-->
<!--      >-->
<!--        <v-avatar color="white" rounded class="mr-2">-->
<!--          <v-img src="3.png" contain></v-img>-->
<!--        </v-avatar>-->

<!--        Zoom JO-->
<!--      </v-chip>-->
<!--      <v-chip-->
<!--          class="ma-1 d-none d-sm-flex"-->
<!--          color=""-->
<!--          label-->
<!--          text-color="white"-->
<!--          dark-->
<!--      >-->
<!--        <v-avatar color="white" rounded class="mr-2">-->
<!--          <v-img src="4.png" contain></v-img>-->
<!--        </v-avatar>-->

<!--        Jordon BZ-->
<!--      </v-chip>-->
<!--      <v-spacer></v-spacer>-->
<!--&lt;!&ndash;      <v-btn dark class="mr-2 withoutupercase d-none d-sm-flex">&ndash;&gt;-->
<!--&lt;!&ndash;        See All (129)&ndash;&gt;-->
<!--&lt;!&ndash;      </v-btn>&ndash;&gt;-->
<!--&lt;!&ndash;      <v-btn dark class="mr-2" outlined>&ndash;&gt;-->
<!--&lt;!&ndash;        <v-icon left>mdi-chevron-left</v-icon>&ndash;&gt;-->
<!--&lt;!&ndash;        <v-icon right>mdi-chevron-right</v-icon>&ndash;&gt;-->
<!--&lt;!&ndash;      </v-btn>&ndash;&gt;-->

<!--    </v-toolbar>-->

<!--    <v-row class="mt-n10">-->
<!--      <v-col-->
<!--          cols="12"-->
<!--          xs="12"-->
<!--          sm="6"-->
<!--          md="3"-->
<!--          v-for="(order, i) in orders"-->
<!--          :key="i"-->
<!--      >-->
<!--        <v-card-->
<!--            class="mx-auto my-12 rounded-lg"-->
<!--            max-width="374"-->
<!--            color="#151515"-->
<!--        >-->
<!--          <v-toolbar color="transparent" class="mt-n6" flat>-->
<!--            <v-avatar color="white" rounded class="mr-2">-->
<!--              <v-img :src="order.pic" contain></v-img>-->
<!--            </v-avatar>-->
<!--            <v-spacer></v-spacer>-->
<!--            <v-avatar color="black" rounded class="mr-2" dark>-->
<!--              <div class="three">-->
<!--                <div class="four">-->
<!--                  <span class="white&#45;&#45;text caption">{{ order.price }}</span>-->
<!--                </div>-->
<!--                <div class="six">-->
<!--                  <span class="orange&#45;&#45;text caption">Sotuvda</span>-->
<!--                </div>-->
<!--              </div>-->
<!--            </v-avatar>-->
<!--          </v-toolbar>-->
<!--        </v-card>-->
<!--      </v-col>-->
<!--    </v-row>-->
  </v-container>
</template>


<script>


import axios from "axios";

export default {
  name: "Home",
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


    shoes: [''],
    orders: [
      {
        pic: "5.png",
        price: "$465",
      },
      {
        pic: "6.png",
        price: "$714",
      },
      {
        pic: "7.png",
        price: "$982",
      },
      {
        pic: "8.png",
        price: "$394",
      },
    ],
  }),



  mounted: async function () {
    this.nextperson()
    this.setSearchIconColor('#6F0DFF');
  },
  beforeRouteUpdate(to, from, next) {
    // Call next to allow the route to update
    next();
    console.log("ishhhhhhhhhhhhhhhhhh")
    // Log the new value of the route param
    const token = 'Bearer '+sessionStorage.getItem('token')
    axios.get(`product/byType/${to.params.id}`, {
      params: {page: this.page-1, text: this.search},
      headers: {'authorization': token}
    }).then(products=>{
      this.$store.commit('setTypeId',to.params.id);
      if (products.data.content.length>=1) {
        this.shoes = products.data.content
        this.totalPages = products.data.totalPages
      }
      this.totalElement = products.data.totalElements
    })
    this.setSearchIconColor('#6F0DFF');
  },

  methods:{
    async nextperson() {
      console.log("fhdjkhfjdhfjdhjfhdjfhdjfhdjfhdjfhdj")
      const token = 'Bearer ' + sessionStorage.getItem('token');
      const products = await axios.get(`product/byType/${this.$store.state.typeId}`, {
        params: {page: this.page-1, text: this.search},
        headers: {'authorization': token}
      })
      this.totalElement = products.data.totalElements
      if (products.data.content.length>=1) {
        this.shoes = products.data.content
        this.totalPages = products.data.totalPages
      }
    },
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