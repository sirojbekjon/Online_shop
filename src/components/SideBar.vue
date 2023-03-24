<template>
  <v-container>
  <MyNavbar @drawe="drawer = !drawer"/>
  <v-navigation-drawer
    v-model="drawer"
    app
    clipped flat dark
    color="#151515"
    fixed
  >
    <v-avatar class="d-block text-center mx-auto mt-4 mb-10" size="80">
      <v-btn class="ma-2" outlined large fab color="white">
        <v-icon color="" x-large>mdi-basket</v-icon>
      </v-btn>
    </v-avatar>

    <v-card flat color="#151515" class="py-md-12 mx-xl-n2">
      <v-list flat class="dark">
        <v-list-item-group v-model="selectedItem">
          <v-list-item
            v-for="(item, i) in items"
            :to="item.path"
            :key="i"
            active-class="green"
            dark
            v-slot="{ active }"

          >
            <v-list-item :to="item.path" >
              <v-list-item-icon class="mr-2">
                <v-icon :color="active ? 'red' : 'white '">{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>
                <v-list-item>{{ item.name }}</v-list-item>
              </v-list-item-title>
            </v-list-item>
          </v-list-item>
        </v-list-item-group>
        <v-list-item-group v-if="$store.state.role==='SUPERADMIN'" v-model="selectedItem">
          <v-list-item
              v-for="(item, i) in items2"
              :to="item.path"
              :key="i"
              active-class="green"
              dark
              v-slot="{ active }"
          >
            <v-list-item :to="item.path" >
              <v-list-item-icon class="mr-2">
                <v-icon :color="active ? 'red' : 'white '">{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>
                <v-list-item>{{ item.name }}</v-list-item>
              </v-list-item-title>
            </v-list-item>
          </v-list-item>
        </v-list-item-group>
        <v-list-item-group v-if="$store.state.role==='ADMIN' || $store.state.role==='SUPERADMIN'" v-model="selectedItem">
          <v-list-item
              v-for="(item, i) in items3"
              :to="item.path"
              :key="i"
              active-class="green"
              dark
              v-slot="{ active }"

          >
            <v-list-item :to="item.path" >
              <v-list-item-icon class="mr-2">
                <v-icon :color="active ? 'red' : 'white '">{{ item.icon }}</v-icon>
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
    drawer:false,
    selectedItem: 0,
    items: [
      {name:'Home', icon: "mdi-home-outline",path:'/' },
      {name:'Market', icon: "mdi-home-outline",path:'/market' },

    ],
    items2:[
      {name:'Mahsulot turi', icon: "mdi-calendar-check-outline",path: '/typeProduct'},
      {name:'Mahsulotlar',icon: "mdi-apps",path: '/product'},
      {name:'Yaratilgan oqimlar',icon: "mdi-pencil",path: '/flows'},
      {name:'Buyurtmalar',icon: "mdi-cart",path: '/order'},
      {name:'Adminlar',icon: "mdi-human",path: '/user'},

    ],
    items3:[
      {name:'Oqim', icon: "mdi-store-outline",path: '/flow_url'},
      {name:'Statistika',icon: "mdi-cart",path: '/statistic'},
      {name:'To\'lov',icon: "money",path: '/payment'},
    ],
  }),

  watch: {
    drawer(value) {
      return value;
    },
    role(){
      return this.role();
    }
  },
};
</script>

<style>
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
