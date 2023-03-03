<template >
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row justify="center">
          <v-col cols="8">
            <v-card class="elevation-12 mr-8">
              <v-window>
                  <v-window-item>
                  <v-row class="fill-height">
                    <v-col cols="12" md="4" class="card_color">
                      <v-card-text class="white--text mt-12 text">
                        <h1 class="display-1">Foydalanuvchilarni ro'yxatdan o'tkazish oynasi</h1>
                        <h5
                            class="text-center"
                        >kiritgan parolingizni yodda salqashni unutmang</h5>
                      </v-card-text>
                      </v-col>
                    <v-col cols="12" md="8">
                      <v-card-text class="mt-12">
                        <div class="text-center mt-4">
                          <v-img
                              class="justify-content-center align-items-center"
                              lazy-src="../assets/web-development-4439345-3726916.webp"
                              max-height="150"
                              max-width="250"
                              src="../assets/web-development-4439345-3726916.webp"
                          ></v-img>
                        </div>
                        <h5 class="text-center display-1 blue-grey--text text--accent-3">Foydalanuvchi ma'lumotlarini kiriting</h5>
                        <v-form ref="submit">
                          <v-text-field
                              label="Login"
                              v-model="data.username"
                              prepend-icon="person"
                              type="text"
                              color="#27496D"
                          />
                          <v-text-field
                              label="Parol"
                              v-model="data.password"
                              prepend-icon="lock"
                              type="password"
                              color="#27496D"
                          />
                          <v-text-field
                              label="Telefon raqam"
                              v-model="data.phoneNumber"
                              prepend-icon="lock"
                              type="text"
                              color="#27496D"
                          />
                          <div class="text-center mb-5">
                            <v-btn @click.prevent="submit" color="#27496D" dark>SAQLASH</v-btn>
                          </div>
                        </v-form>
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
  name:"myregister",
  data(){
    return {
       data: {
        username: "",
        password: "",
         phoneNumber: ""
      },
    }
  },
  props: {
    source: String
  },

  mounted: async function () {
    },
  methods:{
    submit(){
      console.log(this.data)
      axios.post('auth/register',this.data);
      this.data=''
    },
  },
};
</script>
<style>
.text h1{
  text-align: center;
  color: aquamarine;
  margin-top: 200px;
}
.text h5{
  color: yellow;
  margin-top: 50px;
  font-size: 20px;
}
</style>