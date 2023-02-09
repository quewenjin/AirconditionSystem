<template>
    <el-container>
        <el-header height="150px" class="headerbox">
            <div class="backbtn" @click="leftback">
                <i class="fa fa-long-arrow-left" style="margin-right: 10px;"></i>
                <span>班级</span>
            </div>
            <div class="classname">创建班级</div>
        </el-header>
        <el-main class="mainbox">
            <h3>班级创建</h3>
            <div class="overline">
                <div class="inputBox">
                    <h5>班级名称</h5>
                    <el-input  placeholder="输入班级名称" v-model="setClassName" clearable style="width: 180px; margin-right: 80px; margin-left: 20px;"></el-input>
                </div>
                <el-button type="primary" @click="dialogTableVisible = true"><i class="fa fa-user-plus"></i></el-button>
                <el-button type="primary" @click="creatClass">创建</el-button>
                <div>
                    <el-input placeholder="输入关键字搜索" v-model="search" clearable style="width: 180px; margin-right: 80px; margin-left: 20px;"></el-input>
                </div>
            </div>
            <!-- 模态框 -->
            <el-dialog :visible.sync="dialogTableVisible" center :close-on-click-modal="false" :destroy-on-close="true">
                <template slot="title">
                    <div class="titlebox">添加成员</div>
                </template>
                <InitAdd/>
            </el-dialog>
            <el-divider></el-divider>
            <!-- 表格内容 -->
            <div style="margin-left: 50px;">
                <el-table
                :data="tempMemberList.filter(data => !search || data.studentName.toLowerCase().includes(search.toLowerCase())|| data.studentId.toLowerCase().includes(search.toLowerCase()))"
                style="width: 100%;"        
                @cell-mouse-enter="mouseEnter"
                @cell-mouse-leave="mouseLeave"
                @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="35"></el-table-column>
                    <el-table-column
                    label="学号"
                    prop="studentName">
                    </el-table-column>
                    <el-table-column
                    label="姓名"
                    prop="studentId">
                    </el-table-column>
                    <el-table-column>
                    <template slot="header">
                        <div clss="tbheaderbox">
                            <!-- <el-input v-model.trim="search" size="mini" placeholder="输入关键字搜索"  clearable/> -->
                            <!-- <span class="headerbtn" @click="addwork(scope.row)">发布作业</span> -->
                            <el-button type="text" class="headerbtn animated fadeIn" style="color: red;" @click="open">删除选中</el-button>
                        </div>
                    </template>
                    <template slot-scope="scope">
                        <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)" v-show="scope.row.isShow" style="height: 20px;padding: 5px;" class="animated fadeIn">编辑</el-button>
                        <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)" v-show="scope.row.isShow" style="height: 20px; padding: 5px;" class="animated fadeIn">删除</el-button>
                    </template>
                    </el-table-column>
                </el-table>
            </div>
            <div>
                <el-dialog :visible.sync="EditVisible" center :close-on-click-modal="false" :destroy-on-close="true">
                    <template slot="title">
                        <div class="titlebox">修改信息</div>
                    </template>
                    <div>
                        <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="姓名">
                            <el-input v-model="form.studentName"></el-input>
                        </el-form-item>
                        <el-form-item label="学号">
                            <el-input v-model="form.studentId"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button :loading="loading" type="primary" @click="editDone">确认修改</el-button>
                            <el-button @click="cancleSub">取消</el-button>
                        </el-form-item>
                    </el-form>
                    </div>
                </el-dialog>
            </div>
        </el-main>
    </el-container>
</template>

<script>
import InitAdd from './InitAdd'
import { mapState} from 'vuex'

export default {
    components: {
        InitAdd
    },
    data () {
        return {
            dialogTableVisible: false,
            delVisible: false,
            EditVisible: false,
            loading: false,
            multipleSelection: [],
            search: '',
            toShow: false,
            tableData: [
                {
                    studentName: 'hah',
                    studentId: '123',
                    isShow: false
                }
            ],
            rowIndex: 0,
            form: {
                studentName: '',
                studentId: '',
                isShow: false
            },
            setClassName: ''
        }
    },
    computed: {
        ...mapState([ 'userInfo', 'memberList', 'teaClassInfo', 'tempMemberList' ])
    },
    methods: {
        leftback() {
            this.$router.go(-1)
            this.setClassName = ''
        },
        mouseEnter(row) {
            row.isShow = true
        },
        mouseLeave(row) {
            row.isShow = false
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delThose() {
            console.log('gusgd')
            // 数组的批量删除，逆向循环
            for (let i = this.tempMemberList.length - 1; i >= 0; i--) {
                for (let j = this.multipleSelection.length - 1; j >= 0; j--) {
                    if (this.tempMemberList[i] === this.multipleSelection[j]) {
                    this.tempMemberList.splice(i, 1)
                    }
                }
            }
        },
        handleEdit(index, row) {
            console.log(index, row);
            this.form = JSON.parse(JSON.stringify(row))
            this.rowIndex = index
            this.EditVisible = true
        },
        handleDelete(index, row) {
            this.tempMemberList.splice(index, 1)
        },
        open() {
            this.$confirm('此操作将移除改学生, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                if(this.multipleSelection.length) {
                    this.delThose()
                    this.$message({
                        type: 'success',
                        message: '删除成功'
                    });
                }else {
                    this.$message({
                        type: 'info',
                        message: '您未选中任何成员'
                    });
                }                
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        },
        editDone() {
            this.loading = true
            if(this.form.studentName&&this.form.studentId) {
                this.tempMemberList[this.rowIndex] = JSON.parse(JSON.stringify(this.form))
                this.EditVisible = false
                this.$message({
                        message: '编辑成功！',
                        type: 'success'
                })
            }else {
                this.$message({
                    message: '学号或姓名不能为空',
                    type: 'error'
                })
                return false
            }
            this.loading = false
        },
        cancleSub() {
            this.EditVisible = false
        },
        async subCreat() {//添加请求
            if(this.setClassName) {
                let asc = {
                    teacherIdOfSubject: this.userInfo.userId,
                    theSubject: this.setClassName
                }
                const {data: res} = await this.$http.post('/teacher/creatClass', asc)
                console.log(res)
                if(res.status=='success') {
                    const s = 1
                    await this.$store.dispatch('getNewClass', s)
                    this.$message({
                        type: 'success',
                        message: '创建成功！'
                    });
                    this.leftback()
                }else {
                    this.$message({
                        type: 'error',
                        message: '请求失败！'
                    });
                }
            }else {
                this.$message({
                    type: 'warning',
                    message: '必须填写班级名称！'
                });
            }
        },
        creatClass() {
            this.$confirm('您确定创建此班级吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.subCreat()
                             
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });          
            });
        }
    }
}
</script>

<style scoped>
.headerbox {
    min-height: 150px;
    background-color: #E4E7ED;
}
.backbtn {
    font-size: 1.5em;
    margin: 20px 0 0 20px;
    cursor: pointer;
}
.classname {
    font-size: 2em;
    text-align: center;
    padding: 30px;
}
.mainbox {
    padding-left: 60px;
    padding-right: 60px;
}
.plusbtn {
    text-align: end;
    margin: 10px;
}
.overline {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.delBtn {
    color: #F56C6C;
    cursor: pointer;
    
}
.inputBox {
    display: flex;
    align-items: center;
    /* widows: 50%; */
}
</style>