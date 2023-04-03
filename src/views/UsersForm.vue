<template>
  <v-form
      ref="form"
      lazy-validation
      v-model="user"
  >
    <v-container>
      <v-window>
        <v-window-item>
         <v-row justify="center">
            <v-col xs="12" sm="8" md="6" lg="4" style="background-color: #b9b9b9; margin:10px">
                <v-text-field
                    v-model="editedUser.username"
                    :counter="10"
                    outlined
                    clearable
                    :rules="emailRules"
                    label="Name"
                >
                </v-text-field>
                <v-text-field
                  outlined
                  clearable
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show1 ? 'text' : 'password'"
                  @click:append="show1 = !show1"
                  v-model="editedUser.password"
                  label="Password"
                  :rules="passwordRules"
                  persistent-hint
                  single-line
              ></v-text-field>
              <v-text-field
                  outlined
                  clearable
                  v-model="editedUser.phoneNumber"
                  label="Telefon raqam"
                  :rules="phoneRules"
                  required
              ></v-text-field>
              <v-btn
                  :disabled="!valid"
                  color="success"
                  class="mr-4"
                  @click="save"
              >
                Saqlash
              </v-btn>
              <v-btn
                  color="error"
                  class="mr-4"
                  @click="reset"
              >
                Reset Form
              </v-btn>
              <v-alert
                  class="mt-6 col-md-12"
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
        </v-col>
      </v-row>
        </v-window-item>
      </v-window>
    </v-container>
  </v-form>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    token: 'Bearer ' + sessionStorage.getItem('token'),
    show1:false,
    user:'',
    valid: true,
    name: '',
    alert: false,
    alert_color:'',
    message: '',
    phoneRules: [
      v => !!v || 'Telefon nomer yozish talab qilinadi',
      v => (v && v.length <= 14) || 'Telefon to\'ldirilgan bo\'lishi kerak',
    ],
    email: '',
    emailRules: [
      v => !!v || 'Username yozish talab qilinadi',
      v => /.+.+.+/.test(v) || 'Username to\'ldirilgan bo\'lishi kerak',
    ],
    passwordRules: [
      v => !!v || 'Parol yozish talab qilinadi',
      v => /.+.+.+/.test(v) || 'Password to\'ldirilgan bo\'lishi kerak',
    ],
    select: null,
    checkbox: false,

    editedUser: {
      username:'',
      password:'',
      phoneNumber:''
    },
  }),
  mounted: async function(){
    await axios.get(`userme`,{headers:{'Authorization':this.token}}).then(response =>{
      this.editedUser = Object.assign({}, response.data)
    })
  },

  methods: {
    save () {
      console.log(this.editedUser)
      if (this.editedUser.password ===null || this.editedUser.phoneNumber===null || this.editedUser.username===null){
        this.alert = true
        this.alert_color='red'
        this.message = 'Parol yoki login noto\'g\'ri'
        setTimeout(() => {
          this.alert = false;
        }, 5000);
      }else {
        this.$refs.form.validate()
        axios.put('edit', this.editedUser, {headers: {'Authorization': this.token}})
        this.$refs.form.reset()
        this.alert = true
        this.alert_color='green'
        this.message = 'Ma\'lumotlariz muvaffaqqiyatli o\'zgartirildi'
        setTimeout(() => {
          this.alert = false;
        }, 5000);
      }
    },
    reset () {
      this.$refs.form.reset()
    },
  },
}
</script>
