<template>
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row justify="center">
          <v-col xs="12" sm="8" md="6" lg="4">
            <v-card class="elevation-12 mr-lg-16"  :class="{ 'centered-form': this.$store.state.isSidebarOpen}">
              <v-window>
                <v-window-item>
                  <v-row>
                    <v-col cols="12" md="8" style="background-color: #635F68">
                      <v-card-text class="mx-auto my-2 rounded-xl"  xs="12" sm="8" md="6" lg="4">
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

                        <v-container>
                              <v-form ref="submit">
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
                                <div class="text-center mt-4">
                                  <v-btn @click.prevent="submit" color="#6F0DFF" dark>KIRISH</v-btn>
                                <v-alert
                                    class="mt-6"
                                    :value="alert"
                                    :color="alert_color"
                                    dark
                                    border="left"
                                    transition="scale-transition"
                                    @input="alert = false"
                                >
                                  <div class="d-flex align-center">
                                    <v-icon class="mr-2">mdi-alert-circle-outline</v-icon>
                                    <span>{{ message }}</span>
                                  </div>
                                </v-alert>
                                <h4 class="text-center white--text mt-6 mb-2">
                                  Iltimos profilngiz bo'lmasa ro'yhatdan o'ting!
                                </h4>
                                  <v-btn to="/register" :style="{ width: '100%' }" color="#6F0DFF" dark>
                                    {{registertext}}
                                  </v-btn>
                                </div>
                              </v-form>
                        </v-container>

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
     registertext:'Ro\'yhatdan o\'tish',
     isSidebarOpen: false,
     drawer:false,
     alert: false,
     alert_color:'',
     message: '',
     data: {
       username: "",
       password: "",
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
              if (response.status===202){
               this.$store.commit('setStatus',false)
                this.$store.commit('set',false)
                this.$router.push('/market');
                sessionStorage.setItem('token',response.data);
                window.location.reload();
              }
            }
            ).catch(()=>{
              this.alert = true
              this.alert_color='red'
              this.message = 'Parol yoki login noto\'g\'ri'
              setTimeout(() => {
                this.alert = false;
              }, 5000);
            })
          },
        },

  // watch: {
  //   drawer(value) {
  //     return value == false;
  //   }
  // },
      };
    </script>

<style>
/* Default button style */
.materialDesignButton {
  color:#fff;
  border-radius:5px;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size:1.04em;
  letter-spacing: 0.05em;
  width: auto;
  transition: 0.3s;
}

.materialDesignButton:hover {
  opacity: 0.7;
}

/* Media query for smaller screens */
@media (max-width: 600px) {
  .responsive-btn {
    padding: 8px 16px;
    font-size: 14px;
  }
}


.card_color{
  background-color: #1D1D26;
}
.centered-form {
  display: flex;
  align-items: center;
  justify-content: center;
}


</style>
