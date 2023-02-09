import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import VueSocketIo from 'vue-socket.io'
import SocketIO from "socket.io-client"
import axios from 'axios'
import 'vue-event-calendar/dist/style.css' //1.1.10之后的版本，css被放在了单独的文件中，方便替换
import vueEventCalendar from 'vue-event-calendar'
// import vueEventCalendar from '../../dist/index.js'

// 导入全局样式
import './assets/global.css'
import 'font-awesome/scss/font-awesome.scss'

// Vue.config.productionTip = false
Vue.config.productionTip = true

axios.defaults.baseURL = 'http://localhost:8081' //测试用
    // axios.defaults.baseURL = 'http://39.97.119.247:81'//测试2
    // axios.defaults.baseURL = 'http://39.100.133.1:8081' //正式

Vue.prototype.$http = axios

Vue.use(vueEventCalendar, {
    locale: 'zh'
})

Vue.use(new VueSocketIo({
    debug: false,
    // debug: true,
    connection: SocketIO('http://www.jieblue.xyz'), //测试接口
    // connection: SocketIO('http://39.97.119.247:8081'),
    vuex: {
        store,
        mutationPrefix: "SOCKET_",
        actionPrefix: "SOCKET_"

    }
}))

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')