<template >
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row justify="center">
          <v-col xs="12" sm="8" md="6" lg="4">
            <v-card class="elevation-24 mr-lg-16">
              <v-window>
                  <v-window-item>
                  <v-row class="fill-height">
<!--                    <v-col cols="12" md="4" class="card_color">-->
<!--                      <v-card-text class="white&#45;&#45;text text">-->
<!--                        <h1 class="display-1">Admin bo'lish uchun  ro'yxatdan o'ting</h1>-->
<!--                        <h5-->
<!--                            class="text-center"-->
<!--                        >kiritgan parolingizni yodda salqashni unutmang</h5>-->
<!--                      </v-card-text>-->
<!--                      </v-col>-->
                    <v-col cols="12" md="12" style="background-color: #635F68">
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
                        <h5 class="text-center display-1 white--text">Ma'lumotlaringizni to'g'ri kiriting</h5>
                        <v-form ref="submit">
                          <v-text-field
                              label="Login"
                              v-model="data.username"
                              prepend-icon="person"
                              type="text"
                              color="#6F0DFF"
                          />
                          <v-text-field
                              label="Parol"
                              v-model="data.password"
                              prepend-icon="lock"
                              type="password"
                              color="#6F0DFF"
                          />
                          <v-text-field
                              label="Telefon raqam"
                              v-mask="'+998 ## ### ## ##'"
                              v-model="data.phoneNumber"
                              prepend-icon="lock"
                              type="text"
                              color="#6F0DFF"
                          />
                          <div class="text-center" style="height: 40px">
                            <v-btn @click.prevent="submit"  :style="{ width: '100%' }" color="#6F0DFF" dark>
                              Ro'yhatdan o'tish
                            </v-btn>
                          </div>
                          <h5 class="text-center white--text">
                            Iltimos profilngiz bo'lsa ro'yhatdan o'tmang! Profilingiz bo'lsa pastdagi tugmani bosing!
                          </h5>
                          <div class="text-center"  style="height: 30px">
                            <v-btn to="/login" color="#6F0DFF" dark>kirish</v-btn>
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
         phoneNumber: '+998'
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
      axios.post('auth/register',this.data).then(response=>{
        console.log(response.data)
        sessionStorage.setItem('token',response.data);
        this.$store.commit('setStatus',false)
        this.$store.commit('set',false)
        this.$router.push('/market');
        window.location.reload()
      });
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
