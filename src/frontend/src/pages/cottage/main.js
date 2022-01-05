import { createApp } from 'vue'
import App from './cottage.vue'
import VueGoogleMaps from '@fawmi/vue-google-maps'

const app = createApp(App);
app.use(VueGoogleMaps, {
	load: {
		key: '',
	},
}).mount('#app')