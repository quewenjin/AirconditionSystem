<template>
    <div>
        <p>作业列表</p>
        <el-divider></el-divider>
        <el-table
            :data="homeworkList"
            stripe
            style="width: 100%">
            <el-table-column
            prop="workId"
            label="作业号"
            width="180">
            </el-table-column>
            <el-table-column
            prop="workTitle"
            label="作业名称"
            width="180">
            </el-table-column>
            <el-table-column
            prop="workCloseTime"
            label="截止日期">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="checkWork(scope.$index, scope.row)" class="animated fadeIn">批改</el-button>
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
                    isShow: false
                },
                {
                    id: '12987123',
                    name: '第七章作业',
                    desc: '荷兰优质淡奶，奶香浓而不腻',
                    ddl: '2020-05-30',
                    isShow: false
                },
                {
                    id: '12987125',
                    name: '第六章作业',                    
                    desc: '荷兰优质淡奶，奶香浓而不腻',                    
                    ddl: '2020-05-28',
                    isShow: false
                },
                {
                    id: '12987126',
                    name: '第五章作业',                   
                    desc: '荷兰优质淡奶，奶香浓而不腻',                   
                    ddl: '2020-04-31',
                    isShow: false
                }
            ]
        }
    },
    computed: {
        ...mapState(['homeworkList'])
    },
    methods: {
        checkWork(index, row) {
            this.$router.push({
                name: 'PaperCheck',
                query:{homeworkCon: JSON.stringify(row)}
            })
            this.$store.dispatch('detailList', row.workId)
        }
    }
}
</script>

<style>

</style>