<template>
    <div>
        <el-divider></el-divider>
        <div>
            <el-input v-model="search" style="width: 200px; margin-right: 80px;" size="mini" placeholder="输入关键字搜索"  clearable/>
        </div>        
        <el-table
        :data="stuHomeworkList.filter(data => !search || data.workTitle.toLowerCase().includes(search.toLowerCase())|| data.workContext.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%;"
        @cell-mouse-enter="mouseEnter"
        @cell-mouse-leave="mouseLeave">
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="作业标题：">
                            <span>{{ props.row.workTitle }}</span>
                        </el-form-item>
                        <el-form-item label="截止日期：">
                            <span>{{ props.row.workCloseTime }}</span>
                        </el-form-item>             
                        <el-form-item label="作业内容：">
                            <span>{{ props.row.workContext }}</span>
                        </el-form-item>
                        <el-form-item label="作业图片：">
                            <!-- <span><img v-for="(img, index) in props.row.pictures" :key="index" :src="img" /></span> -->
                            <span>
                                <!-- <div class="demo-image__preview"> -->
                                <el-image
                                    v-for="(img, index) in props.row.pictures"
                                    :key="index"
                                    style="width: 100px; height: 100px; margin-right: 10px;"
                                    :src="img" 
                                    :preview-src-list="props.row.pictures">
                                </el-image>
                                <!-- </div> -->
                            </span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column
            label="作业号"
            prop="workId">
            </el-table-column>
            <el-table-column
            label="作业标题"
            prop="workTitle">
            </el-table-column>
            <el-table-column
            label="截止日期"
            prop="workCloseTime">
            </el-table-column>

            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-badge is-dot :hidden="scope.row.subState==1" class="item">
                        <el-button
                        size="mini"
                        @click="checkWork(scope.$index, scope.row)" style="height: 20px;padding: 5px;" class="animated fadeIn">提交详情</el-button>
                    </el-badge>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
    data() {
        return {
            search: '',
            toShow: false,
        }
    },
    computed: {
        ...mapState([ 'stuHomeworkList' ])
    },
    methods: {
        mouseEnter(row) {
            row.isShow = true
        },
        mouseLeave(row) {
            row.isShow = false
        },
        checkWork(index, row) {
            console.log(index, row)
            this.$router.push({
                name: 'SubDetil',
                query:{ rowInfo: JSON.stringify(row) }
            })
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
.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>