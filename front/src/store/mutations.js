import { PUSH_INFO, SHOW_SEARCH, PUSH_WORK, PUSH_CHANGE_CLASS, DETAUL_LIST, GET_CLASSMATES, GET_NEW_CLASS, PUSH_NEWCLASS, CLEARFILE } from './mutation-types'
const moment = require("moment")
import axios from 'axios'

export default{
    async [PUSH_INFO] (state, {loginInfo}) { //让ADD_TODO变成一个变量
      state.userInfo.userId = loginInfo.id
      state.userInfo.userName = loginInfo.name
      state.userInfo.userRoot = loginInfo.identity
      state.userInfo.userAvatar = loginInfo.picture

      window.sessionStorage.setItem('USERNAME', state.userInfo.userName)

      let dec = {
        studentId: state.userInfo.userId
      }
      // 请求列表
      if(state.userInfo.userRoot == 1) {
        const {data: res} = await axios.post('/student/getSubjects', dec)
        console.log('列表',res)
        // state.userInfo.userClass = res.class
        // state.userInfo.userGrade = res.grade
        state.stuClassInfo.defaultInfo = res[0]
        state.stuClassInfo.classList = res
        state.stuClassInfo.defaultName = state.stuClassInfo.defaultInfo.subjectName
        //请求列表
        let {data: res2} = await axios.post('/student/getWorksOfSubject',{studentId: state.userInfo.userId, subjectId: state.stuClassInfo.defaultInfo.subjectId})//切换
        state.stuHomeworkList = res2
      }else {
        const {data: res} = await axios.post('/teacher/getSubjects', {teacherId :state.userInfo.userId})
        console.log('列表',res)
        state.teaClassInfo.defaultInfo = JSON.parse(JSON.stringify(res[0]))
        // console.log(state.teaClassInfo.defaultInfo)
        state.teaClassInfo.classList = JSON.parse(JSON.stringify(res))
        // state.teaClassInfo.defaultName =  `${res[0].grade}年级${res[0].class}班${res[0].subjectName}`
        // state.teaClassInfo.defaultName = state.stuClassInfo.defaultInfo.subjectName
        state.teaClassInfo.defaultName = res[0].subjectName
        // console.log(state.teaClassInfo.defaultName)
        let {data: res2} = await axios.post('/teacher/getWorksOfSubject', {subjectId: state.teaClassInfo.defaultInfo.subjectId})//切换
        for(let v of res2) {  
          // console.log(v);
          v.isShow = false
        };
        console.log('处理后的', res2)
        state.homeworkList = res2
      }
      
    },
    [SHOW_SEARCH](state,{search}){
      if(search){
        state.searchChatText = state.chatText.filter((value)=>{  //过滤数组元素
          return value.msg.includes(search) | value.uname.includes(search); //如果包含字符返回true
        });
      }else {
        state.searchChatText = [{uname: '查询失败！', msg: '请输入关键词！'}]
      }
      if(!state.searchChatText.length){
        state.searchChatText = [{uname: '无此用户！', msg: '无记录！'}]
      }
      console.log(state.searchChatText)
    },
    [PUSH_WORK] (state,{cons}) {
      state.content = cons.content
    },
    SOCKET_sid(state,data) {
        state.sessionId = data
    },
    SOCKET_chat(state,data) {
        console.log(data)
        let chatmsg = {
            uname: data.uname,
            msg: data.msg,
            date: moment().format("HH:mm:ss"),
            msgType: 'msgres',
            cid: data.cid,
            avatar: data.avatar
        }
        state.chatText.push(chatmsg)
    },
    SOCKET_response(state,respond) {
        console.log(respond)
        let joinmsg = {
            uname: respond.msg,
            msg: '',
            date: moment().format("HH:mm:ss"),
            msgType: 'respond'
        }
        state.chatText.push(joinmsg)
        // state.groupMembers += respond.change
        console.log("response目前返回人数："+state.groupMembers)
    },
    [PUSH_CHANGE_CLASS](state, list) {
      if(state.userInfo.userRoot==1) {
        state.stuHomeworkList = list
      }else {
        for(let v of list) {  
          // console.log(v);
          v.isShow = false
        };
        console.log('处理后的', list)
        state.homeworkList = list
      }
    },
    async [DETAUL_LIST](state, {id}) {
      let {data: res} = await axios.post('/teacher/workSubmission', { workId: id})
      if(res) {
        state.teaList = res
        console.log(state.teaList)
      }
    },
    async [GET_CLASSMATES](state) {
      if(state.userInfo.userRoot==1) {
        let {data: res} = await axios.post('/student/getClassmates',{ studentId: state.userInfo.userId, subjectId: state.stuClassInfo.defaultInfo.subjectId})
        state.memberList = res
      }else {
        let {data: res} = await axios.post('/teacher/studentsOfSubject',{ subjectId: state.teaClassInfo.defaultInfo.subjectId})
        state.memberList = res
      }
    },
    async [GET_NEW_CLASS](state, {solveType}) {
      const {data: res} = await axios.post('/teacher/getSubjects', {teacherId :state.userInfo.userId})
      console.log('列表',res)
      if(solveType==1) {//创建课程
        state.teaClassInfo.defaultInfo = res[res.length-1]
        state.teaClassInfo.classList = res
        
        // state.teaClassInfo.defaultName =  `${res[0].grade}年级${res[0].class}班${res[0].subjectName}`
        state.teaClassInfo.defaultName = res[res.length-1].subjectName
        //添加学生
        if(state.tempMemberList.length!==0) {
          console.log('默认',state.teaClassInfo.defaultInfo)
          let asc = {
              subjectIdOfStudnet: state.teaClassInfo.defaultInfo.subjectId,
              students: state.tempMemberList
          }
          let {data: res2} = await axios.post('/teacher/addStudent', asc)
          console.log(res2)
        }
      }else {
        state.teaClassInfo.defaultInfo = JSON.parse(JSON.stringify(res[0]))
        // console.log(state.teaClassInfo.defaultInfo)
        state.teaClassInfo.classList = JSON.parse(JSON.stringify(res))
        // state.teaClassInfo.defaultName =  `${res[0].grade}年级${res[0].class}班${res[0].subjectName}`
        // state.teaClassInfo.defaultName = state.stuClassInfo.defaultInfo.subjectName
        state.teaClassInfo.defaultName = res[0].subjectName
      }
      let {data: res2} = await axios.post('/teacher/getWorksOfSubject', {subjectId: state.teaClassInfo.defaultInfo.subjectId})//切换
      for(let v of res2) {
        // console.log(v);
        v.isShow = false
      };
      console.log('处理后的', res2)
      state.homeworkList = res2
    },
    async [PUSH_NEWCLASS](state) {
      const {data: res} = await axios.post('/student/getSubjects', {studentId: state.userInfo.userId})
      console.log('列表',res)
      // state.userInfo.userClass = res.class
      // state.userInfo.userGrade = res.grade
      state.stuClassInfo.defaultInfo = res[res.length-1]
      state.stuClassInfo.classList = res
      state.stuClassInfo.defaultName = state.stuClassInfo.defaultInfo.subjectName
      //请求列表
      let {data: res2} = await axios.post('/student/getWorksOfSubject',{studentId: state.userInfo.userId, subjectId: state.stuClassInfo.defaultInfo.subjectId})//切换
      state.stuHomeworkList = res2
    },
    [CLEARFILE](state) {
      state.tempMemberList = []
    }
}
