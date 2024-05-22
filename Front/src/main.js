import { createApp } from 'vue';
import { useKakao } from 'vue3-kakao-maps/@utils';
import App from './App.vue';
import { createPinia } from 'pinia';
import router from './router';
import { serviceKey } from './assets/key/key.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';
import { IonicVue } from '@ionic/vue';

const app = createApp(App);
useKakao(serviceKey);
app.use(createPinia());
app.use(router);
app.use(IonicVue);

app.mount('#app');
