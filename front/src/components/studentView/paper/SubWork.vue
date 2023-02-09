<template>
    <div>
        <p>提交列表</p>
        <el-divider></el-divider>
        <el-table
            :data="stuHomeworkList"
            stripe
            style="width: 100%">
            <el-table-column
            prop="workId"
            label="作业号"
            width="180">
            </el-table-column>
            <el-table-column
            prop="workTitle"
            label="作业标题"
            width="180">
            </el-table-column>
            <el-table-column
            prop="workCloseTime"
            label="截止日期">
            </el-table-column>
            <el-table-column label="提交状况">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.subState==1">已提交</el-tag>
                    <el-tag v-else type="danger">未提交</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="subWork(scope.$index, scope.row)" class="animated fadeIn">提交</el-button>
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
            tableData: [
                {
                    id: '12987122',
                    name: '第八章作业',
                    desc: '荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻荷兰优质淡奶，奶香浓而不腻',
                    ddl: '2020-05-31',
                    subState: true,
                    subDate: '2020-06-01',
                    checkState: true,
                    point: 98 
                },
                {
                    id: '12987123',
                    name: '第七章作业',
                    desc: '荷兰优质淡奶，奶香浓而不腻',
                    ddl: '2020-05-30',
                    subState: true,
                    subDate: '2020-06-01',
                    checkState: false,
                    point: '未批改'
                    
                },
                {
                    id: '12987125',
                    name: '第六章作业',                    
                    desc: '荷兰优质淡奶，奶香浓而不腻',                    
                    ddl: '2020-05-28',
                    subState: false,
                    subDate: '0000-00-00',
                    checkState: false,
                    point: '未批改'
                    
                },
                {
                    id: '12987126',
                    name: '第五章作业',                   
                    desc: '荷兰优质淡奶，奶香浓而不腻',                   
                    ddl: '2020-04-31',
                    subState: false,
                    subDate: '0000-00-00',
                    checkState: false,
                    point: '未批改'
                    
                }
            ]
        }
    },
    computed: {
        ...mapState([ 'stuHomeworkList' ])
    },
    methods: {
        subWork(index, row) {
            this.$router.push({
                name: 'PushPaper',
                query:{rowInfo: row}
            })
        }
    }
}
</script>

<style>

</style>