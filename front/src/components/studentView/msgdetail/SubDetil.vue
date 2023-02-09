<template>
    <div>
        <div>
          <span class="backbtn" @click="$router.back()"><i class="fa fa-long-arrow-left"></i></span>
          <span>作业号：{{ preRow.workId }}</span>
        </div>
        <el-divider></el-divider>
        <div>
            <p>
                提交状况 
                 <span>
                    <el-tag type="success" v-if="preRow.subState==1">已提交</el-tag>
                    <el-tag type="danger" v-else>未提交</el-tag>
                 </span>
            </p>
            <p>
                作业成绩
                 <span>
                    <el-tag v-if="preRow.checkState==1">{{preRow.point}} 分</el-tag>
                    <el-tag type="warning" v-else>未批改</el-tag>
                 </span>
            </p>
            <div class="descbox" v-show="preRow.subState==1">
                <div>作业内容</div> 
                <el-card shadow="hover" style="width: 500px; margin-left: 5px;">
                    <div class="qlbox">
                        <div class="ql-container ql-snow">
                            <div class="ql-editor" v-html="workCon">
                            </div>
                        </div>
                    </div>
                </el-card>
            </div>
            <div class="descbox" v-show="preRow.checkState==1">
                <div>作业评语</div> 
                <el-card shadow="hover" style="width: 500px; margin-left: 5px;">{{preRow.comment}}</el-card>
            </div>
            <div class="subbtn">
                <el-button :disabled="preRow.subState==1" type="primary" @click="subNow" plain>提交作业</el-button>
                <el-button :disabled="preRow.subState==0" type="info" @click="subNow" plain>修改作业</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import {mapState} from 'vuex'

export default {
    data() {
        return {
            preRow: {},
            workCon: ''
        }
    },
    computed: {
        ...mapState(['userInfo'])
    },
    methods: {
        subNow() {
            this.$router.push({
                name: 'PushPaper',
                query:{ rowInfo: this.preRow }
            })
        },
        async getWorkContent() {
            let asc = {
                studentId: this.userInfo.userId,
                workId: this.preRow.workId
            }
            let {data: res} = await this.$http.post('/teacher/getSubContext', asc)
            if(res) {
                this.workCon = res.subContext
            }
        }
    },
    created() {
        this.preRow = JSON.parse(this.$route.query.rowInfo)
        this.getWorkContent()
    }
}
</script>

<style scoped>
.backbtn {
    font-size: 1.2em;
    margin-right: 10px;
    margin-left: 10px;
    cursor: pointer;
    width: 50px;
    color: #409EFF;
}
.descbox {
    display: flex;
    flex: 1 ;
    margin: 5px 0 5px 0;
}
.subbtn {
    margin: 40px 0 10px 0;
}
.qlbox {
    /* height: 500px; */
    width: 100%;
    /* min-width: 500px; */
    /* min-width: 800px; */
    /* margin-left: 5px; */
    /* display: flex; */
    /* justify-content: center; */
}
.ql-container {
    width: 100%;
}
</style>