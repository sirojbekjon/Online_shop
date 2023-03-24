<template>
  <v-row v-if="$store.state.token" justify="center">
    <v-col cols="10">
      <v-card-title>
        <v-spacer></v-spacer>
      </v-card-title>
      <v-data-table
          :headers="headers"
          :items="ordersWithIndex"
          :items-per-page="itemsPerPage"
          item-key="desserts.index"
          single-expand
          :hide-default-footer="true"
          :loading="loading"
          sort-by="calories"
          class="myForm"
      >
        <template v-slot:top>
          <v-toolbar
              flat
              style="background-color: #1D1D26"
          >
            <v-toolbar-title style="color: white">Oqimlar</v-toolbar-title>
            <v-divider
                class="mx-4"
                inset
                vertical
            ></v-divider>
            <v-spacer></v-spacer>
            <v-dialog
                v-model="dialog"
                max-width="500px"
            >
            </v-dialog>
            <v-dialog v-model="dialogDelete" max-width="500px">
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.product="{ item }">
          <!--          <v-card :key="item.id" style="width: 100%; height: auto; background-color: darkblue; margin: 10px">-->
          <v-card-title>
            <v-img v-if="item.product.fileUpload.contentType!=='video/mp4'"  contain :src="`http://192.168.202.23:8088/upload/${item.product.fileUpload.name}`"   width="70px" height="auto"/>
            <video  controls v-else-if="item.product.fileUpload.contentType === 'video/mp4'" width="70px" height="auto">
              <source  :src="`http://192.168.202.23:8088/upload/${item.product.fileUpload.name}`" type="video/mp4">
            </video>
          </v-card-title>
        </template>

        <template v-slot:no-data>
          <v-btn
              color="#6F0DFF"
           >
            Reset
          </v-btn>
        </template>


        <template v-slot:item.ready="{ item }">
          <v-checkbox
              v-model="item.ready"
              class="pa-3"
              readonly
              color="orange"
          ></v-checkbox>
        </template>
        <template v-slot:item.hold="{ item }">
          <v-checkbox
              v-model="item.hold"
              class="pa-3"
              readonly
              color="orange"
          ></v-checkbox>
        </template>
        <template v-slot:item.neww="{ item }">
          <v-checkbox
              v-model="item.neww"
              :label="item.neww ? 'Yangi' : 'Eski'"
              class="pa-3"
              readonly
              color="green"
          ></v-checkbox>
        </template>
        <template v-slot:item.onway="{ item }">
          <v-checkbox
              v-model="item.onway"
              class="pa-3"
              readonly
              color="orange"
          ></v-checkbox>
        </template>
        <template v-slot:item.delivered="{ item }">
          <v-checkbox
              v-model="item.delivered"
              class="pa-3"
              readonly
              color="orange"
          ></v-checkbox>
        </template>
        <template v-slot:item.canceled="{ item }">
          <v-checkbox
              v-model="item.canceled"
              class="pa-3"
              readonly
              color="red"
          ></v-checkbox>
        </template>

        <template v-slot:item.adminname="{item}">
          <v-tab>
            {{ item.flow.user.username }}
          </v-tab>
        </template>


      </v-data-table>
      <v-col cols="12" md="6">
        <v-pagination
            v-model="page"
            :length="pageCount"
            @input="nextperson"
            color="#6F0DFF"
        >
        </v-pagination>

      </v-col>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  props:[
    'selected_type_id'
  ],
  name:'product',
  data: () => ({
    totalElement:'',
    loading: true,
    dialog: false,
    dialogDelete: false,
    i:1,
    page: 1,
    totalPages:0,
    itemsPerPage: 10,
    token: 'Bearer ' + sessionStorage.getItem('token'),

    perPageChoices: [
      {text:'5 records/page' , value: 5},
      {text:'10 records/page' , value: 10},
      {text:'20 records/page' , value: 20},
    ],

    headers: [
      {    text: 'T/R',
        align: 'start',
        sortable: true,
        value: 'index',},
      {
        text: 'Oqim nomi',
        align: 'start',
        sortable: false,
        value: 'flow.name',
      },
      { text: 'sotuvchisi ismi', value: 'adminname' },
      { text: 'Yangi', value: 'neww' },
      { text: 'Tayyor', value: 'ready' },
      { text: 'Ushlab turilibti', value: 'hold' },
      { text: 'Yo\'lda', value: 'onway' },
      { text: 'Yetkazib berildi', value: 'delivered' },
      { text: 'Bekorqilindi', value: 'canceled' },
      { text: 'Ko\'rishlar soni', value: 'flow.visits_count' },
      { text: 'Surati', value: 'product' },
      // { text: 'Admin haqqi', value: 'archived' },

    ],
    desserts: [],
  }),


  mounted: async function () {
    this.nextperson()
  },


  computed: {




    ordersWithIndex(){
      return this.desserts.map(
          (items, index) => ({
            ...items,
            index: (this.page-1)*10 + index + 1
          }))
    },


    totalRecords() {
      return this.desserts.length
    },


    pageCount() {
      return this.totalPages
      // this.totalRecords / this.itemsPerPage
    },
  },

  methods: {
    async nextperson() {
      const response = await axios.get('client/get/userId', {
        params: {page: this.page - 1},
        headers: {'authorization': this.token}
      })
      this.totalElement = response.data.totalElements
      if (response.data.length !== 0) {
        this.desserts = response.data.content
        this.totalPages = response.data.totalPages
        this.loading = false
      } else {
        this.desserts = response.data.content
        this.totalPages = response.data.totalPages
        this.loading = false
      }

    },

  },
}
</script>



<style scoped>
.myForm{
  color: white !important;
  background-color: #635F68!important;
}
.myForm:hover{
  color: #1D1D26 !important;
}
.searchPanel{
  text-shadow: 0 1px white !important;
  color: red !important;
  box-shadow: 0 1px #6F0DFF !important;

}
.searchPanel .v-input__icon--append .v-icon::before {
  color: blue !important;
}

</style>