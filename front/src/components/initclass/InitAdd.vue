<template>
    <el-card class="box-card" shadow="never" :body-style="{padding: '0px', margin: '0px' }">
      <div v-show="isShow">
        <div class="inputposition">
            <div class="Wrapper">
                <div class="Input">
                    <input type="text" id="input" class="Input-text" autocomplete="off" placeholder="请输入姓名" v-model="name">
                    <label for="input" class="Input-label">姓名</label>
                </div>
            </div>
            <div class="Wrapper">
                <div class="Input">
                    <input type="text" id="input" class="Input-text" autocomplete="off" placeholder="请输入学号" v-model="num">
                    <label for="input" class="Input-label">学号</label>
                </div>
            </div>
        </div>
        <div class="subbtn">
            <el-button type="primary" round @click="addOne">添加</el-button>
        </div>
        <el-divider>其他方式</el-divider>
        <div class="subbtn">
          <el-button type="primary" plain @click="isShow = false">批量导入</el-button>
        </div>
      </div>
      <div class="uploadbox" v-show="!isShow">
        <div class="hahas">
          <div style="align-self: center;">
            <el-upload
            ref="upload"
            :disabled="disabled"
            action="customize"
            name="openFile"
            :http-request="uploadFile"
            :before-upload="beforeAvatarUpload"
            :on-remove="handleRemove"
            :file-list="file"
            :limit="1">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传excel文件</div>
            </el-upload>
          </div>
          <div class="subsbth">
              <el-button type="info" @click="dialogTableVisible = true" size="mini">查看</el-button>
          </div>
          <div class="subsbth">
              <el-button type="primary" round @click="addSome">批量导入</el-button>
          </div>
        </div>
        <div>
          <el-divider></el-divider>
          <div style="display: flex; justify-content: center;">
            <div class="backbtn" @click="isShow = true">
              <i class="el-icon-back"></i>
            </div>
          </div>
          <div>
            <el-popover
              placement="top-start"
              width="250"
              trigger="hover">
              <div>
                <i class="fa fa-info-circle" aria-hidden="true"></i>
              </div>
              <h5>Excel文件中，至少包含以“姓名”、“学号”为键值的数据</h5>
              <div>
                <el-image :src="require('@/assets/tipimg.jpg')"></el-image>
              </div>
              <!-- <div > -->
                <i slot="reference"  class="fa fa-question-circle tipBox"></i>
              <!-- </div> -->
              <!-- <el-button slot="reference">hover 激活</el-button> -->
            </el-popover>
            
          </div>
        </div>
      </div>
      <el-dialog title="表格内容" center :visible.sync="dialogTableVisible" :modal="false" :fullscreen="isfull">
        <!-- <el-container> -->
          <!-- <el-main> -->
          <el-table :data="stuList" style="width: 100%" :height="tableHeight">
            <el-table-column property="studentName" label="姓 名" width="150"></el-table-column>
            <el-table-column property="studentId" label="学 号" show-overflow-tooltip></el-table-column>
          </el-table>
         <span slot="footer" class="dialog-footer">
          <el-button @click="fullscreenAct">{{btnmsg}}</el-button>
          <el-button type="primary" @click="dialogTableVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </el-card>
</template>

<script>
import XLSX from 'xlsx'
import {mapState} from 'vuex'

