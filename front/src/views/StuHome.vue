<template>
    <el-container class="home_con">
        <!-- 侧边部分 -->
        <el-aside width="20%" style="background: #fff; box-shadow: 2px 0px 8px #C0C4CC;">
            <div class="boxinner" >
                <div class="headerbox">
                    <!-- <div class="classdrown">
                        <el-dropdown trigger="click">
                        <span class="el-dropdown-link">
                            {{stuClassInfo.defaultName}}<i class="el-icon-arrow-down el-icon--right"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown" style="width: 150px;">
                            <el-dropdown-item v-for="(aclass, index) in stuClassInfo.classList" :key="index" @click.native="changeclass(aclass)">{{aclass.subjectName}}</el-dropdown-item> -->
                            <!-- <el-dropdown-item>三年级2班</el-dropdown-item>
                            <el-dropdown-item>四年级3班</el-dropdown-item> -->
                            <!-- <el-divider></el-divider>
                            <div class="creatbtn" @click="dialogTableVisible=true">添加班级</div>
                        </el-dropdown-menu>
                        </el-dropdown>
                    </div> -->
                    <!-- <div class="outbtn" @click="openSet">
                        <i class="el-icon-user-solid iconout"></i>
                        <span>班级成员</span>
                    </div> -->
                    <!-- <el-dialog :visible.sync="dialogTableVisible" center :close-on-click-modal="false" :destroy-on-close="true" style="width: 80%;">
                        <template slot="title">
                            <div class="titlebox">加入课程</div>
                        </template> -->
                        <!-- <AddNew/> -->
                        <!-- <div class="joinclass">
                            <el-form :model="numberValidateForm" ref="numberValidateForm" label-width="100px" class="demo-ruleForm">
                                <el-form-item
                                    label="课程号"
                                    prop="subId"
                                    :rules="[
                                    { required: true, message: '此项不能为空'},
                                    { type: 'number', message: '课程号必须为数字值'}
                                    ]"
                                >
                                    <el-input style="width: 80%" type="age" v-model.number="numberValidateForm.subId" autocomplete="off" clearable></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="submitForm('numberValidateForm')">提交</el-button>
                                    <el-button @click="resetForm('numberValidateForm')">重置</el-button>
                                </el-form-item>
                            </el-form>
                        </div>
                    </el-dialog> -->
                </div>
                <!-- 第1部分 -->
                <div style="padding: 0;" v-if="defpages==1" class="animated bounceInLeft">
                    <!-- 导航栏 -->
                    <el-menu
                    default-active="/stuhome/msgdetil"
                    class="el-menu-vertical-demo"
                    router>
                        <el-menu-item index="/stuhome/msgdetil">
                            <template slot="title">
                                <i class="el-icon-news"></i>
                                <span>任务通知</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="/stuhome/subview/work">
                            <template slot="title">
                                <i class="el-icon-document"></i>
                                <span slot="title">作业提交</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="/stuhome/chatti">
                            <template slot="title">
                                <i class="el-icon-chat-line-round"></i>
                                <span slot="title">在线交流</span>
                            </template>
                        </el-menu-item>
                    </el-menu>
                </div>
                <!-- 第2部分 -->
                <!-- <div style="padding: 0; padding-left: 25px;" v-else class="animated bounceInRight">
                    <h2>信息</h2>
                    <div class="tecInfoBox">老师: {{stuClassInfo.defaultInfo.teacherName}}</div>
                    <div class="taskNumBox">需提交的作业数: {{stuClassInfo.defaultInfo.shouldSubmit}}</div>
                    
                </div>
                <div class="footbox">
                    <div class="footerbtn">
                        <div :class="{innerbtn: true, innerbtnhov: defpages!=1, innerbtnact:defpages==1}" @click="changepages(1)"><i class="el-icon-s-management iconbtn"></i>学科交流</div>
                        <div style="font-size: 28px; color: #909399;">|</div>
                        <div :class="{innerbtn: true, innerbtnhov: defpages!=2, innerbtnact:defpages==2}" @click="changepages(2)"><i class="el-icon-s-flag iconbtn"></i>信息提示</div>
                    </div>                    
                </div> -->
            </div>
        </el-aside>
        <el-main class="cons">
            <el-container>
                <el-header style="background: #E4E7ED; height:80px;">
                    <Headers/>
                </el-header>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-main>
    </el-container>
