<template>
  <v-row v-if="$store.state.token" justify="center">
    <v-col cols="10">
      <v-card-title>
        <v-spacer></v-spacer>
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            hide-details
            class="searchPanel"
            ref="searchField"
            translate="yes"
            solo-inverted
            light
        >
        </v-text-field>
      </v-card-title>
      <v-data-table
          :headers="headers"
          :items="ordersWithIndex"
          :items-per-page="itemsPerPage"
          item-key="desserts.index"
          single-expand
          :search="search"
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
                      <v-select
                          :items="typeProduct"
                          item-text="name"
                          item-value="id"
                          v-model="editedItem.typeProduct"
                          label="Mahsulot turi"
                          persistent-hint
                          single-line
                      ></v-select>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <v-text-field
                            v-model="editedItem.name"
                            label="Mahsulot"
                        ></v-text-field>
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

        <template v-slot:item.fileUpload_id="{}">
          <div>
           <img v-if="fileType!=='video/mp4'"  :src="getImageUrl(imageUrl)" width="100px" height="100px"/>
           <video controls v-else-if="fileType === 'video/mp4'" width="200px" height="200px">
            <source :src="imageUrl" type="video/mp4">
            </video>
          </div>
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
  name:'product',
  data: () => ({
    files:'',
    fileType:'',
    imageUrl: '',
    search:'',
    totalElement:'',
    typeProduct:'',
    deleteId:'',
    loading: true,
    dialog: false,
    dialogDelete: false,
    i:1,
    page: 1,
    totalPages:0,
    itemsPerPage: 10,
    token: 'Bearer ' + sessionStorage.getItem('token'),
    headers: [
      {
        text: 'Mahsulot',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      { text: 'Mahsulot Turi', value: 'typeProduct.name' },
      { text: 'Mahsulot haqida', value: 'about' },
      { text: 'Brand', value: 'brand' },
      { text: 'Narxi', value: 'price' },
      { text: 'Ishlab chiqarilgan', value: 'produced' },
      { text: 'Chegirma', value: 'sale' },
      { text: 'Mavjudligi', value: 'status' },
      { text: 'Surati', value: 'fileUpload_id' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      name: '',
      typeProduct: '',
    },
    defaultItem: {
      name: '',
      typeProduct: '',
    },
  }),


  mounted: async function () {
    this.setSearchIconColor('#6F0DFF');
    const typeProductResponse = await axios.get('typeProduct/get', {
      headers: {'authorization': this.token}
    })
    this.typeProduct = typeProductResponse.data
    const response = await axios.get('product/get', {
      params: {page: this.page - 1, text: this.search},
      headers: {'authorization': this.token}
    })
    if (this.search !== '' && this.search.length > 3 && response.data.length !== 0) {
      this.desserts = response.data.content
      this.totalPages = response.data.totalPages
      this.loading = false
    } else {
      console.log(response.data.content[0].fileUpload.id)
      this.desserts = response.data.content
      this.totalPages = response.data.totalPages
      this.loading = false

    }

    axios.get('file/download/1', {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'multipart/form-data',
        'authorization': this.token
      },
      responseType: 'blob' // set response type to blob
    })
        .then(response => {
          const blobUrl = URL.createObjectURL(response.data); // create blob URL
          this.imageUrl = blobUrl;
          this.fileType = response.data.type
          console.log(response)
        })
        .catch(error => {
          console.log(error);
        });


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
      return this.editedIndex === -1 ? 'Create' : 'Edit'
    },
  },

  watch: {
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
  },

  created () {
    this.initialize()
  },

  methods: {
    getImageUrl(file) {
      return file.imageUrl;
    },



    async nextperson() {
      const response = await axios.get('personal/get', {
        params: {page: this.page-1,text:this.search},
        headers: {'authorization': this.token}
      })
      this.totalElement = response.data.totalElements
      if (this.search!== '' && this.search.length > 3 && response.data.length!==0){
        this.desserts=response.data.content
        this.totalPages = response.data.totalPages
        this.loading=false
      }else {
        this.desserts = response.data.content
        this.totalPages = response.data.totalPages
        this.loading=false
      }
    },



    initialize () {
    },

    editItem (item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem (item) {
      this.deleteId = item.id
      this.editedIndex = this.desserts.indexOf(item)
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
        if (this.editedItem.region.name){this.editedItem.region = this.editedItem.region.id}
         await axios.put('product/edit/' + this.editedItem.id, this.editedItem, {headers: {'authorization': this.token}})
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
      } else {
        await axios.post('product/add', this.editedItem, {headers: {'authorization': this.token}})
        this.desserts.push(this.editedItem)
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
  color: white !important;
  box-shadow: 0 1px #6F0DFF !important;

}
.searchPanel .v-input__icon--append .v-icon::before {
  color: blue !important;
}




</style>