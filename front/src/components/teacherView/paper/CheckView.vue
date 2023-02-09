<template>
    <div>
        <div>
          <span class="backbtn" @click="$router.back()"><i class="fa fa-long-arrow-left"></i></span>
          <span>学号：{{ $route.query.rowInfo.stuNum }}</span>
          <el-divider direction="vertical"></el-divider>
            <span>姓名：{{$route.query.rowInfo.name}}</span>
        </div>
        <el-divider></el-divider>
        <p>
            标题：<span>{{$route.query.homeworkCon.workTitle}}</span>     
        </p>
        <p>
            内容：<span>{{$route.query.homeworkCon.workContext}}</span>
        </p>
        <p>
            <el-image
                v-for="(img, index) in $route.query.homeworkCon.pictures"
                :key="index"
                style="width: 100px; height: 100px; margin-right: 10px;"
                :src="img" 
                :preview-src-list="$route.query.homeworkCon.pictures">
            </el-image>
        </p>
        <el-divider></el-divider>
        <p>
            提交内容：
        </p>
        <!-- 显示框 -->
        <div class="qlbox">
            <!-- <div v-html="content"></div> -->
            <div class="ql-container ql-snow">
                <div class="ql-editor" v-html="content">
                <!-- <div class="ql-editor" v-html="$route.query.rowInfo.subContext"> -->
                </div>
            </div>
        </div>
        <el-divider></el-divider>
        <!-- 评分框 -->
        <div>
            <p>
                评分
            </p>
            <el-form :model="homeworkResForm" ref="homeworkResForm" label-width="100px" class="demo-ruleForm">
                <el-form-item
                    label="得分"
                    prop="point"
                    :rules="[
                    { required: true, message: '评分不能为空'},
                    { type: 'number', message: '分数必须为数字'}
                    ]"
                >
                    <el-input type="age" v-model.number="homeworkResForm.point" autocomplete="off" style="width: 30%" placeholder="请输入得分..."></el-input>
                </el-form-item>
                <el-form-item label="评语" prop="desc" >
                    <el-input type="textarea" v-model="homeworkResForm.desc" placeholder="请输入评语..." style="width: 80%"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('homeworkResForm')">提交</el-button>
                    <el-button @click="resetForm()">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { mapState } from 'vuex'

export default {
    computed: {
        // ...mapState(['content'])
    },
    data() {
        return {
            content: '',
            homeworkResForm: {
                point: '',
                desc: ''
            }
        }
    },
    methods: {
        submitForm(formName) {
        this.$refs[formName].validate( async (valid) => {
            if (valid) {
                alert('submit!');
                let asc = {
                    studentId: this.$route.query.rowInfo.stuNum,
                    workId: this.$route.query.homeworkCon.workId,
                    score: this.homeworkResForm.point,
                    comment: this.homeworkResForm.desc
                }
                let {data: res} = await this.$http.post('/teacher/correctWork', asc)
                if(res.status=='success') {
                    this.$message({
                        message: '批改成功！',
                        type: 'success'
                    })
                this.$store.dispatch('detailList', this.$route.query.homeworkCon.workId)
                this.$router.back()
                }else {
                    this.$message({
                        message: '请求失败！',
                        type: 'error'
                    })
                    return false
                }
            } else {
                console.log('error submit!!');
                this.$message({
                    message: '请求失败！',
                    type: 'error'
                })
                return false;
            }
        });
      },
      resetForm() {
        this.$refs['homeworkResForm'].resetFields();
      },
      async getWorkContent() {
          let asc = {
              studentId: this.$route.query.rowInfo.stuNum,
              workId: this.$route.query.homeworkCon.workId
          }
          let {data: res} = await this.$http.post('/teacher/getSubContext', asc)
          if(res) {
              this.content = res.subContext
          }
      }
    },
    created() {
        this.getWorkContent()
        if(this.$route.query.rowInfo.checkState===1) {
            this.homeworkResForm.point = this.$route.query.rowInfo.point
            this.homeworkResForm.desc = this.$route.query.rowInfo.comment
        }
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
.qlbox {
    height: 500px;
    width: 90%;
    /* min-width: 800px; */
    margin-left: 5%;
    display: flex;
    justify-content: center;
}
.ql-container {
    width: 90%;
}
</style>