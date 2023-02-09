<template>
    <div>
        <!-- <div>
            <el-input v-model="search" style="width: 200px; margin-right: 80px;" size="mini" placeholder="输入关键字搜索"  clearable/>
        </div> -->
        <!-- <el-table
        :data="homeworkList.filter(data => !search || data.workTitle.toLowerCase().includes(search.toLowerCase())|| data.workContext.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%;"        
        @cell-mouse-enter="mouseEnter"
        @cell-mouse-leave="mouseLeave"
        @selection-change="handleSelectionChange">
            <el-table-column
                type="selection"
                width="35">
            </el-table-column>
            <el-table-column type="expand">
            <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="作业名称：">
                    <span>{{ props.row.workTitle }}</span>
                </el-form-item>
                <el-form-item label="截止日期：">
                    <span>{{ props.row.workCloseTime }}</span>
                </el-form-item>
                <el-form-item label="作业内容：">
                    <span>{{ props.row.workContext }}</span>
                </el-form-item>
                <el-form-item label="作业图片："> -->
                    <!-- <span><img v-for="(img, index) in props.row.pictures" :key="index" :src="img" /></span> -->
                    <!-- <span> -->
                        <!-- <div class="demo-image__preview"> -->
                        <!-- <el-image
                            v-for="(img, index) in props.row.pictures"
                            :key="index"
                            style="width: 100px; height: 100px; margin-right: 10px;"
                            :src="img" 
                            :preview-src-list="props.row.pictures">
                        </el-image> -->
                        <!-- </div> -->
                    <!-- </span>
                </el-form-item>
                </el-form>
            </template>
            </el-table-column>
            <el-table-column
            label="作业号"
            prop="workId">
            </el-table-column>
            <el-table-column
            label="作业名称"
            prop="workTitle">
            </el-table-column>
            <el-table-column
            label="截止日期"
            prop="workCloseTime">
            </el-table-column>

            <el-table-column> -->
            <!-- <template slot="header" slot-scope="scope">
                <div clss="tbheaderbox"> -->
                    <!-- <el-input v-model.trim="search" size="mini" placeholder="输入关键字搜索"  clearable/> -->
                    <!-- <span class="headerbtn" @click="addwork(scope.row)">发布作业</span>
                    <span class="headerbtn animated fadeIn" style="color: red;" v-show="toShow" @click="askDeleteThose">删除选中</span>
                </div>
            </template>
            <template slot-scope="scope">
                <el-button
                size="mini"
                @click="checkWork(scope.$index, scope.row)" v-show="scope.row.isShow" style="height: 20px;padding: 5px;" class="animated fadeIn">提交详情</el-button>
                <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)" v-show="scope.row.isShow" style="height: 20px;padding: 5px;" class="animated fadeIn">编辑</el-button>
                <el-button
                size="mini"
                type="danger"
                @click="askDelete(scope.$index, scope.row)" v-show="scope.row.isShow" style="height: 20px; padding: 5px;" class="animated fadeIn">删除</el-button>
            </template>
            </el-table-column>
        </el-table> -->
        <!-- <div>
            <el-dialog :visible.sync="dialogTableVisible" center :close-on-click-modal="false" :destroy-on-close="true" @close="cancleSub('pub')">
                <template slot="title">
                    <div class="titlebox">发布作业</div>
                </template>
                <div>
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="作业名称">
                            <el-input v-model="form.workTitle"></el-input>
                        </el-form-item>
                        <el-form-item label="作业内容">
                            <el-input type="textarea" v-model="form.workContext"></el-input>
                        </el-form-item>
                        <el-form-item label="上传图片">
                           <el-upload
                           ref="uploadpub"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            list-type="picture-card"
                            :before-upload="handleBefore"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            <el-dialog :visible.sync="dialogVisible" :modal="false">
                                <img width="100%" :src="dialogImageUrl" alt="">
                            </el-dialog>
                        </el-form-item> -->
                        <!-- <el-form-item label="作业号" style="width: 50%;">
                            <el-input v-model="form.workId"></el-input>
                        </el-form-item> -->
                        <!-- <el-form-item label="截止时间">
                            <el-date-picker type="date" placeholder="选择日期" v-model="form.workCloseTime" value-format="yyyy-MM-dd" style="width: 50%;"></el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <el-button :loading="loading" type="primary" @click="onSubmit">立即发布</el-button>
                            <el-button @click="cancleSub('pub')">取消</el-button>
                        </el-form-item>
                    </el-form> -->
                    
                    <!-- <div>
                        图片
                        <img :src="iconBase64[0]" />
                    </div> -->
                <!-- </div> -->
            <!-- </el-dialog> -->
        <!-- </div> -->
        <div>
            <el-dialog :visible.sync="EditVisible" center :close-on-click-modal="false" :destroy-on-close="true" @close="cancleSub('edt')">
                <template slot="title">
                    <div class="titlebox">编辑作业</div>
                </template>
                <div>
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="作业名称">
                            <el-input v-model="form.workTitle"></el-input>
                        </el-form-item>
                        <el-form-item label="作业内容">
                            <el-input type="textarea" v-model="form.workContext"></el-input>
                        </el-form-item>
                        <el-form-item label="上传图片">
                           <el-upload
                            ref="upload"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            list-type="picture-card"
                            :before-upload="handleBefore"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            <el-dialog :visible.sync="dialogVisible" :modal="false">
                                <img width="100%" :src="dialogImageUrl" alt="">
                            </el-dialog>
                        </el-form-item>
                        <el-form-item label="截止时间">
                            <el-date-picker type="date" placeholder="选择日期" v-model="form.workCloseTime" value-format="yyyy-MM-dd" style="width: 50%;"></el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <el-button :loading="loading" type="primary" @click="editDone">确认修改</el-button>
                            <el-button @click="cancleSub('edt')">取消</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
    data() {
        return {
            loading: false,
            dialogImageUrl: '',
            dialogVisible: false,
            iconBase64: [],
            tempfile: [],
            search: '',
            multipleSelection: [],
            toShow: false,
            dialogTableVisible: false,
            EditVisible: false,
            rowIndex: 0,
            form: {
                workId: '',
                workTitle: '',
                workCloseTime: '',
                workContext: '',
                pictures: [],
                isShow: false
            }
        }
    },
    computed: {
        ...mapState([ 'homeworkList', 'teaClassInfo' ])
    },
    methods: {
        // openDetails (row, column, cell, event) {
        //     console.log(row)
        //     row.id = '哈哈'
        //     this.isShow = true
        // },
        async transforBase(file) { //图片转base64
            // let temp = ''

            function reader (file) {
                return new Promise(function (resolve, reject) {
                    let reader = new FileReader();
                    reader.readAsDataURL(file)        
                    reader.onload = function () {
                        resolve(reader);
                    };
                    reader.onerror = reject;
                });
            }

            let {result: temp} = await reader(file)
            // console.log('外面',temp)
            return temp
            // reader(file)
            // .then(function (reader) {
            //     console.log('file 转 base64结果：' + reader.result);
            //     temp = reader.reader
            //     console.log('里面'+temp)    
            // })

            // let reader = new FileReader()
            
            // reader.readAsDataURL(file)
            // reader.onload = () => {
            //     console.log('file 转 base64结果：' + reader.result)
            //     this.tempfile.push(reader.result)
            //     temp = reader.result
            //     console.log('里面',temp)
            // }
            // reader.onerror = function (error) {
            //     console.log('Error: ', error)
            // }

            
        },
        async handleBefore(file) {
            console.log(file)
            let tem = await this.transforBase(file)
            this.iconBase64.push( tem )
            // console.log('直接打印',tem)
            // let reader = new FileReader()
            // reader.readAsDataURL(file)
            // reader.onload = () => {
            //     console.log('file 转 base64结果：' + reader.result)
            //     this.iconBase64.push( reader.result)
            //     this.form.pictures.push(reader.result)
            //     // console.log('第一个',this.iconBase64[0])
            // }
            // reader.onerror = function (error) {
            //     console.log('Error: ', error)
            // }
        },
        async handleRemove(file, fileList) {
            console.log(file, fileList);
            this.iconBase64 = []
            for( let v of fileList) {
                console.log(v)
                let tem = await this.transforBase(v.raw)
                this.iconBase64.push( tem )
            }
            console.log('移除后',this.iconBase64)
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        handleEdit(index, row) {
            console.log(index, row);
            this.form = this.deepClone(row)
            this.rowIndex = index
            this.EditVisible = true
        },
        async editDone() {
            this.loading = true
            this.form.pictures = this.iconBase64
            // 修改请求, 未含图片
            let asc = {
                workId: this.form.workId,
                workTitle: this.form.workTitle,
                workContext: this.form.workContext,
                closeTime: this.form.workCloseTime,
                pictures: []
            }
            for(let v of this.form.pictures) {
                let s = {
                    context: v
                }
                asc.pictures.push(s)
            }
            let {data: res} = await this.$http.post('/teacher/editWork', asc)
            if(res.status=='success') {
                this.homeworkList[this.rowIndex] = this.deepClone(this.form)
                this.form.pictures = []//清空列表
                this.iconBase64 = []
                this.EditVisible = false
                this.clearform()
                this.$message({
                        message: '编辑成功！',
                        type: 'success'
                    })
            }else {
                this.$message({
                    message: '请求失败！',
                    type: 'error'
                })
                return false
            }
            this.loading = false
            this.$refs.upload.clearFiles()
        },
        askDelete(index, row) {
            this.$confirm('此操作将删除该项作业, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.handleDelete(index, row)          
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });          
            });
        },
        async handleDelete(index, row) {
            console.log(index);
            let asc = [
                {
                    workId: row.workId
                }
            ]
            let {data: res} = await this.$http.post('/teacher/deleteWork', asc)
            if(res.status=='success') {
                this.homeworkList.splice(index, 1);
                this.$message({
                    message: '删除成功！',
                    type: 'success'
                })
             }else {
                this.$message({
                    message: '请求失败！',
                    type: 'error'
                })
                return false
            }
        },
        mouseEnter(row) {
            row.isShow = true
        },
        mouseLeave(row) {
            row.isShow = false
        },
        addwork(data) {
            console.log(data)
            this.dialogTableVisible = true
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        askDeleteThose() {
            this.$confirm('此操作将删除该项作业, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.delThose()  
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });          
            });
        },
        async delThose() {
            let asc = []
            for(let v of this.multipleSelection) {
                let s = {
                    workId: v.workId
                }
                asc.push(s)
            }
            // console.log(asc)
            const {data: res} = await this.$http.post('/teacher/deleteWork', asc)
            if(res.status=='success') {
                // 数组的批量删除，逆向循环
                for (let i = this.homeworkList.length - 1; i >= 0; i--) {
                    for (let j = this.multipleSelection.length - 1; j >= 0; j--) {
                        if (this.homeworkList[i] === this.multipleSelection[j]) {
                        this.homeworkList.splice(i, 1)
                        }
                    }
                }
                this.$message({
                    message: '删除成功！',
                    type: 'success'
                })

             }else {
                this.$message({
                    message: '请求失败！',
                    type: 'error'
                })
                return false
            }
            
        },
        deepClone(obj) {
            let clone = {};
            for (let key in obj) {
                clone[key] = obj[key];
            }
            return clone;
        },
        clearform() {
            for (let key in this.form) {
                this.form[key] = ''
            }
            this.form.isShow = false
        },
        async onSubmit() {
            this.loading = true
            console.log('submit!');
            this.form.pictures = this.iconBase64
            console.log(this.form.pictures)
            // 发送请求，暂无图片
            let asc = {
                subjectId: this.teaClassInfo.defaultInfo.subjectId,
                workTitle: this.form.workTitle,
                workContext: this.form.workContext,
                closeTime: this.form.workCloseTime,
                pictures: []
            }
            for(let v of this.form.pictures) {
                let s = {
                    context: v
                }
                asc.pictures.push(s)
            }
            // console.log('pictures', asc.pictures)
            let {data: res} = await this.$http.post('/teacher/publishWork', asc)
            console.log(res)
            if(res.status=='success') {
                this.form.workId = res.workId
                let item = this.deepClone(this.form)
                this.homeworkList.splice(0,0,item)
                this.form.pictures = []//清空列表
                this.iconBase64 = []
                this.dialogTableVisible = false
                this.$refs.uploadpub.clearFiles()
                this.clearform()
                this.$message({
                        message: '发布成功！',
                        type: 'success'
                })
             }else {
                this.$message({
                    message: '请求失败！',
                    type: 'error'
                })
                return false
            }
            this.loading = false
        },
        cancleSub(str) {
            this.form.pictures = []//清空列表
            this.iconBase64 = []
            this.dialogTableVisible = false
            this.EditVisible = false
            this.clearform()
            if(str==='pub') {
                this.$refs.uploadpub.clearFiles()
            }else {
                this.$refs.upload.clearFiles()
            }
            
        },
        checkWork(index, row) {
            console.log(index, row)
            this.$router.push({
                name: 'DetailPage',
                query:{row: row}
            })
            this.$store.dispatch('detailList', row.workId)
        }
    },
    watch: {
        'multipleSelection': function(newVal) {
            if(newVal.length) {
                this.toShow = true
            }else {
                this.toShow = false
            }
        }
    }
}
</script>

<style scoped>
.demo-table-expand {
    font-size: 0;
}
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
}
.headerbtn {
    color: #409EFF;
    font-weight: 500;
    cursor: pointer;
    margin-right: 25px;
}
</style>