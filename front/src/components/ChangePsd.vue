<template>
    <el-container>
        <el-header height="150px" class="headerbox">
            <div class="backbtn" @click="leftback">
                <i class="fa fa-long-arrow-left" style="margin-right: 10px;"></i>
                <span>班级</span>
            </div>
            <div class="classname">修改密码</div>
        </el-header>
        <el-main class="mainbox">
            <!-- <div class="overline">
                <h3>
                    个人资料
                </h3>
            </div> -->
            <el-divider></el-divider>
            <div class="infoBox">
                <div>
                    <el-avatar style="border: solid 1px #C0C4CC; cursor: pointer;" shape="square" :size="100" :fit="fit" :src="userInfo.userAvatar">{{userInfo.userName}}</el-avatar>
                </div>
                <div>
                    <h4>姓名：<span>{{userInfo.userName}}</span></h4>
                </div>
                <div>
                    <h4>账号：<span>{{userInfo.userId}}</span></h4>
                </div>
            </div>
            <div class="formBox">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="ruleFormbox">
                    <el-form-item label="原密码" prop="prePass">
                        <el-input type="password" v-model="ruleForm.prePass"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="pass">
                        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-main>
    </el-container>
</template>

<script>
import { mapState } from 'vuex'
export default {
    data () {
        var checkPre = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('请输入原密码'));
            }
            setTimeout(() => {
                callback();
            }, 1000);
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
        return {
            fit: 'cover',
            dialogTableVisible: false,
            dialogVisible: false,
            previewImg: '',
            oldImg: '',

            // 防止重复提交
            loading: false,


            ruleForm: {
                pass: '',
                checkPass: '',//新密码
                prePass: ''//原密码
            },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          prePass: [
            { validator: checkPre, trigger: 'blur' }
          ]
        }
        }
    },
    computed: {
        ...mapState(['userInfo'])
    },
    methods: {
        leftback() {
            this.$router.go(-1)
            this.clearform()
        },
        submitForm(formName) {
        this.$refs[formName].validate(async (valid) => {
          if (valid) {
            let asc = {
                id: this.userInfo.userId,
                prePassword: this.ruleForm.prePass,
                newPassword: this.ruleForm.checkPass,
                identity: this.userInfo.userRoot
            }
            const {data: res} = await this.$http.post('/editPassword', asc)
            // console.log(res)
            if(res.status == 'success') {
                this.$message({
                    message: '修改成功！',
                    type: 'success'
                })
            }else {
                this.$message({
                    message: '修改失败！',
                    type: 'error'
                })
            }
            this.clearform()
          } else {
            this.$message({
                message: '请求失败！',
                type: 'error'
            })
            this.$refs[formName].resetFields();
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      clearform() {
          this.$refs['ruleForm'].resetFields();
      }
    }
}
</script>

<style lang="scss" scoped>
h4 {
    margin: 5px;
    padding: 0;
}
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
    // width: 95%;
    // margin-left: 5%;    
    display: grid;
    grid-template-rows: repeat(3, auto);
    justify-items: center;
}
.ruleFormbox {
    width: 40%;
    margin-top: 50px;
    
}
.formBox {
    display: flex;
    justify-content: center;
}
</style>