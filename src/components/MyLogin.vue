<template>
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row justify="center">
          <v-col cols="6">
            <v-card class="elevation-12 mr-lg-16">
              <v-window>
                <v-window-item>
                  <v-row>
                    <v-col cols="12" md="8" style="background-color: #635F68">
                      <v-card-text class="mt-3">
                        <h4
                            class="text-center display-2 teal--text text--accent-3"
                        ></h4>
                        <div class="text-center mt-4">
                          <v-img
                              class="justify-content-center align-items-center"
                              lazy-src="../assets/web-development-4439345-3726916.webp"
                              max-height="150"
                              max-width="250"
                              src="../assets/web-development-4439345-3726916.webp"
                          ></v-img>
                        </div>
                        <v-form ref="submit" class="mt-3">
                          <v-text-field
                              label="Username"
                              v-model="data.username"
                              prepend-icon="email"
                              type="text"
                              color="#6F0DFF"
                          />

                          <v-text-field
                              id="password"
                              label="Password"
                              v-model="data.password"
                              prepend-icon="lock"
                              type="password"
                              color="#6F0DFF"

                          />
                          <div class="text-center mb-5">
                            <v-btn @click.prevent="submit" color="#6F0DFF" dark>KIRISH</v-btn>
                          </div>
                        </v-form>

                      </v-card-text>

                    </v-col>
                    <v-col cols="12" md="4" class="card_color">
                      <v-card-text class="white--text mt-12">
                        <h1 class="text-center display-1" style="color: #7FFFD4">Online shop!</h1>
                        <h5
                            class="text-center"
                        >Foydalanish uchun o'zingizning shaxsiy ma'lumotlaringiz kiritilgan va ro'yxatdan o'tgan bo'lishingiz shart</h5>
                      </v-card-text>
                     </v-col>
                  </v-row>
                </v-window-item>

              </v-window>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
</template>


<script>
import axios from 'axios';
export default {

name:"MyLogin",
  data(){
   return {
     data: {
       username: "",
       password: ""
     },
   }
  },
  props: {
    source: String
  },
        methods:{
           submit () {
              sessionStorage.clear();
              axios.post('auth/login', this.data).then(response =>{
             if (response.data){
              sessionStorage.setItem('token',response.data);
               this.$store.commit('setStatus',false)
               console.log(this.$store.state.status)
               this.$router.push('/');
             }else {
               alert("Login yoki parol noto'g'ri")
             }
            })
          },
        },
      };
    </script>

<style>
.card_color{
  background-color: #1D1D26;
}
</style>
