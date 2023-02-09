<template>
    <el-container>
        <el-header height="150px" class="headerbox">
            <div class="backbtn" @click="leftback">
                <i class="fa fa-long-arrow-left" style="margin-right: 10px;"></i>
                <span>返回</span>
            </div>
            <div class="classname">找回密码</div>
        </el-header>
        <el-main class="mainbox">
            <el-divider></el-divider>
            <el-steps :active="active" align-center finish-status="success">
                <el-step title="步骤1" description="请输入账号与角色"></el-step>
                <el-step title="步骤2" description="请输入验证码"></el-step>
                <el-step title="步骤3" description="请设置新密码"></el-step>
            </el-steps>
            <!-- 步骤1 -->
            <div v-show="this.active===0" class="formBox">
                <el-form :model="formId" status-icon :rules="rules" ref="formId" label-width="100px" class="ruleFormbox">
                    <el-form-item label="账号" prop="userId">
                        <el-input v-model="formId.userId" placeholder="请选输入账号"></el-input>
                    </el-form-item>
                    <el-form-item label="角色" prop="regist">
                        <el-select v-model="formId.regist" placeholder="请选择角色">
                            <el-option label="学生" value="1"></el-option>
                            <el-option label="教师" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="resetForm('formId')">重置</el-button>
                        <el-button type="primary" @click="submitForm('formId')">下一步</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <!-- 步骤2 -->
            <div v-show="this.active===1" class="formBox">
                <el-form :model="formPass" status-icon :rules="rules" ref="formPass" label-width="100px" class="ruleFormbox">
                    <el-form-item label="验证码" prop="prePass">
                        <el-input v-model="formPass.prePass"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="resetForm('formPass')">上一步</el-button>
                        <el-button type="primary" @click="submitForm('formPass')">下一步</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <!-- 步骤3 -->
            <div v-show="this.active===2" class="formBox">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="ruleFormbox">
                    <el-form-item label="新密码" prop="pass">
                        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="resetForm('ruleForm')">上一步</el-button>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <!-- 成功页面 -->
            <div v-show="this.active===3" class="formBox">
                <div class="otbx">
                    <i class="fa fa-check-circle-o icbox" aria-hidden="true"></i>
                    <h2>修改成功</h2>
                    <el-button type="text" @click="leftback">点击返回</el-button>
                </div>
            </div>
            <!-- 失败页面 -->
            <div v-show="this.active===4" class="formBox">
                <div class="otbx">
                    <i class="fa fa-times-circle-o icbox" style="color: #F56C6C;" aria-hidden="true"></i>
                    <h2>修改失败</h2>
                    <el-button type="text" @click="leftback">点击返回</el-button>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script>
export default {
    data () {
        var checkPre = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('请输入验证码'));
            }
            setTimeout(() => {
                if(this.formId.userId===this.formPass.prePass) {
                    callback();
                }else {
                    callback(new Error('验证失败'));
                }
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
            // 防止重复提交
            loading: false,
            active: 0,
            formId: {
                userId: '',
                regist: ''
            },
            ruleForm: {
                pass: '',
                checkPass: '',
            },
            formPass: {
                prePass: ''
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
                ],
                userId: [
                    { required: true, message: '请输入账号', trigger: 'blur' }
                ],
                regist: [
                    { required: true, message: '请选择角色', trigger: 'change' }
                ],
            }
        }
    },
    methods: {
        leftback() {
            this.$router.go(-1)
            this.clearform()
        },
        clearform() {
            this.active = 0
            this.$refs['formId'].resetFields()
            this.$refs['ruleForm'].resetFields()
            this.$refs['formPass'].resetFields()
        },
        submitForm(formName) {
        this.$refs[formName].validate(async (valid) => {
          if (valid) {
            // alert('submit!');
            this.active++
            if(formName==='ruleForm') {
                let asc = {
                    id: this.formPass.prePass,
                    password: this.ruleForm.checkPass,
                    identity: Number(this.formId.regist)
                }
                console.log(asc)
                const {data: res} = await this.$http.post('/forgetPassword', asc)
                console.log(res)
                if(res.status=='success') {
                    this.$message({
                        message: '修改成功！',
                        type: 'success'
                    })
                }else {
                    this.active++
                }
            }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        if(formName==='formId') {
            this.$refs[formName].resetFields();
        }else {
            this.active--
        }
      },
      next() {
          this.active++
        // if (this.active++ > 2) this.active = 0;
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
.icbox {
    font-size: 5em;
    color: #5F928C;
}
.otbx {
    text-align: center;
    margin-top: 30px;
}
</style>