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



        <template v-slot:item.createdAt="{ item }">
          <v-card-title>
          <h5>
            {{item.createdAt ? item.createdAt.substring(0,10) : 'N/A'}}
          </h5>
          </v-card-title>
        </template>
        <template v-slot:item.flow.user="{ item }">
          <v-card-title>
          <h5>
            {{item.flow ? item.flow.user.phoneNumber : ''}} | {{item.flow ? item.flow.user.username : 'Sotuvchisiz'}}
          </h5>
          </v-card-title>
        </template>
        <template v-slot:item.product="{ item }">
          <!--          <v-card :key="item.id" style="width: 100%; height: auto; background-color: darkblue; margin: 10px">-->
          <v-card-title >
            <v-img v-if="item.product.fileUpload.contentType!=='video/mp4'"  contain :src="`https://arzongina.uz/upload/${item.product.fileUpload.name}`"   width="100px" height="auto"/>
            <video  controls v-else-if="item.product.fileUpload.contentType === 'video/mp4'" width="100px" height="auto">
              <source  :src="`https://arzongina.uz/upload/${item.product.fileUpload.name}`" type="video/mp4">
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

        <template v-slot:item.neww="{ item }">
          <v-checkbox
              color="orange"
              v-model="item.neww"
              :label="item.neww ? 'Yangi' : 'Eski'"
              class="pa-3"
              @click="changedOnway(item,'neww')"
          ></v-checkbox>
        </template>

        <template v-slot:item.onway="{ item }">
          <v-checkbox
              success
              v-model="item.onway"
              :label="item.onway ? 'Yo\'lda' : ''"
              class="pa-3"
              @click="changedOnway(item,'onway')"
          ></v-checkbox>
        </template>

        <template v-slot:item.ready="{ item }">
          <v-checkbox
              success
              v-model="item.ready"
              :label="item.ready ? 'Tayyor' : ''"
              class="pa-3"
              @click="changedOnway(item,'ready')"
          ></v-checkbox>
        </template>
        <template v-slot:item.canceled="{ item }">
          <v-checkbox
              error
              v-model="item.canceled"
              :label="item.canceled ? 'Bekor qilindi' : ''"
              class="pa-3"
              @click="changedOnway(item,'canceled')"
          ></v-checkbox>
        </template>
        <template v-slot:item.hold="{ item }">
          <v-checkbox
              success
              v-model="item.hold"
              :label="item.hold ? 'Saqlab turilipti' : ''"
              class="pa-3"
              @click="changedOnway(item,'hold')"
          ></v-checkbox>
        </template>
        <template v-slot:item.salary="{ item }">
          <v-checkbox
              yellow
              v-model="item.salary"
              :label="item.salary ? 'To\'landi' : ''"
              class="pa-3"
              @click="changedOnway(item,'salary')"
          ></v-checkbox>
        </template>



        <template v-slot:item.switch="{ item }">
          <v-switch
              v-model="item.delivered"
              :label="`${ item.delivered ? 'Yetkazildi' : 'Yetkazilmadi'}`"
              class="pa-3"
              @click="changedOnway(item,'delivered')"
          ></v-switch>
        </template>


        <template v-slot:item.actions="{ item }">
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
  name:'OrderProduct',
  data: () => ({
    files:'',
    fileType:'',
    onway:false,
    imageUrl: '',
    search:'',
    totalElement:'',
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
        value: 'index',
      },
      {
        text: 'Buyurtmachi',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      { text: 'Telefon nomeri', value: 'phoneNumber' },
      { text: 'Buyurtma berilgan', value: 'createdAt' },
      { text: 'Oqim nomi', value: 'flow.name' },
      { text: 'sotuvchisi', value: 'flow.user' },
      { text: 'Mahsulot', value: 'product.name' },
      { text: 'Narxi', value: 'product.price' },
      { text: 'buyurtma', value: 'neww' },
      { text: 'Tayyor', value: 'ready' },
      { text: 'Ushlab turilipti', value: 'hold'},
      { text: 'Yo\'lda', value: 'onway' },
      { text: 'Bekor qilindi', value: 'canceled' },
      { text: 'Yetkazib berildi', value: 'switch'},
      // { text: 'Yetkazib berildi', value: 'flow.delivered' },
      // { text: 'Yetkazib berildi', value: 'status'},
      { text: 'surati', value: 'product' },
      // { text: 'Admin haqqi', value: 'salary' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    flow:null,
    desserts: [''],
    editedIndex: -1,
    editedItem: {
      name: '',
      typeProduct: [],
      flow:[],
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
    this.nextperson();
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


   async changedOnway(item,text) {
     console.log(item)
     console.log(text)
      await axios.put(`client/edit/${item.id}`,item, {params:{type:text},headers: {'authorization': this.token}})
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
      await axios.get('client/getAll', {
        params: {page: this.page - 1, text: this.search},
        headers: {'authorization': this.token}
      }).then(response=>{
        console.log(response)
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
      })
      this.setSearchIconColor('#6F0DFF');

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


    // async save() {
    //   if (this.editedIndex > -1) {
    //     if (isNaN(this.editedItem.typeProduct)){this.editedItem.typeProduct = this.editedItem.typeProduct.id}
    //     if (isNaN(this.editedItem.fileUpload)){this.editedItem.fileUpload = this.editedItem.fileUpload.id}
    //     await axios.put('product/edit/' + this.editedItem.id, this.editedItem, {headers: {'authorization': this.token}})
    //     Object.assign(this.desserts[this.editedIndex], this.editedItem)
    //     this.close()
    //     this.nextperson()
    //   } else {
    //     await axios.post('product/add', this.editedItem, {headers: {'authorization': this.token}})
    //     this.desserts.push(this.editedItem)
    //   }
    //   this.close()
    // },



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