export default {
    data() {
        return {
            name: '',
            num: '',
            isShow: true,
            file: [],
            disabled: false,
            stuList: [],
            dialogTableVisible: false,
            tableHeight: '250px',
            btnmsg: '全 屏',
            isfull: false
        }
    },
    computed: {
      ...mapState(['teaClassInfo', 'tempMemberList'])
    },
    methods: {
      beforeAvatarUpload(file) {
        var testmsg=file.name.substring(file.name.lastIndexOf('.')+1)
        const extension = testmsg === 'xls'
        const extension2 = testmsg === 'xlsx'
        if (!extension && !extension2) {
          // console.log(testmsg)
          this.$message.error('上传Excel文件只能是 xls 或 xlsx 格式!');
          return false;
        }
        return (extension || extension2)
      },
      async uploadFile(params) { 
        const _file = params.file;
        const fileReader = new FileReader();
        fileReader.onload = (ev) => {
          try {
            const data = ev.target.result;
            const workbook = XLSX.read(data, {
              type: 'binary'
            });
            for (let sheet in workbook.Sheets) {
            //循环读取每个文件
              const sheetArray = XLSX.utils.sheet_to_json(workbook.Sheets[sheet]);
              console.log("读取文件");
              console.log(sheetArray)
              // 封装
              sheetArray.map(v => {
                let obj = {}     
                obj.studentName = v.姓名
                obj.studentId = String(v.学号)
                this.stuList.push(obj)
              })
              console.log('stulist',this.stuList)
              this.dialogTableVisible = true
            }
          } catch (e) {
              this.$message.warning('文件类型不正确！');
            }
        };
        fileReader.readAsBinaryString(_file);
      },
      fullscreenAct() {
        this.isfull = !this.isfull
        if(this.isfull) {
          this.tableHeight = '480px'
          this.btnmsg = '缩 小'
        }else {
          this.tableHeight = '250px'
          this.btnmsg = '全 屏'
        }
      },
      handleRemove(file, fileList) {
        this.stuList = []
      },
      addOne() {
        if(this.num&&this.name) {
          let asc = {
              studentId: this.num,
              studentName: this.name,
              isShow: false
          }
          this.tempMemberList.push(asc)
          console.log(this.tempMemberList)
          this.$message({
            message: '导入成功！',
            type: 'success'
          })
          this.num = ''
          this.name = ''
        }else {
          this.$message({
            message: '请添加内容',
            type: 'error'
          })
        }
      },
      addSome() {
        let asc = JSON.parse(JSON.stringify(this.stuList))
        console.log(asc)
        if(asc.length!==0) {
          for(let v of asc) {
            let s = {
              studentId: v.studentId,
              studentName: v.studentName,
              isShow: false
            }
            this.tempMemberList.push(s)
          }
          console.log(this.tempMemberList)
          this.$message({
            message: '导入成功！',
            type: 'success'
          })
          this.$refs.upload.clearFiles()
          this.stuList = []
        }else {
          this.$message({
            message: '请添加内容',
            type: 'error'
          })
        }
      }
    }
}
</script>

