<template>
    <el-container>
        <el-header height="150px" class="headerbox">
            <div class="backbtn" @click="leftback">
                <i class="fa fa-long-arrow-left" style="margin-right: 10px;"></i>
                <span>班级</span>
            </div>
            <div class="classname">{{userInfo.userName}}</div>
        </el-header>
        <el-main class="mainbox">
            <div class="overline">
                <h3>
                    个人资料
                </h3>
            </div>
            <el-divider></el-divider>
            <div class="infoBox">
                <div>
                    <el-avatar style="border: solid 1px #C0C4CC; cursor: pointer;" shape="square" :size="100" :fit="fit" :src="userInfo.userAvatar" @click.native="dialogTableVisible=true">{{userInfo.userName}}</el-avatar>
                </div>
                <div>
                    <h4>姓名：<span>{{userInfo.userName}}</span></h4>
                </div>
                <div>
                    <h4>账号：<span>{{userInfo.userId}}</span></h4>
                </div>
            </div>
            <div>
                <el-dialog :visible.sync="dialogTableVisible" center :close-on-click-modal="false" :destroy-on-close="true" @close="closeDia">
                    <template slot="title">
                        <div class="titlebox">更换头像</div>
                    </template>
                    <div>
                        <div class="previewBox">
                            <el-avatar style="border: solid 1px #C0C4CC;" shape="square" :size="250" :fit="fit" :src="previewImg">{{userInfo.userName}}</el-avatar>
                        </div>
                        <div class="previewBox">
                            <el-upload
                            class="upload-demo"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :on-preview="handlePreview"                           
                            :show-file-list="false"
                            :on-change='changeUpload'
                            :auto-upload="false"
                            :before-upload="beforeAvatarUpload">
                            <el-button size="small" type="primary">点击上传</el-button>
                            <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                            </el-upload>
                        </div>
                    </div>
                </el-dialog>
            </div>
            <!-- 裁剪图片 -->
            <el-dialog title="图片剪裁" :visible.sync="dialogVisible" append-to-body>
                <div class="cropper-content">
                    <div class="cropper" style="text-align:center">
                    <VueCropper
                        ref="cropper"
                        :img="option.img"
                        :outputSize="option.size"
                        :outputType="option.outputType"
                        :info="true"
                        :full="option.full"
                        :canMove="option.canMove"
                        :canMoveBox="option.canMoveBox"
                        :original="option.original"
                        :autoCrop="option.autoCrop"
                        :fixed="option.fixed"
                        :fixedNumber="option.fixedNumber"
                        :centerBox="option.centerBox"
                        :infoTrue="option.infoTrue"
                        :fixedBox="option.fixedBox"                        
                    ></VueCropper>
                    </div>
                </div>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="cancle">取 消</el-button>
                    <el-button type="primary" @click="finish" :loading="loading">确认</el-button>
                </div>
            </el-dialog>
        </el-main>
    </el-container>
</template>

<script>
import { VueCropper }  from 'vue-cropper' 
import { mapState } from 'vuex'
export default {
    components: {
        VueCropper
    },
    data () {
        return {
            fit: 'cover',
            dialogTableVisible: false,
            dialogVisible: false,
            previewImg: '',
            oldImg: '',

            // 裁剪组件的基础配置option
            option: {
                img: '', // 裁剪图片的地址
                info: true, // 裁剪框的大小信息
                outputSize: 0.8, // 裁剪生成图片的质量
                outputType: 'jpeg', // 裁剪生成图片的格式
                canScale: false, // 图片是否允许滚轮缩放
                autoCrop: true, // 是否默认生成截图框
                // autoCropWidth: 200, // 默认生成截图框宽度
                // autoCropHeight: 200, // 默认生成截图框高度
                fixedBox: true, // 固定截图框大小 不允许改变
                fixed: true, // 是否开启截图框宽高固定比例
                fixedNumber: [5, 5], // 截图框的宽高比例
                full: false, // 是否输出原图比例的截图
                canMoveBox: false, // 截图框能否拖动
                original: false, // 上传图片按照原始比例渲染
                centerBox: false, // 截图框是否被限制在图片里面
                infoTrue: true // true 为展示真实输出图片宽高 false 展示看到的截图框宽高
            },
            picsList: [],  //页面显示的数组
            // 防止重复提交
            loading: false,

        }
    },
    computed: {
        ...mapState(['userInfo'])
    },
    methods: {
        leftback() {
            this.$router.go(-1)
        },
        handlePreview(file) {
            console.log(file);
        },
        async beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;
            console.log(file)
            this.previewImg = await this.transforBase(file)
            
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        async transforBase(file) {
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
        },
        changeUpload(file, fileList) {
            const isLt5M = file.size / 1024 / 1024 < 5
            if (!isLt5M) {
                this.$message.error('上传文件大小不能超过 5MB!')
                return false
            }
            this.fileinfo = file.raw
            console.log('列表',file.raw)
            // 上传成功后将图片地址赋值给裁剪框显示图片
            this.$nextTick( async () => {
                this.option.img = await this.transforBase(file.raw)
                this.dialogVisible = true
            })
        },
        cancle() {
            this.previewImg = this.oldImg
            this.loading = false
            this.dialogVisible = false
        },
        closeDia() {
            this.previewImg = this.oldImg
            this.loading = false
        },
        finish() {
            this.$refs.cropper.getCropData(async(data) => {
                var fileName = 'goods' + this.fileinfo.uid
                // console.log('装酒',data)                
                this.loading = true
                this.previewImg = data
                
                let asc = {
                    userId: this.userInfo.userId,
                    userPicture: this.previewImg
                }
                let res = {}
                if(this.userInfo.userRoot===1) {
                    const {data: ress} = await this.$http.post('/student/updatePicture', asc)
                    res = ress
                }else {
                    const {data: rest} = await this.$http.post('/teacher/updatePicture', asc)
                    res = rest
                }
                console.log(res)
                if(res.status == 'success') {
                    this.userInfo.userAvatar = this.previewImg
                    this.oldImg =  this.userInfo.userAvatar
                    // this.previewImg =t ''
                    this.dialogVisible = false
                    this.loading = false
                    this.$message({
                        message: '上传成功！',
                        type: 'success'
                    })
                }else {
                    this.$message({
                        message: '上传失败！',
                        type: 'error'
                    })
                    this.previewImg = this.oldImg
                    this.loading = false
                }
                this.dialogTableVisible = false
                //上传阿里云服务器
                // client().put(fileName, data).then(result => {
                // this.dialogVisible = false
                // this.picsList.push(result.url)
                // }).catch(err => {
                // console.log(err)
                // this.loading = false
                // })
            })
        }
    },
    created() {
        this.previewImg = this.userInfo.userAvatar
        this.oldImg = this.userInfo.userAvatar
    }
}
</script>

<style lang="scss" scoped>
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
    font-size: 1.5em;
    color: #fff;
    
}
.iconclass {
    width: 30px;
    height: 30px;
    padding: 8px;
    background-color: #409EFF;
    border-radius: 50%;
    cursor: pointer;
}
.overline {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.infoBox {
    width: 95%;
    margin-left: 5%;
}
.previewBox {
    display: flex;
    justify-content: center;
    margin: 10px;
}

.cropper-content {
    .cropper {
        width: auto;
        height: 300px;
    }
}
</style>