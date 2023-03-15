<template>
  <v-container>
      <Shoes :typedId="selected_type_id"/>
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
    console.log(this.selected_type_id)
  },
  methods: {},
};
</script>
<style scoped>


</style>
