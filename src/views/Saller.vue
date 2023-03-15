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
              <v-card>
                <v-card-title>
                  <span class="text-h5">{{ formTitle }}</span>
                </v-card-title>
              </v-card>
            </v-dialog>
            <v-dialog v-model="dialogDelete" max-width="500px">
              <v-card>
                <v-card-title class="text-h5">Siz rostan ham ushbu ma'lumotni o'chirmoqchimisiz ?</v-card-title>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="#6F0DFF" text @click="closeDelete">Yopish</v-btn>
                  <v-btn color="#6F0DFF" text @click="deleteItemConfirm">Roziman</v-btn>
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:no-data>
          <v-btn
              color="#6F0DFF"
              @click="initialize"
          >
            Reset
          </v-btn>
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
        value: 'name',
      },
      { text: 'sotuvchisi ismi', value: 'user.username' },
      { text: 'sotuvchisi nomeri', value: 'user.phoneNumber' },
      { text: 'Ko\'rishlar soni', value: 'visits_count' },

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
      const response = await axios.get('flow/all', {
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