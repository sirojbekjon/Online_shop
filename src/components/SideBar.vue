<template>
  <v-container>
  <MyNavbar @drawe="drawer = !drawer"/>
  <v-navigation-drawer
    :class="{ 'darkCard': darkMode }"
    v-model="drawer"
    app
  >
    <v-avatar class="d-block text-center mx-auto mt-4 mb-10" size="80" :class="{ 'darkCard': darkMode }">
      <v-btn class="ma-2" outlined large fab >
        <v-icon x-large>mdi-shopping-search-outline</v-icon>
      </v-btn>
    </v-avatar>

    <v-card flat color="transparent">
      <v-list flat >
        <v-list-item-group v-model="selectedItem">
          <v-list-item
            v-for="(item, i) in items"
            :to="item.path"
            :key="i"
            active-class="green"
            v-slot="{ active }"

          >
            <v-list-item :to="item.path" >
              <v-list-item-icon class="mr-2">
                <v-icon :color="active ? 'red' : 'gray '"  >{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>
                <v-list-item>{{ item.name }}</v-list-item>
              </v-list-item-title>
            </v-list-item>
          </v-list-item>
        </v-list-item-group>
        <v-list-item-group :class="{ 'darkCard': darkMode }" v-if="$store.state.role==='SUPERADMIN'" v-model="selectedItem">
          <v-list-item
              v-for="(item, i) in items2"
              :to="item.path"
              :key="i"
              active-class="green"
              v-slot="{ active }"
          >
            <v-list-item :to="item.path" >
              <v-list-item-icon class="mr-2">
                <v-icon :color="active ? 'red' : 'gray'">{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>
                <v-list-item>{{ item.name }}</v-list-item>
              </v-list-item-title>
            </v-list-item>
          </v-list-item>
        </v-list-item-group>
        <v-list-item-group :class="{ 'darkCard': darkMode }" v-if="$store.state.role==='ADMIN' || $store.state.role==='SUPERADMIN'" v-model="selectedItem">
          <v-list-item
              v-for="(item, i) in items3"
              :to="item.path"
              :key="i"
              active-class="green"
              v-slot="{ active }"

          >
            <v-list-item :to="item.path" >
              <v-list-item-icon class="mr-2">
                <v-icon :color="active ? 'red' : 'gray '">{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>
                <v-list-item>{{ item.name }}</v-list-item>
              </v-list-item-title>
            </v-list-item>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-card>
  </v-navigation-drawer>

  </v-container>
</template>
<script>
import MyNavbar from "@/components/MyNavbar";

export default {
  name:'SideBar',
  components:{
    MyNavbar
  },
  data: () => ({
    props:[
        'typeId'
    ],
    darkMode: false,
    drawer:false,
    selectedItem: 0,
    items: [
      {name:'Home', icon: "mdi-home-outline",path:'/' },
      {name:'Market', icon: "mdi-cart",path:'/market' },
    ],
    items2:[
      {name:'Mahsulot turi', icon: "mdi-basket",path: '/typeProduct'},
      {name:'Mahsulotlar',icon: "shopping",path: '/product'},
      {name:'Yaratilgan oqimlar',icon: "mdi-pencil",path: '/flows'},
      {name:'Buyurtmalar',icon: "mdi-cart-variant",path: '/order'},
      {name:'Adminlar',icon: "people",path: '/user'},
      {name:'Shaxsiy Ma\'lumotlarim',icon: "person",path: '/myform'},

    ],
    items3:[
      {name:'Oqim', icon: "mdi-clipboard-flow-outline",path: '/flow_url'},
      {name:'Statistika',icon: "mdi-finance",path: '/statistic'},
      {name:'To\'lov',icon: "money",path: '/payment'},
    ],
  }),

  watch: {
    drawer(value) {
      return value;
    },
    role(){
      return this.role();
    },
    darkMode(newVal) {
      this.$vuetify.theme.dark = newVal; // update the Vuetify theme
    }
  },

};
</script>

<style scoped>
.darkCard {
  background-color: #222;
  color: #000;
}


.border {
  margin: 0px 8px;
  background: #6f0dff;
  border-radius: 15px;
  text-decoration: none;
  width: 60px;
  height: 60px;
}
.v-list-item-group .v-list-item--active {
  color: white !important;
}
.v-list-item__content {
  padding: 20px 0 !important;
}
.image {
  border: 1px solid white;
}
</style>
