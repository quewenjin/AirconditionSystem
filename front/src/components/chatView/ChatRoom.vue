<template>
    <el-container>
        <el-main class="maincontent" id="dialogue_box">
            <vuescroll :ops="ops" ref="vs" style="width:100%;height:100%" >
                <!-- <div id="dialogue_box"> -->
            <!-- <div style="padding: 15px">
                <div v-for="(chat, index) in chatText" :key="index" class="animated fadeInUp">
                    <div v-if="chat.msgType == 'respond'" class="respondMsg">
                        <div class="sysTime">{{chat.date}}</div>
                        <div class="respond">{{chat.uname}}</div>
                    </div>
                    <div v-else-if="chat.msgType == 'msgres'" class="msgresMsg">
                        <div v-if="chat.uname==userInfo.userName&&chat.cid===userInfo.userId" class="self">
                            <el-card class="box-card" shadow="hover" style="background-color: #409EFF; color: #FFFFFF">
                                <div>{{chat.msg}}</div>
                                <div class="msgdate">{{chat.date}} &nbsp;{{chat.uname}}</div>
                            </el-card>
                            <el-avatar class="msgName" :src="imgUrl" :size="50">{{chat.uname}}</el-avatar>
                        </div>
                        <div v-else class="others">
                                <el-avatar class="msgName" :src="chat.avatar" :size="50">{{chat.uname}}</el-avatar>
                            <el-card class="box-card" shadow="hover">
                                <div>{{chat.msg}}</div>
                                <div class="msgdate">{{chat.date}} &nbsp;{{chat.uname}}</div>
                            </el-card>
                        </div>
                    </div>
                </div>
            </div> -->
            <!-- </div> -->
            </vuescroll>
        </el-main>
        <el-footer>
            <!-- <chatFooter/> -->
            <!-- <div class="cont">
                <el-input placeholder="Send your message..." style="width: 80%;"  v-model="msg" @keyup.enter.native="send"></el-input>
                <div class="emojiwin"> -->
                    <!-- <el-popover placement="top" trigger="click" visible-arrow="true" >
                        <VEmojiPicker @select="selectEmoji" style="height: 300px; width: 225px"/>
                        <i slot="reference" class="fa fa-smile-o "></i>
                    </el-popover> -->
                <!-- </div>
                <el-button class="thisBtn" type="primary" icon="el-icon-s-promotion" circle @click.native="send"></el-button>
            </div> -->
        </el-footer>
    </el-container>
</template>

<script>
import { mapState } from 'vuex'
// import VEmojiPicker from 'v-emoji-picker'
import vuescroll from 'vuescroll'

