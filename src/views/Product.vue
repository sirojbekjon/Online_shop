<template>
  <v-row v-if="$store.state.token" justify="center">
    <v-col cols="10">
      <v-card-title>
        <v-spacer></v-spacer>
        <v-text-field
            background-color="#635F68"
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            hide-details
            class="searchPanel"
            ref="searchField"
            @input="nextperson"
            translate="yes"
            solo
            solo-inverted
        >
        </v-text-field>


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


        <template v-slot:item.fileUpload="{ item }">
<!--          <v-card :key="item.id" style="width: 100%; height: auto; background-color: darkblue; margin: 10px">-->
            <v-card-title>
            <v-img v-if="item.fileUpload.contentType!=='video/mp4'"  contain :src="`http://192.168.202.23:8088/upload/${item.fileUpload.name}`"   width="100px" height="auto"/>
            <video  controls v-else-if="item.fileUpload.contentType === 'video/mp4'" width="100px" height="auto">
              <source  :src="`http://192.168.202.23:8088/upload/${item.fileUpload.name}`" type="video/mp4">
            </video>
            </v-card-title>
<!--          </v-card>-->
        </template>


        <template v-slot:top>
          <v-toolbar
              flat
              style="background-color: #1D1D26"
          >
            <v-toolbar-title style="color: white">Mahsulot</v-toolbar-title>
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
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                    color="#6F0DFF"
                    dark
                    class="mb-2"
                    v-bind="attrs"
                    v-on="on"
                >
                  Create
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="text-h5">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                      <v-select
                          :items="typeProduct"
                          item-text="name"
                          item-value="id"
                          v-model="editedItem.typeProduct"
                          label="Mahsulot turi"
                          persistent-hint
                          single-line
                      ></v-select>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <v-text-field
                            v-model="editedItem.name"
                            label="Nomlanishi"
                        ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <v-text-field
                            v-model="editedItem.produced"
                            label="Firmasi"
                        ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <v-text-field
                            v-model="editedItem.about"
                            label="Mahsulot haqida"
                        ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <v-text-field
                            v-model="editedItem.price"
                            label="narxi"
                        ></v-text-field>
                      </v-col>       <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <v-text-field
                            v-model="editedItem.salary"
                            label="Admin to'lovi"
                        ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <v-text-field
                            v-model="editedItem.sale"
                            label="chegirma"
                        ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <input type="file" ref="fileInput" @change="onFileSelected">
                      </v-col>


                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                      color="#6F0DFF"
                      text
                      @click="close"
                  >
                    Close
                  </v-btn>
                  <v-btn
                      color="#6F0DFF"
                      text
                      @click="save"
                  >
                    Save
                  </v-btn>
                </v-card-actions>
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
        <template v-slot:item.switch="{ item }">
          <v-switch
              v-model="item.status"
              :label="`${ item.status ? 'Mavjud' : 'Tugagan'}`"
              class="pa-3"
              @click="changedStatus(item)"
          ></v-switch>
        </template>
        <template v-slot:item.actions="{ item }">
          <v-icon
              small
              class="mr-2"
              @click="editItem(item)"
              color="green"
          >
            mdi-pencil
          </v-icon>
          <v-icon
              small
              @click="deleteItem(item)"
              color="red"
          >
            mdi-delete
          </v-icon>
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
    files:'',
    fileType:'',
    imageUrl: '',
    search:'',
    totalElement:'',
    typeProduct:[],
    deleteId:'',
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
        text: 'Mahsulot',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      { text: 'Mahsulot Turi', value: 'typeProduct.name' },
      { text: 'Mahsulot haqida', value: 'about' },
      { text: 'Admin to\'lovi', value: 'salary' },
      { text: 'Narxi', value: 'price' },
      { text: 'Ishlab chiqarilgan', value: 'produced' },
      { text: 'Chegirma', value: 'sale' },
      { text: 'Faollik', value: 'switch'},
      { text: 'Surati', value: 'fileUpload' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      name: '',
      typeProduct: [],
      salary:'',
      about:'',
      price:'',
      produced:'',
      sale:'',
      status:true,
      fileUpload:''

    },
    defaultItem: {
      name: '',
      typeProduct: '',
    },
  }),


  mounted: async function () {
    this.nextperson()
    const typeProductResponse = await axios.get('typeProduct/get', {
      headers: {'authorization': this.token}
    })
    this.typeProduct = typeProductResponse.data
    this.setSearchIconColor('#6F0DFF');
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





    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
  },

  watch: {
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
    '$route.query.page': {
      handler() {
        this.nextperson();
      },
      immediate: true
    }
  },
  created () {
    this.initialize()
  },

  methods: {
    getImageUrl(file) {
      return file.imageUrl;
    },

    async changedStatus(item) {
      await axios.put(`product/edit/status/${item.id}`,this.editedItem, {headers: {'authorization': this.token}})
    },

    onFileSelected() {
      this.files = this.$refs.fileInput.files;
      this.uploadFiles()
    },
    async uploadFiles() {
      const formData = new FormData();

      for (let i = 0; i < this.files.length; i++) {
        formData.append('files', this.files[i], this.files[i].name);
      }
      const response = await axios.post("file/upload", formData,
          {headers:{Accept:'application/json','Content-Type': 'multipart/form-data','authorization': this.token}});
            this.editedItem.fileUpload = response.data.id
    },

    async nextperson() {
      const response = await axios.get('product/get', {
        params: {page: this.page - 1, text: this.search},
        headers: {'authorization': this.token}
      })
      this.totalElement = response.data.totalElements
      if (this.search !== '' && this.search.length > 3 && response.data.length !== 0) {
        this.desserts = response.data.content
        this.totalPages = response.data.totalPages
        this.loading = false
      } else {
        this.desserts = response.data.content
        this.totalPages = response.data.totalPages
        this.loading = false
      }
    },


    initialize () {
    },

    editItem(item) {
      this.editedIndex = this.ordersWithIndex.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem (item) {
      this.deleteId = item.id
      this.editedIndex = this.ordersWithIndex.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteItemConfirm() {
      await axios.delete('product/delete/' + this.deleteId, {headers: {'authorization': this.token}})
      this.desserts.splice(this.editedIndex, 1)
      this.closeDelete()
    },

    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    closeDelete () {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },


    async save() {
      if (this.editedIndex > -1) {
        if (isNaN(this.editedItem.typeProduct)){this.editedItem.typeProduct = this.editedItem.typeProduct.id}
        if (isNaN(this.editedItem.fileUpload)){this.editedItem.fileUpload = this.editedItem.fileUpload.id}
        await axios.put('product/edit/' + this.editedItem.id, this.editedItem, {headers: {'authorization': this.token}})
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
        this.close()
        this.nextperson()
      } else {
        await axios.post('product/add', this.editedItem, {headers: {'authorization': this.token}})
        this.nextperson()
      }
      this.close()
    },



    setSearchIconColor(color) {
      const icon = this.$refs.searchField.$el.querySelector(
          '.v-input__icon--append .v-icon'
      );
      icon.style.color = color;
    }
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