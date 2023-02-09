<template>
    <div id="app">
        <h3>授课计划</h3>
        <el-divider></el-divider>
        <!-- <el-button @click="addVisible = true"><i class="fa fa-pencil"></i>添加</el-button> -->
        <!-- <vue-event-calendar
        :events="demoEvents"
        title="授课计划"
        @day-changed="handleDayChanged"> -->
        <!-- <template scope="props"> -->
            <!-- <el-button  @click="addVisible = true">添加</el-button> -->
            <!-- <el-button  @click="addVisible = true" style="margin: 0; padding: 8px 20px 8px 20px;"><i class="fa fa-pencil-square-o addBoxpen" aria-hidden="true"></i></el-button>
            <div v-for="(event, index) in props.showEvents" :key="index" class="event-item" @mouseenter="event.isShow = true" @mouseleave="event.isShow = false">
                <div class="titleBox">
                    <div>{{index+1}}.{{event.title}}</div>
                    <div>{{event.date}}<i @click="delPlan(event, index)" v-show="event.isShow" class="el-icon-delete icoBtn animated fadeIn"></i></div>
                </div>
                <hr style="FILTER:alpha(opacity=100,finishopacity=0,style=3)" width="95%" color=#E4E7ED SIZE=1/>
                <div>
                    {{event.desc}}
                </div>
            </div>
        </template> -->
        <!-- </vue-event-calendar> -->
        <div>
            <el-dialog :visible.sync="addVisible" center :close-on-click-modal="false" :destroy-on-close="true">
                <template slot="title">
                    <div class="titlebox">添加计划</div>
                </template>
                <div>
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="时间">
                            <el-date-picker type="date" placeholder="选择日期" v-model="form.date" value-format="yyyy/MM/dd" style="width: 50%;"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="标题">
                            <el-input v-model="form.title"></el-input>
                        </el-form-item>
                        <el-form-item label="内容">
                            <el-input type="textarea" v-model="form.desc"></el-input>
                        </el-form-item>                        
                        <el-form-item>
                            <el-button type="primary" @click="addPlan">添加</el-button>
                            <el-button @click="addVisible = false">取消</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'app',
  computed: {
    ...mapState(['userInfo'])
  },
  data () {
    return {
      addVisible: false,
      form: {
          date: '',
          title: '',
          desc: ''
      },
      demoEvents: []
    }
  },
  created() {
    this.creMeno()
  },
  methods: {
    handleDayChanged (data) {
      console.log('date-changed', data)
      this.form.date = data.date
    //   this.addVisible = true
    //   this.$EventCalendar.toDate('2020/7/10')
    },
    // handleMonthChanged (data) {
    //   console.log('month-changed', data)
    // },
    async creMeno() {
      let newArr = await this.getMemo()//更新
      this.demoEvents = JSON.parse(JSON.stringify(newArr))
    },
    async getMemo() {
      let asc = {
        teacherId: this.userInfo.userId
      }
      const {data: res} = await this.$http.post('/teacher/getMemo', asc)
      // console.log(res)
      if(res.teacherMemo){
        let aa = this.strToArrChange(res.teacherMemo)//解析
        for(let v of aa) {
          v.isShow = false
        }
        return aa
      }else {
        return []
      }
      // console.log('获取',aa)
      
    },
    async setMemo(arr) {
      let temp = JSON.parse(JSON.stringify(arr))
      let ss = ''
      if(temp.length!==0) {
        ss = this.arrToStrChange(temp)//转换
      }
      let asc = {
        teacherId: this.userInfo.userId,
        teacherMemo: ss
      }
      const {data: res} = await this.$http.post('/teacher/setMemo', asc)
      console.log(res)
      if(res.status == 'success') {
        let newArr = await this.getMemo()//更新
        this.demoEvents = JSON.parse(JSON.stringify(newArr))
        return res
      }else {
        return {status: 'error'}
      }
      
    },
    async addPlan() {
      if(this.form.date) {
        let temp = JSON.parse(JSON.stringify(this.demoEvents))
        let asc = {
            date: this.form.date,
            title: this.form.title,
            desc: this.form.desc,
            isShow: false
        }
        temp.splice(0,0,asc)
        
        let res = await this.setMemo(temp)
        console.log('addplan', res)
        if(res.status=='success') {
          this.form.date = ''
          this.form.title = ''
          this.form.desc = ''
        }
        this.addVisible = false
      }else {
        this.$message({
          message: '请选择日期！',
          type: 'error'
        })
      }
      
    },
    async delPlan(row, index) {
      let aa = JSON.parse(JSON.stringify(this.demoEvents))
      console.log(aa)
      let newArr = aa.reduce((total, current) => {
          // ((current.title !== row.title)&&(current.desc!==row.desc)) && total.push(current);
          current.isShow!==true && total.push(current);
          return total;
      }, []);
      console.log(newArr)

      let res = await this.setMemo(newArr)//更新列表
      console.log('delplan', res)
      
    },
    arrToStrChange(phoneList) {
        for(var i=0;i<phoneList.length;i++){
        var str=""
        for(var j in phoneList[i]){
            str+=j+"="+phoneList[i][j]+"|"
      }
        str=str.slice(0,-1)
        phoneList[i]=str
    }
      var str1="?"+phoneList.join("&")
      // console.log(str1)
      return str1
     
    },
    strToArrChange(str1) {
       var str1=str1.slice(1);
      var arr=str1.split("&");
      var list=[];
      for(var j=0;j<arr.length;j++){
          var objArr=arr[j].split("|")
          var obj={};
          for(var k=0;k<objArr.length;k++){
              var objs=objArr[k].split("=");
              obj[objs[0]]=String(objs[1]).slice(0);
          }
          list.push(obj)
      }
      // console.log(list)
      return list
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 30px;
}
.titleBox {
    display: flex;
    justify-content: space-between;
}
.event-item {
    padding-top: 10px !important;
    padding-bottom: 10px !important;
}
.icoBtn {
    margin-left: 5px;
    color: #F56C6C;
    cursor: pointer;
}
.addBoxpen {
  font-size: 1.5em;
}
/* h1, h2, h3 {
  font-weight: normal;
  margin: 0;
  padding: 0;
} */
/* ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
} */
/* a {
  color: #42b983;
} */
/* .t-center{
  text-align: center;
  margin: 20px;
} */
/* .mt150{
  margin-top: 150px;
} */
</style>