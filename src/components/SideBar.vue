<template>
  <v-container>
  <MyNavbar @drawe="drawer = !drawer"/>
  <v-navigation-drawer
    v-model="drawer"
    app
    color="#000000"
    mini-variant
    mini-variant-width="110"
  >
    <v-avatar class="d-block text-center mx-auto mt-4 mb-10" size="80">
      <v-btn class="ma-2" outlined large fab color="white">
        <v-icon color="" x-large>mdi-basket</v-icon>
      </v-btn>
    </v-avatar>

    <v-card flat color="#151515" class="rounded-xl mx-4 py-10">
      <v-list flat class="dark">
        <v-list-item-group v-model="selectedItem">
          <v-list-item
            v-for="(item, i) in items"
            :to="item.path"
            :key="i"
            active-class="border"
            v-slot="{ active }"
            :ripple="false"
          >
            <v-list-item-content>
              <v-icon
                v-text="item.icon"
                :color="active ? 'white' : 'grey lighten-1'"

              ></v-icon>
            </v-list-item-content>
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
  components:{
    MyNavbar
  },
  data: () => ({
    props:[
        'typeId'
    ],
    drawer:true,
    selectedItem: 0,

    items: [
      { icon: "mdi-home-outline",path:'/' },
      { icon: "mdi-cart-outline",path: '/register' },
      { icon: "mdi-store-outline",path: '/flow_url'},
      { icon: "mdi-calendar-check-outline",path: '/typeProduct' },
      { icon: "mdi-apps",path: '/flow' },
      { icon: "mdi-apps",path: '/product' },
    ],
  }),

  watch: {
    drawer(value) {
      return value;
    },
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