</template>

<script>
import Headers from '../components/Headers'
import { mapState } from 'vuex'

export default {
    components: {
        Headers
    },
    data() {
        return {
            classmsg: [
                '语文',
                '数学',
                '英语'
            ],
            thisclass: '语文',
            defpages: 1,
            teacherName: '张伟',
            taskNum: 4,
            dialogTableVisible: false,
            numberValidateForm: {
                subId: ''
            }
        }
    },
    computed: {
        ...mapState(['stuClassInfo', 'userInfo'])
    },
    methods: {
        async changeclass(aclass) {
            this.stuClassInfo.defaultName = aclass.subjectName
            this.stuClassInfo.defaultInfo = aclass
            let {data: res} = await this.$http.post('/student/getWorksOfSubject', {studentId: this.userInfo.userId,subjectId: aclass.subjectId})//切换
            // console.log(res)
            this.$store.dispatch('pushChangeClass', res)
        },
        changepages(data) { 
            this.defpages = data
        },
        openSet() {
            this.$store.dispatch('getClassmates')
            this.$router.push('/classsetting')
        },
        submitForm(formName) {
            this.$refs[formName].validate(async(valid) => {
            if (valid) {
                let asc = {
                    studentId: this.userInfo.userId,
                    subjectId: this.numberValidateForm.subId
                }
                const {data: res} = await this.$http.post('/student/joinClass', asc)
                // console.log(res)
                if(res.status=='success') {
                    await this.$store.dispatch('pushNewclass')
                    this.dialogTableVisible = false
                    this.$message({
                        message: '加入成功！',
                        type: 'success'
                    })
                }else {
                    this.$message({
                        message: '请求失败！',
                        type: 'error'
                    })
                    return false;
                }
            } else {
                this.$message({
                    message: '请求失败！',
                    type: 'error'
                })
                return false;
            }
            });
        },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
}
</script>

<style scoped>
.home_con {
    /* background: rgba(94,165,155, 1); */
    height: 100%;
}
.cons {
    height: 100%;
    padding: 0;
    margin: 0;
}
.boxinner {
    height: 100%;
    display: grid;
    grid-template-rows: 20% 65% 15%;
    /* align-content: space-around;     */
}
.footbox {
    height: 100%;
    box-shadow:0px -1px 2px #606266;
    margin-top: 10px;
    align-self: end;
}
.footerbtn {
    display: flex;
    justify-content: space-around;
    align-content: center;
    padding: 20px;
}
.innerbtn {
    padding: 10px;
    border-radius: 10px;
    /* background: rgba(78, 167, 255, 0.267); */
    border: solid 1px rgba(78, 167, 255, 0.267);
    cursor: pointer;
}
.innerbtnhov:hover {
    background-color: rgba(185, 218, 252, 0.322);
}
.innerbtnact {
    background: rgba(78, 167, 255, 0.267);
}
.iconbtn {
    color: #409EFF;
    margin-right: 5px;
    font-size: 1.2em;
}

.headerbox {
    height: 100%;
}
.el-dropdown-link {
    font-size: 1.5em;
    color: black;
    cursor: pointer;
   
}
.classdrown {
    text-align: center;
    margin: 20px;
}
.iconout {
    margin-right: 5px;
    color: #F5AB3C;
}
.outbtn {
    margin: 25px;
    cursor: pointer;
}
.creatbtn {
    color: #409EFF;
    text-align: center;
    cursor: pointer;
    padding: 10px;
}

.tecInfoBox {
    font-size: 20px;
    color: #409EFF;
    margin: 10px 0 10px 0;
}
.taskNumBox {
    font-size: 15px;
    color: #F56C6C;
    margin: 10px 0 10px 0;
}
.joinclass {
    display: flex;
    justify-content: center;
}
</style>