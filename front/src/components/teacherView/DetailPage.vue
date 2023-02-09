<template>
  <div class="export-excel-container">
    <div v-if="isShow" class="animated fadeIn">
      <el-form :inline="true" @submit.native.prevent>
        <el-form-item >
          <div class="backbtn" @click="isShow = false">
            <i class="fa fa-long-arrow-left"></i>
          </div>
        </el-form-item>
        <el-form-item label="文件名">
          <el-input v-model="filename" placeholder="请输出要导出文件的名称" />
        </el-form-item>
        <el-form-item label="文件类型">
          <el-select v-model="bookType" style="width: 80px">
            <el-option
              v-for="item in options"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="自动列宽">
          <el-radio-group v-model="autoWidth">
            <el-radio :label="true">
              是
            </el-radio>
            <el-radio :label="false">
              否
            </el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" size="medium" @click="handleDownload">
          导出 Excel
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="tabheader" v-else>
      <!-- <div @click="isShow = true">导出数据</div> -->
      <div>
        <span class="backbtn" @click="$router.back()"><i class="fa fa-long-arrow-left"></i></span>
        <span>作业号：{{$route.query.row.workId}}</span>
        <el-divider direction="vertical"></el-divider>
        <span>已提交：{{$route.query.row.alreadySubmitted}}/{{$route.query.row.shouldSubmit}}</span>
      </div>
      <el-button type="primary" size="medium"  @click="isShow = true">
        导出数据
      </el-button>
    </div>
    <el-divider></el-divider>
    <!-- 表格数据 -->
    <el-table :data="teaList">
      <el-table-column label="序号" width="55">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="姓名">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="学号">
        <template slot-scope="scope">
          {{ scope.row.stuNum }}
        </template>
      </el-table-column>
      <el-table-column label="提交日期">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.subState==1">{{ scope.row.subDate }}</el-tag>
          <el-tag v-else type="danger">未提交</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="作业分数">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.checkState==1">{{ scope.row.point }}</el-tag>
          <el-tag v-else type="warning">未批改</el-tag>
        </template>
      </el-table-column>
      
    </el-table>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  // name: "ExportExcel",
  data() {
    return {
      list: [
        {
          stuNum: '1234567',
          name: '张三',
          subState: true,
          subDate: '2020-06-01',
          checkState: true,
          point: 98
        },
        {
          stuNum: '1234568',
          name: '张四',
          subState: true,
          subDate: '2020-06-01',
          checkState: false,
          point: '未批改'
        },
        {
          stuNum: '1234568',
          name: '张五',
          subState: false,
          subDate: '0000-00-00',
          checkState: false,
          point: '未批改'
        }],
      listLoading: true,
      downloadLoading: false,
      filename: "",
      autoWidth: true,
      bookType: "xlsx",
      elementLoadingText: "正在加载...",
      options: ["xlsx", "csv", "txt"],
      isShow: false
    };
  },
  computed: {
    ...mapState(['teaList'])
  },
  created() {
    // this.fetchData();
  },
  methods: {
    fetchData() {
      this.listLoading = true;
      getList().then((response) => {
        this.list = response.data;
        this.listLoading = false;
      });
    },
    handleDownload() {
      this.downloadLoading = true;
      import("@/vendor/ExportExcel").then((excel) => {
        const tHeader = ["学号", "姓名", "提交日期", "作业分数"];
        const filterVal = ["stuNum", "name", "subDate", "point"];
        const list = this.teaList;
        const data = this.formatJson(filterVal, list);
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: this.filename,
          autoWidth: this.autoWidth,
          bookType: this.bookType,
        });
        this.downloadLoading = false;
      });
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map((v) =>
        filterVal.map((j) => {
          return v[j];
        })
      );
    },
  },
};
</script>

<style scoped>
.backbtn {
    font-size: 1.5em;
    margin-right: 10px;
    margin-left: 10px;
    cursor: pointer;
    width: 50px;
    color: #409EFF;
}
.backbtn:hover {
  color: rgba(40, 147, 255, 0.836);
}
.tabheader {
  display: flex;
  flex: 1;
  align-items: center;
  justify-content: space-between;
}
</style>