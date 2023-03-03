import '@fortawesome/fontawesome-free/css/all.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import axios from "axios";

axios.defaults.baseURL= 'http://192.168.202.23:8088/api/';

// let refresh = false;
//
// axios.interceptors.response.use(resp=>resp,async error => {
//     if (error.response.status === 401 && !refresh){
//         refresh = true;
//         const response = await axios.post('refresh',{},{withCredentials:true});
//         if (response.status === 200){
//             axios.defaults.headers.common['Authorization'] = `Bearer ${response.data}`;
//             return axios(error.config);
//         }
//     }
//     refresh = false;
//     // const token = JSON.parse( <string>sessionStorage.getItem('token') );
//     // if( token ){
//     //     axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
//     // }
//     return error;
// });

Vue.use(Vuetify);

export default new Vuetify({
    icons:{
        iconfont:'md' || 'fa',
    },
    theme: {
        themes: {
            dark: {
                background: '#000000',
            }
        }
    }
});
