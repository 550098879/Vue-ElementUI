<template>
    <div>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="name"
                    label="姓名"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="pwd"
                    label="密码"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="sex"
                    label="性别"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址"
                    width="200">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                    <el-button type="text" size="small">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--分页栏-->
        <div class="block">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currPage"
                    :page-sizes="[2, 4, 6, 8]"
                    :page-size="this.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="totalSize">
            </el-pagination>
        </div>
    </div>

</template>

<script>

    export default {
        methods: {
            handleClick(row) {
                //查看当前行数据
                console.log(row);
            },
            handleSizeChange(val) {
                //设置每页条数
                console.log(`每页 ${val} 条`);
                this.pageSize = val;
                //调用方法,刷新表格
                this.getUsers();
            },
            handleCurrentChange(val) {
                //设置当前页
                console.log(`当前页: ${val}`);
                this.currPage = val;
                this.getUsers();
            },
            getUsers(){
                //发送axios请求
                axios.get("http://localhost:8181/user/findAll/"+this.currPage+"/"+this.pageSize)
                    .then((resp) => {  //将this传递到回调函数中
                        console.log("response:",resp);
                        this.tableData = resp.data.content;
                        this.totalSize = resp.data.totalElements;
                    });
            }

        },

        data() {
            return {
                tableData: [],  //表格数据
                pageSize : 2,  //每页条数
                currPage : 1,  //当前页数
                totalSize : 1, //总条数

            }
        },
        created(){
            this.getUsers();
        }


    }
</script>

<style scoped>

</style>