const moment = require("moment")
export default {
    components: {
        // VEmojiPicker,
        vuescroll
    },
    data() {
        return {
            isShow: false,
            input: '',
            msg: '',
            imgUrl: '',
            ops: {
                vuescroll: {},
                scrollPanel: {},
                rail: {
                    keepShow: true
                },
                bar: {
                    hoverStyle: true,
                    onlyShowBarOnScroll: true, //?????????????????????????????????????????????
                    background: "#909399",//???????????????
                    opacity: 0.5,//??????????????????
                    "overflow-x": "hidden"
                }
            },
        }
    },
    watch: {
        $route(to, from) {
            // console.log(to);
            // console.log(from);
            if(to.path === '/home/chatroom') { //??????????????????
                this.isShow = false
            } else {
                this.isShow = true
            }
        },
        '$store.state.isloading'() {
            if(this.$store.state.isloading === true) {
                this.$loading({
                    fullscreen: true,
                    lock: true,
                    text: '?????????????????????????????????????????????????????????~ http://localhost/#/home/enter'
                })
            }
        },
        '$store.state.groupMembers'() {
            
            if(this.$store.state.groupMembers === 0) {
                this.$message({
                        type: 'error',
                        message: '??????????????????!'
                    });
                this.$loading({
                    fullscreen: true,
                    lock: true,
                    text: '????????????????????????????????????????????????~ http://localhost/#/home/enter'
                })
            }
            
        },
    },
    methods: {
        // ...mapState(['watchNew']),
        openmsg() {
            // const root = window.sessionStorage.getItem('ROOMROOT')
            if(window.sessionStorage.getItem('FIRSTCRT')=='true') {
                    this.onLine()
                    this.$notify({
                        title: '?????????????????????',
                        message: '??????????????????????????????',
                        type: 'success'
                    });
                window.sessionStorage.setItem('FIRSTCRT', 'false')
            }
            
        },
        getImeUrl() {
            this.imgUrl = this.userInfo.userAvatar
        },
        // selectEmoji(emoji) {
        //     console.log(emoji)
        //     this.msg += emoji.data
        // },
        onLine() {
            let sig = { name: this.userInfo.userName }
            this.$socket.emit("join", sig);
        },
        send() { 
            let transdata = {
                msg: this.msg,
                uname: this.userInfo.userName,
                date: moment().format("HH:mm:ss"),
                cid: this.userInfo.userId,
                avatar: this.userInfo.userAvatar
            }
            // console.log(transdata)
            this.$socket.emit("chat",transdata);
            this.msg = ''
        },
        roolDown() {
            this.$nextTick(function(){
                this.$refs['vs'].scrollBy({
                    dy: '100%'
                },2000)
            },1000)
        },
    },
    beforeCreate(){
        const urls = this.$route.path
        console.log(urls)
    },
    created() {
        // const roomid = {
        //     room: this.groupInfo.groupId
        // }
        // this.$socket.emit("online_cnt", roomid)//????????????
        // window.addEventListener('beforeunload', e => this.beforeunloadFn(e))
        // this.onLine()
        // window.sessionStorage.setItem('FIRSTCRT', 'true')
        this.openmsg()
    },
    computed: {
        ...mapState(['chatText','userInfo'])
    },
    mounted() {
        this.getImeUrl()
    },
    destroyed() {
        // window.removeEventListener('scroll', this.handleScroll); //?????????????????????????????????????????????
    },
    sockets:{ //????????????????????????????????????????????? ps???????????????????????????????????????????????????????????????????????????????????????
        connect() {      //???ws:127.0.0.1:8000???????????????
            console.log('????????????');
        },
    },
    updated() {
        this.roolDown()
    }
}
</script>

<style scoped>
.maincontent{
    height: 350px;
}
.cont{
    display: flex;
    flex: 1;
    margin-left: 15px;
    margin-right: 15px;
    margin-bottom: 15px;
    margin-top: 15px;
}
.thisBtn{
    margin: 5px;
    margin-left: 10px;
    margin-top: 0;
}
.respondMsg {
    display: flex;
    /* justify-content: center; */
    flex-direction: column;
    /* background-color: aqua; */
}
.sysTime {
    align-self: center;
    font-size: 12px;
    margin-top: 10px;
}
.respond {
    align-self: center;
    font-size: 12px;
    color: #FFFFFF;
    background-color: #909399;
    padding: 6px;
    border-radius: 11px;
}
.box-card{
    width: 380px;
    background-color: #EBEEF5;
    margin: 10px;
    border-radius: 10px;
    word-break: break-all;
}
.msgdate{
    font-size: 10px;
    margin-top: 10px;
}
.self {
    display: flex;
    flex: 1;
    justify-content: flex-end;
}
.others {
    display: flex;
    flex: 1;
    justify-content: flex-start;
}
.msgName {
    align-self: center;
}
.emojiwin {
    margin: 5px;
    margin-right: 0;
    font-size: 23px;
    color: #909399;
}
.cent {
    display: flex;
    justify-content: center;
}


.__bar-is-vertical {
  right: -1px !important;
}
.__bar-is-horizontal {
  display: none !important;
}
</style>