<template>
    <div class="login_con">
        <div class="animates">
            <ul>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
        <div class="pos">
            <div class="angle">
                <div :class="{angbox: true ,angboxact: ruleForm.identity==1}" @click="handleClick(1)">学生</div>
                <div :class="{angbox: true ,angboxact: ruleForm.identity==2}" @click="handleClick(2)">教师</div>
            </div>
            <div class="login_box">
                <div class="fonts">登陆</div>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                    <el-form-item  prop="id" class="name">
                        <el-input v-model="ruleForm.id" placeholder="请输入账号"></el-input>
                    </el-form-item>
                    <el-form-item  prop="password">
                        <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item style="align-self: center;">
                        <el-button type="primary" @click="submitForm" round style="background-color: rgba(94,165,155,1); border-color:#fff; width: 90px;">登陆</el-button>
                    </el-form-item>
                </el-form>
                <div class="boxfooter">
                    <div>
                        <el-popover
                        placement="top"
                        width="200"
                        trigger="hover">
                        <div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                        </div>
                        <h3>学生用户，请联系教师取得账号密码。</h3>
                        <i slot="reference"  class="fa fa-question-circle tipBox"></i>
                        </el-popover>
                    </div>
                    <div style="cursor: pointer;" @click="getBack">忘记密码</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
    data () {
        return {
            ruleForm: {
                id: '123402311',
                password: '123456',
                // id: '',
                // password: '',
                identity: 1,
            },
            rules: {
                id: [
                { required: true, message: '请输入账号', trigger: 'blur' }
                ],
                password: [
                { required: true, message: '请输入密码', trigger: 'blur' },
                { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
                ]
            }
        }
    },
    computed: {
        ...mapState(['userInfo'])
    },
    methods: {
        handleClick(ang) {
            this.ruleForm.identity = ang
            if(ang===1) {
                this.ruleForm.id = '123402311'
            }else {
                this.ruleForm.id = '159001'
            }
        },
        getBack() {
            this.$router.push("/forgotpsd")
        },
        submitForm () {
            // let that = this
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    console.log(this.ruleForm)
                    let asc = {
                        id: this.ruleForm.id,
                        password: this.ruleForm.password,
                        identity: this.ruleForm.identity
                    }
                    let formData = new FormData()
                    formData.append('id', this.ruleForm.id)
                    formData.append('password', this.ruleForm.password)
                    formData.append('identity', this.ruleForm.identity)

                    let config = {
                        headers: {
                            // 'Content-Type': 'multipart/form-data',
                            'Content-Type': 'application/json'
                        }
                    }
                    console.log('asc',asc)
                    const {data: res} = await this.$http.post('/login', asc)
                    console.log('接收',res)
                    if(res.status === 'success') {
                        res.identity = this.ruleForm.identity
                        this.$store.dispatch('pushInfo', res)
                        window.sessionStorage.setItem('FIRSTCRT', 'true')//在线交流中使用
                        if(this.ruleForm.identity==1) {
                            this.$router.push("/stuhome")
                        }else {
                            this.$router.push("/home")
                        }
                    }else {
                        this.$refs.ruleForm.resetFields()
                        this.$message({
                            message: '登陆失败，请检查账号密码！',
                            type: 'error'
                        })
                        return false
                    }
                } else {
                    this.$message({
                        message: '请输入账号密码',
                        type: 'error'
                    })
                    return false
                }
            })
        }
        // async getstate() {
        //     return await window.localStorage.getItem('USERNAME')
        // },
        // signUp() {
        //     this.$router.push("/signup")
        // }
    }
}
</script>

<style scoped>

.login_con {
    background: rgba(94,165,155, 1);
    height: 100%;

    display: grid;
    grid-template-columns: 55% auto 5%;
    grid-template-rows: 20% auto 15%;
}
.pos {
    grid-column-start: 2;
    grid-row-start: 2;
}
.angle {
    display: flex;
    
    width: 140px;
    margin-left: 25px;
    font-size: 20px;
    font-family: '时尚中黑简体';
    border: solid 1px rgb(224, 247, 230);
    border-radius: 10px 10px 0 0;
}
.angbox {
    height:28px;
    width: 70px;
    color: #fff;
    border-radius: 10px 10px 0 0;
    text-align: center;
    cursor: pointer;
}
.angboxact {
    background-color: #fff;
    color: #5F928C;
}
.login_box {
    width: 400px;
    height: 330px;
    background-color: #fff;
    border-radius: 10px;

    /* grid-column-start: 2;
    grid-row-start: 2; */

    display: flex;
    flex-direction: column;
}
.fonts {
    font-family: '时尚中黑简体';
    font-size: 2em;
    font-weight: 900;
    color: #5F928C;
    margin: 25px;

    align-self: center;
}
.demo-ruleForm {
    align-self: center;
    width: 70%;
    margin: 0;
    padding: 0;

    display: flex;
    flex-direction: column;
}
.boxfooter {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 0 40px 0 40px;
    color: rgba(94,165,155,1);
    
}
i {
    margin: 0 15px 0 15px;
}
.tipBox {
  font-size: 20px;
}
/* 动画 */
.animates {
    /* grid-column-start: 2; */
    grid-row-start: 2;
    align-self: center;
    justify-self: center;
}
ul {
    height: 200px;
    display: flex;
}
li {
    list-style-type:none;
    width: 20px;
    height: 20px;
    border-radius: 10px;
    background-color: #fff;
    margin-right: 20px;
}
li:nth-child(1) {
    /* background: red; */
    animation: love1 4s 0s infinite;
    background: #fff;
}
li:nth-child(2) {
    /* background: darkturquoise; */
    animation: love2 4s 0.2s infinite;
    background: #fff;
}
li:nth-child(3) {
    /* background: darksalmon; */
    animation: love3 4s 0.4s infinite;
    background: #fff;

}
li:nth-child(4) {
    /* background: deeppink; */
    animation: love4 4s 0.6s infinite;
    background: #fff;
}
li:nth-child(5) {
    /* background: yellow; */
    animation: love5 4s 0.8s infinite;
    background: #fff;
}
li:nth-child(6) {
    /* background: deeppink; */
    animation: love4 4s 0.6s infinite;
    background: #fff;
}
li:nth-child(7) {
    /* background-color: darksalmon; */
    animation: love3 4s 0.4s infinite;
    background: #fff;
}
li:nth-child(8) {
    /* background: darkturquoise; */
    animation: love2 4s 0.2s infinite;
    background: #fff;
}
li:nth-child(9) {
    /* background: red; */
    animation: love1 4s 0s infinite;
    background: #fff;
}

@keyframes love1 {
    30%, 50% {
        height: 60px;
        transform: translateY(-30px);
    }
    70%, 100% {
        height: 0px;
        transform: translateY(0px);
    }
}
@keyframes love2 {
    30%, 50% {
        height: 125px;
        transform: translateY(-60px);
    }
    70%, 100% {
        height: 0px;
        transform: translateY(0px);
    }
}
@keyframes love3 {
    30%, 50% {
        height: 160px;
        transform: translateY(-75px);
    }
    70%, 100% {
        height: 0px;
        transform: translateY(0px);
    }
}
@keyframes love4 {
    30%, 50% {
        height: 180px;
        transform: translateY(-60px);
    }
    70%, 100% {
        height: 0px;
        transform: translateY(0px);
    }
}
@keyframes love5 {
    30%, 50% {
        height: 200px;
        transform: translateY(-45px);
    }
    70%, 100% {
        height: 0px;
        transform: translateY(0px);
    }
}
</style>
