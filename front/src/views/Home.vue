<template>
    <el-container class="home_con">
        <!-- 侧边部分 -->
        <el-aside width="20%" style="background: #fff; box-shadow: 2px 0px 8px #C0C4CC;">
            <div class="boxinner" >
                <div class="headerbox">
                    <!-- <div class="classdrown">
                        <el-dropdown trigger="click">
                        <span class="el-dropdown-link">
                            {{teaClassInfo.defaultName}}<i class="el-icon-arrow-down el-icon--right"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown" style="width: 150px;">
                            <el-dropdown-item v-for="(aclass, index) in teaClassInfo.classList" :key="index" @click.native="changeclass(aclass)">{{aclass.subjectName}}</el-dropdown-item> -->
                            <!-- <el-dropdown-item>三年级2班</el-dropdown-item>
                            <el-dropdown-item>四年级3班</el-dropdown-item> -->
                            <!-- <el-divider></el-divider>
                            <div class="creatbtn" @click="newClass">创建班级</div>
                        </el-dropdown-menu>
                        </el-dropdown>
                    </div> -->
                    <!-- <div class="outbtn"  @click="dialogTableVisible = true">
                        <i class="fa fa-user-plus iconout"></i>
                        <span>添加成员</span>
                    </div>
                    <div class="outbtn" @click="openSet">
                        <i class="el-icon-s-tools iconout"></i>
                        <span>班级设置</span>
                    </div> -->
                    <el-dialog :visible.sync="dialogTableVisible" center :close-on-click-modal="false" :destroy-on-close="true">
                        <template slot="title">
                            <div class="titlebox">添加成员</div>
                        </template>
                        <AddNew/>
                    </el-dialog>
                </div>
                <!-- 第1部分 -->
                <div style="padding: 0;" v-if="defpages==1" class="animated bounceInLeft">
                    <!-- 导航栏 -->
                    <el-menu
                    default-active="/home/courseplan"
                    class="el-menu-vertical-demo"
                    router>
                        <el-menu-item index="/home/courseplan">
                            <template slot="title">
                                <i class="el-icon-date"></i>
                                <span>授课计划</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="/home/homework">
                            <template slot="title">
                                <i class="el-icon-menu"></i>
                                <span slot="title">作业管理</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="/home/paper/list">
                            <template slot="title">
                                <i class="el-icon-document"></i>
                                <span slot="title">作业批改</span>
                            </template>
                        </el-menu-item>
                    </el-menu>
                </div>
                <!-- 第2部分 -->
                <div style="padding: 0;" v-else class="animated bounceInRight">
                    <!-- 导航栏 -->
                    <el-menu
                    default-active="/home/worksch"
                    class="el-menu-vertical-demo"
                    router>
                        <el-menu-item index="/home/worksch">
                            <template slot="title">
                                <i class="el-icon-s-cooperation"></i>
                                <span>课程安排</span>
                            </template>
                        </el-menu-item>
                        <el-menu-item index="/home/chatti">
                            <template slot="title">
                                <i class="el-icon-edit-outline"></i>
                                <span slot="title">答疑交流</span>
                            </template>
                        </el-menu-item>
                    </el-menu>
                </div>
                <!-- <div class="footbox">
                    <div class="footerbtn">
                        <div :class="{innerbtn: true, innerbtnhov: defpages!=1, innerbtnact:defpages==1}" @click="changepages(1)"><i class="el-icon-s-management iconbtn"></i>班级管理</div>
                        <div style="font-size: 28px; color: #909399;">|</div>
                        <div :class="{innerbtn: true, innerbtnhov: defpages!=2, innerbtnact:defpages==2}" @click="changepages(2)"><i class="el-icon-s-flag iconbtn"></i>计划管理</div>
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
import AddNew from '../components/teacherView/AddNew'
import Headers from '../components/Headers'
import { mapState } from 'vuex'

export default {
    components: {
        AddNew,
        Headers
    },
    data() {
        return {
            // classmsg: [
            //     '六年级1班语文',
            //     '三年级2班数学',
            //     '四年级3班英语'
            // ],
            // thisclass: '6年级1班语文',
            defpages: 1,
            dialogTableVisible: false
        }
    },
    computed: {
        ...mapState(['teaClassInfo'])
    },
    methods: {
        async changeclass(aclass) {
            this.teaClassInfo.defaultInfo = aclass
            this.teaClassInfo.defaultName = aclass.subjectName
            // this.thisclass = `${aclass.grade}年级${aclass.class}班${aclass.subjectName}`
            let {data: res} = await this.$http.post('/teacher/getWorksOfSubject', {subjectId: aclass.subjectId})//切换
            console.log(res)
            this.$store.dispatch('pushChangeClass', res)
        },
        changepages(data) {
            this.defpages = data
        },
        openSet() {
            this.$store.dispatch('getClassmates')
            this.$router.push('/classsetting');
        },
        newClass() {
            this.$store.dispatch('clearfile')//清楚默认数组
            this.$router.push('/initclass');
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
    grid-template-rows: 27% 58% 15%;
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
.titlebox {
    font-size: 1.5em;
}
</style>