<template>
  <v-row  v-if="$store.state.token" justify="center">
    <v-col cols="8">
      <v-data-table
        :headers="headers"
        :items="desserts"
        class="myForm"
      >
        <template v-slot:top >
          <v-toolbar
            flat
            style="background-color: #1D1D26"

          >
            <v-toolbar-title style="color: white">Mahsulot turi</v-toolbar-title>
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
                        <v-text-field
                          color="#6F0DFF"
                          v-model="editedItem.name"
                          label="TypeProduct"
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
                <v-card-title class="text-h5" style="color:#1D1D26">Siz rostan ham ushbu ma'lumotni o'chirmoqchimisiz ?</v-card-title>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn    color="#6F0DFF" text @click="closeDelete">Close</v-btn>
                  <v-btn    color="#6F0DFF" text @click="deleteItemConfirm">Yes</v-btn>
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
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

    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  name:'TypeProduct',
  data: () => ({
    deleteId:'',
    search:'',
    dialog: false,
    dialogDelete: false,
    token: 'Bearer ' + sessionStorage.getItem('token'),
    headers: [
      {
        text: 'Nomlanishi',
        align: 'start',
        sortable: false,
        value: 'name',

      },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      name: '',
    },
    defaultItem: {
      name: '',
    },
  }),


  mounted: async function () {
    const response = await axios.get('typeProduct/get')
    this.desserts = response.data
    // this.$store.commit('setTypeProduct',this.desserts)
  },


  computed: {
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
      console.log(this.deleteId)
      await axios.delete('typeProduct/delete/' + this.deleteId, {headers: {'authorization': this.token}})
      this.desserts.splice(this.editedIndex, 1)
      // this.$store.commit('setTypeProduct',this.desserts)
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
        await axios.put('typeProduct/edit/' + this.editedItem.id, this.editedItem, {headers: {'authorization': this.token}})
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
        // this.$store.commit('setTypeProduct',this.desserts)
      } else {
        await axios.post('typeProduct/add', this.editedItem, {headers: {'authorization': this.token}})
        this.desserts.push(this.editedItem)
        // this.$store.commit('setTypeProduct',this.desserts)
      }
      this.close()
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
</style>
