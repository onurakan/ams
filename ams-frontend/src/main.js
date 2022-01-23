import { createApp } from 'vue'
import App from './App.vue'
import './assets/reset.css';
import 'bootstrap'


const app = createApp(App)

app.config.globalProperties.auth = {
    auth: {
            username: 'user',
            password: 'password'
        }
    }
    
app.config.globalProperties.ams_backend_url=process.env.VUE_APP_AMS_BACKEND_URL
app.config.globalProperties.page_size=10

console.info("Vue is using AMS_BACKEND_URL=" + app.config.globalProperties.ams_backend_url);

app.mount('#app')
