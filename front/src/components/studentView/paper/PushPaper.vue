<template>
    <div>
        <div>
          <span class="backbtn" @click="$router.back()"><i class="fa fa-long-arrow-left"></i></span>
          <span>作业号：{{ $route.query.rowInfo.workId}}</span>
        </div>
        <el-divider></el-divider>
        <div>
            <h4>标题：{{$route.query.rowInfo.workTitle}}</h4>
            <p>内容：{{$route.query.rowInfo.workContext}}</p>
            <p style="margin-left: 3em">
                <el-image
                    v-for="(img, index) in $route.query.rowInfo.pictures"
                    :key="index"
                    style="width: 100px; height: 100px; margin-right: 10px;"
                    :src="img" 
                    :preview-src-list="$route.query.rowInfo.pictures">
                </el-image>
            </p>
        </div>
        <div style="height: 500px" class="box">
                <quill-editor                
                v-model="form.content"
                :style="{height: '400px'}"
                :options="editorOption"
                @blur="onEditorBlur($event)"
                @change="onEditorChange($event)"
                ></quill-editor>  
        </div>
        <div>
            <el-button :loading="loading" type="primary" @click="handleSave">提交</el-button>
            <el-dialog title="预览效果" :visible.sync="dialogTableVisible">
                <div style="min-height: 60vh;">
                    <h1 class="news-title">{{ form.title }}</h1>
                    <div class="news-content" v-html="form.content"></div>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'
import { mapState } from 'vuex'

export default {
    components: { quillEditor },
    data() {
        return {
            loading: false,
            borderColor: "#dcdfe6",
            dialogTableVisible: false,
            form: {
                title: "",
                module: "",
                content: "",
            },
            editorOption: {
                placeholder: "",
                modules: {
                    toolbar: [
                        [{ header: [1, 2, 3, 4, 5, 6, false] }],
                        [{ size: ["small", false, "large", "huge"] }],
                        ["bold", "italic", "underline", "strike"],
                        ["blockquote", "code-block"],
                        [{ list: "ordered" }, { list: "bullet" }],
                        [{ script: "sub" }, { script: "super" }],
                        [{ indent: "-1" }, { indent: "+1" }],
                        [{ direction: "rtl" }],
                        [{ color: [] }, { background: [] }],
                        [{ align: [] }],
                        ["clean"],
                        ["link", "image", "video"],
                    ],
                },
            },
            rules: {
                title: [
                {
                    required: true,
                    message: "请输入标题",
                    trigger: "blur",
                },
                ],
                module: [
                {
                    required: true,
                    message: "请选择模块",
                    trigger: "change",
                },
                ],
                content: [
                    {
                        required: true,
                        message: "请输入内容",
                        trigger: "blur",
                    },
                ],
            },
        }
    },
    computed: {
        ...mapState(['userInfo', 'stuClassInfo'])
    },
    methods: {
        onEditorBlur(quill) {
        // 失去焦点事件

        //   this.$refs.form.validateField("content", (errorMsg) => {
        //     this.borderColor = "#dcdfe6";
        //     if (errorMsg) {
        //       this.borderColor = "#F56C6C";
        //     }
        //   });
        },
        onEditorChange({ quill, html, text }) {
            // 内容改变事件
            this.form.content = html;
        },
        // handleSee() {
        //   this.$refs["form"].validate((valid) => {
        //     this.$refs.form.validateField("content", (errorMsg) => {
        //       this.borderColor = "#dcdfe6";
        //       if (errorMsg) {
        //         this.borderColor = "#F56C6C";
        //       }
        //     });
        //     if (valid) {
        //       this.dialogTableVisible = true;
        //     } else {
        //       return false;
        //     }
        //   });
        // },
        async handleSave() {
        //   this.$refs["form"].validate((valid) => {
        //     this.$refs.form.validateField("content", (errorMsg) => {
        //       this.borderColor = "#dcdfe6";
        //       if (errorMsg) {
        //         this.borderColor = "#F56C6C";
        //       }
        //     });
        //     if (valid) {
        //       this.$baseMessage("submit!", "success");
        //     } else {
        //       return false;
        //     }
        //   });
            this.loading = true
            this.$loading({
                text: '提交中',
                background: 'rgba(0, 0, 0, 0.7)',
                target: document.querySelector('.box')
            })
            console.log(this.form.content)
            let formData = new FormData()
            formData.append('submitContext', this.form.content)
            formData.append('subjectId', this.$route.query.rowInfo.workId)
            formData.append('studentId', this.userInfo.userId)
            let asc = {
                workId: this.$route.query.rowInfo.workId,
                // subjectId: this.stuClassInfo.defaultInfo.subjectId,
                studentId: this.userInfo.userId,
                submitContext: this.form.content
            }
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }
            let {data: res} = await this.$http.post('/student/submitWork', asc)
            console.log(res)
            if(res.status=='success') {
                
                // this.form.content = ''
                let {data: res} = await this.$http.post('/student/getWorksOfSubject', {studentId: this.userInfo.userId,subjectId: this.stuClassInfo.defaultInfo.subjectId})//切换
                console.log(res)
                this.$store.dispatch('pushChangeClass', res)
                if(this.$route.query.rowInfo.subState==0){
                    this.stuClassInfo.defaultInfo.shouldSubmit--
                }
                this.$router.back()
                this.$message({
                    message: "提交成功！",
                    type:'success'
                })
            }else {
                this.$message({
                    message: "提交失败！请重试！",
                    type:'error'
                })                
            }
            this.loading = false
            // this.$store.dispatch('pushWork', this.form)
        },
        async getWorkContent() {
            let asc = {
                studentId: this.userInfo.userId,
                workId: this.$route.query.rowInfo.workId
            }
            let {data: res} = await this.$http.post('/teacher/getSubContext', asc)
            if(res) {
                this.form.content = res.subContext
            }
        }
    },
    created() {
        this.getWorkContent()
    }
}
</script>

<style lang="scss" scoped>
.backbtn {
    font-size: 1.2em;
    margin-right: 10px;
    margin-left: 10px;
    cursor: pointer;
    width: 50px;
    color: #409EFF;
}

// .news {
//   &-title {
//     text-align: center;
//   }

//   &-content {
//     ::v-deep {
//       p {
//         line-height: 30px;

//         img {
//           display: block;
//           margin-right: auto;
//           margin-left: auto;
//         }
//       }
//     }
//   }
// }
</style>