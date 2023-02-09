<template>
    <div >
        <div class="contbox">
            <div class="cont">
                <h3>答疑交流</h3>
            </div>
            <!-- <div>
                <el-popover placement="bottom-end" width="700" trigger="manual" v-model="search">
                    <div>
                        <el-input placeholder="Search for message or users..." suffix-icon="el-icon-search" v-model="sec" @keyup.enter.native="searchTextNow"></el-input>                        
                    </div>
                    <i slot="reference" class="el-icon-search" @click="search = !search" style="margin-right: 50px; font-size: 1.2em;"></i>
                </el-popover>
            </div> -->
            
        </div>
        <!-- <div>
            <el-drawer title="查找记录" :visible.sync="drawer" direction="btt" :with-header="false" :size="'60%'">
                <h5>查找记录</h5>
                <ul style="background-color: pink">
                    <li v-for="(chat, index) in searchChatText" :key="index">
                        <h5>{{chat.uname}}</h5>
                        <p>{{chat.msg}}</p>
                    </li>
                </ul>
            </el-drawer>
        </div> -->
    </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
    data() {
        return {
            search: false,
            sec: '',
            drawer: false,
        }
    },
    computed: {
        ...mapState(['searchChatText']),
        
    },
    methods: {
        searchTextNow() {
            console.log(this.sec)
            this.$store.dispatch('showSearch',this.sec)
            this.drawer = true
        },
        // refresh() {
        //     const roomid = {
        //         room: this.groupInfo.groupId
        //     }
        //     this.$socket.emit("online_cnt", roomid)
        // }
    }
    // sockets:{ //在此接收又服务器发送过来的数据 ps：注意此处的方法名要与服务器的发送的事件保持一致才能接收到
    //     clientNum(Num){
    //         console.log(typeof Num)
            
    //     }
    // }
}
</script>

<style scoped>
.el-image{
    width: 100px;
    height: 100px;
    border-radius: 50%;
}
.cont{
    height: 20%;
    display: flex;
    flex-direction: row;
    align-items: center;
}
.contbox{
    /* height: 30%; */
    display: flex;
    /* flex-direction: row; */
    justify-content: space-between;
    align-items: center;
}
el-popover{
    background-color: rgba(255, 255, 255) !important;
    box-shadow: 0 0 rgba(255, 255, 255) !important;
}
h5{
    margin: 10px !important;
    margin-left: 0 !important;
}
.types{
    font-size: 12px;
    color: #606266;
}
i{
    color: #303133;
    margin: 15px;
    cursor: pointer;
}
i:hover{
    color: #409EFF;
}
</style>