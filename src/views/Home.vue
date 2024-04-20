<template>
  <v-container>
      <Shoes />
  </v-container>
</template>
<script>
import Shoes from "@/views/Shoes.vue"
export default {
  name: "Home",
  props:[
      'selected_type_id'
  ],
  data(){
return{

}
  },
  components: {
    Shoes
  },
  mounted() {
  },
  methods: {},
};
</script>
<style scoped>


</style>
