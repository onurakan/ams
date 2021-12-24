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
    
app.config.globalProperties.ams_backend_url='http://localhost:8080'

app.mount('#app')