<style scoped>
.box-card {
    width: 100%;
    height: 300px;
    border: snow;
}
.tipBox {
  font-size: 20px;
  color: #606266;
}
.inputposition {
    font-size: 15px;
    
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    /* align-content: center; */
}
.subbtn {
    text-align: center;
    margin-top: 20px
}
.subsbth {
  text-align: center;
}
.backbtn {
  font-size: 2.2em;
  width: 1.8em;
  height: 1.8em;
  color: #fff;
  background: #C0C4CC;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}
.backbtn:hover {
  background: #909399;
}
.uploadbox {
  height: 300px;
  min-height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.hahas {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
/* 输入框 */
@import url("https://fonts.googleapis.com/css?family=Dosis");
:root {
  /* generic */
  --gutterSm: 0.4rem;
  --gutterMd: 0.8rem;
  --gutterLg: 1.6rem;
  --gutterXl: 2.4rem;
  --gutterXx: 7.2rem;
  --colorPrimary400: #7e57c2;
  --colorPrimary600: #5e35b1;
  --colorPrimary800: #4527a0;
  --fontFamily: "Dosis", sans-serif;
  --fontSizeSm: 1.2rem;
  --fontSizeMd: 1.6rem;
  --fontSizeLg: 2.1rem;
  --fontSizeXl: 2.8rem;
  --fontSizeXx: 3.6rem;
  --lineHeightSm: 1.1;
  --lineHeightMd: 1.8;
  --transitionDuration: 300ms;
  --transitionTF: cubic-bezier(0.645, 0.045, 0.355, 1);
  /* floated labels */
  --inputPaddingV: var(--gutterMd);
  --inputPaddingH: var(--gutterLg);
  --inputFontSize: var(--fontSizeLg);
  --inputLineHeight: var(--lineHeightMd);
  --labelScaleFactor: 0.8;
  --labelDefaultPosY: 50%;
  --labelTransformedPosY: calc( (var(--labelDefaultPosY)) -  (var(--inputPaddingV) * var(--labelScaleFactor)) -  (var(--inputFontSize) * var(--inputLineHeight)) );
  --inputTransitionDuration: var(--transitionDuration);
  --inputTransitionTF: var(--transitionTF);
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

.Wrapper {
  -webkit-box-flex: 0;
      -ms-flex: 0 0 80%;
          flex: 0 0 80%;
  max-width: 50%;
  margin-top: 20px;
  align-self: center;
}

.Title {
  margin: 0 0 var(--gutterXx) 0;
  padding: 0;
  color: #fff;
  font-size: var(--fontSizeXx);
  font-weight: 400;
  line-height: var(--lineHeightSm);
  text-align: center;
  text-shadow: -0.1rem 0.1rem 0.2rem var(--colorPrimary800);
}

.Input {
  position: relative;
}

.Input-text {
  display: block;
  margin: 0;
  padding: var(--inputPaddingV) var(--inputPaddingH);
  padding-left: 10px;
  color: inherit;
  width: 300px;
  height: 40px;
  font-family: inherit;
  font-size: var(--inputFontSize);
  font-weight: inherit;
  line-height: var(--inputLineHeight);
  border: solid 1px #909399;
  border-radius: 0.4rem;
  -webkit-transition: box-shadow var(--transitionDuration);
  transition: box-shadow var(--transitionDuration);
}

.Input-text:focus {
  outline: none;
  box-shadow: 0.2rem 0.8rem 1.6rem var(--colorPrimary600);
}

.Input-label {
  display: block;
  position: absolute;
  bottom: 100%;
  left: 1rem;
  color: #311B92;
  font-family: inherit;
  font-size: var(--inputFontSize);
  font-weight: inherit;
  line-height: var(--inputLineHeight);
  opacity: 0;
  -webkit-transform: translate3d(0, var(--labelDefaultPosY), 0) scale(1);
          transform: translate3d(0, var(--labelDefaultPosY), 0) scale(1);
  -webkit-transform-origin: 0 0;
          transform-origin: 0 0;
  -webkit-transition: opacity var(--inputTransitionDuration) var(--inputTransitionTF), visibility 0ms var(--inputTransitionDuration) var(--inputTransitionTF), z-index 0ms var(--inputTransitionDuration) var(--inputTransitionTF), -webkit-transform var(--inputTransitionDuration) var(--inputTransitionTF);
  transition: opacity var(--inputTransitionDuration) var(--inputTransitionTF), visibility 0ms var(--inputTransitionDuration) var(--inputTransitionTF), z-index 0ms var(--inputTransitionDuration) var(--inputTransitionTF), -webkit-transform var(--inputTransitionDuration) var(--inputTransitionTF);
  transition: opacity var(--inputTransitionDuration) var(--inputTransitionTF), transform var(--inputTransitionDuration) var(--inputTransitionTF), visibility 0ms var(--inputTransitionDuration) var(--inputTransitionTF), z-index 0ms var(--inputTransitionDuration) var(--inputTransitionTF);
  transition: opacity var(--inputTransitionDuration) var(--inputTransitionTF), transform var(--inputTransitionDuration) var(--inputTransitionTF), visibility 0ms var(--inputTransitionDuration) var(--inputTransitionTF), z-index 0ms var(--inputTransitionDuration) var(--inputTransitionTF), -webkit-transform var(--inputTransitionDuration) var(--inputTransitionTF);
}

.Input-text:placeholder-shown + .Input-label {
  visibility: hidden;
  z-index: -1;
}

.Input-text:not(:placeholder-shown) + .Input-label,
.Input-text:focus:not(:placeholder-shown) + .Input-label {
  visibility: visible;
  z-index: 1;
  opacity: 1;
  -webkit-transform: translate3d(0, var(--labelTransformedPosY), 0) scale(var(--labelScaleFactor));
          transform: translate3d(0, var(--labelTransformedPosY), 0) scale(var(--labelScaleFactor));
  -webkit-transition: visibility 0ms, z-index 0ms, -webkit-transform var(--inputTransitionDuration);
  transition: visibility 0ms, z-index 0ms, -webkit-transform var(--inputTransitionDuration);
  transition: transform var(--inputTransitionDuration), visibility 0ms, z-index 0ms;
  transition: transform var(--inputTransitionDuration), visibility 0ms, z-index 0ms, -webkit-transform var(--inputTransitionDuration);
}

</style>