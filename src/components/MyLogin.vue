<template>
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row justify="center">
          <v-col cols="6">
            <v-card class="elevation-12 mr-lg-16">
              <v-window>
                <v-window-item>
                  <v-row>
                    <v-col cols="12" md="8">
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
                              color="#27496D"
                          />

                          <v-text-field
                              id="password"
                              label="Password"
                              v-model="data.password"
                              prepend-icon="lock"
                              type="password"
                              color="#27496D"

                          />
                          <div class="text-center mb-5">
                            <v-btn @click.prevent="submit" color="#27496D" dark>SIGN IN</v-btn>
                          </div>
                        </v-form>

                      </v-card-text>

                    </v-col>
                    <v-col cols="12" md="4" class="card_color">
                      <v-card-text class="white--text mt-12">
                        <h1 class="text-center display-1">Online shop!</h1>
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
              this.$router.push('/');
              window.location.reload()
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
  background-color: #27496D;
}
</style>