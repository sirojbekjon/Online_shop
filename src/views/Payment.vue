<template >
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row justify="center">
        <v-col xs="12" sm="8" md="6" lg="4">
          <v-card class="elevation-24 mr-lg-16">
            <v-window>
              <v-window-item>
                <v-row class="fill-height">
                  <v-col cols="12" md="12" :class="{ 'darkCard': darkMode }">
                    <v-col v-for="(user2,i) in user1" :key="i">
                    <v-card>
                      <h4 style="margin-left: 20px;font-family:'Arial'">ID raqamingiz: <span style="font-size: 20px; color: #9b9696">{{user2.id ? user2.id : 'N/A'}}</span></h4>
                      <h4 style="margin-left: 20px;font-family:'Arial'">Ismingiz: <strong style="font-size: 20px;color: #6F0DFF">{{user2.username ? user2.username.toUpperCase() : 'N/A'}}</strong></h4>

                      <v-card-text>
                        <h4 class="card_travel">
                          <img
                              alt
                              :src="require('@/assets/mony.png')"
                              width="30px"
                              height="auto"
                          >
                              Hisobingizda:
                            <strong  style="font-size: 20px; color: #9b9696">
                              {{ user2.salary ? user2.salary + ' so\'m' : '0 so\'m' }}
                            </strong>
                        </h4>
                      </v-card-text>
                      <v-card-text style="margin-top: -25px">
                        <h4>
                          <img
                              alt
                              :src="require('@/assets/coin.png')"
                              width="30px"
                              height="auto"
                          >

                        To'lab berildi: <strong style="font-size: 20px;color: #9b9696">{{user2.paid ? user2.paid +' so\'m' : '0 so\'m'}}</strong>
                        </h4>
                      </v-card-text>
                    </v-card>
                    </v-col>
                    <v-card-text class="mb-1">
                      <h2 class="text-center white--text">To'lovga so'rov yuborish</h2>
                      <v-form ref="submit">
                        <v-text-field
                            label="Karta raqamingizni kiriting"
                            v-model="data.cardNumber"
                            v-mask="'#### #### #### ####'"
                            prepend-icon="list"
                            type="text"
                            color="#6F0DFF"
                        />
                        <v-text-field
                            label="Summani kiriting"
                            v-model="data.partSalary"
                            prepend-icon="money"
                            type="number"
                            color="#6F0DFF"
                        />
                        <div class="text-center">
                          <h5 class="white--text mb-4">Iltimos tasdiqlashdan oldin ma'lumotlarizni diqqat bilan ko'rib chiqing</h5>
                          <v-btn @click.prevent="submit" color="#6F0DFF" dark>Tasdiqlash</v-btn>
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
      user1:[],
      data: {
        partSalary: '',
        cardNumber:''
      },
      token: 'Bearer ' + sessionStorage.getItem('token'),

    }
  },
  props: {
    source: String
  },

  mounted: async function () {
    try {
    axios.get("user",{headers:{'authorization':this.token}}).then(response => {
      this.user1.push(response.data)
    })}catch (error){
      console.log(error)
    }


  },
  methods:{
    submit(){
    axios.post('user/paid',this.data,{headers:{'authorization': this.token}});
    this.data=''
    },
  },
};
</script>
<style>

.darkCard {
  background-color: #222;
  color: #000;
}
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
