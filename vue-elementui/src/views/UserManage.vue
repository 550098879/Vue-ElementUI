<template>
    <div>
        <el-table
                :data="tableData"
                border
                style="width: 80%">
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
                    <el-button  @click="viewById(scope.row)" type="text" size="small">查看</el-button>
                    <el-button @click="deleteById(scope.row)" type="text" size="small">删除</el-button>
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

        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" :before-close="closeDialog">
            <el-form :model="ruleForm" status-icon :rules="rules" style="width: 60%"
                     ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="用户ID" prop="id">
                    <el-input v-model="ruleForm.id" readonly></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="ruleForm.sex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                        <el-radio label="保密"></el-radio>
                    </el-radio-group>
                </el-form-item>

<!--                <el-form-item label="密码" prop="pwd">-->
<!--                    <el-input type="password" v-model="ruleForm.pwd" autocomplete="off"></el-input>-->
<!--                </el-form-item>-->
<!--                <el-form-item label="确认密码" prop="checkPass">-->
<!--                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>-->
<!--                </el-form-item>-->
                <el-form-item label="地址" prop="address">
                    <el-input v-model.number="ruleForm.address"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>


    </div>

</template>

<script>

    export default {
        methods: {
            viewById(row) {
                //查看当前行数据
                console.log(row);
                this.ruleForm = row;
                this.dialogFormVisible = true;//设置嵌入对话框可见
            },
            deleteById(row) {
                //删除当前行数据
                console.log(row);
                axios.delete("http://localhost:8181/user/deleteById/"+row.id,{})
                .then((resp)=>{
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    this.getUsers();
                });
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
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //执行添加用户操作
                        console.log(this.ruleForm);
                        axios.post("http://localhost:8181/user/addUser",this.ruleForm)
                            .then((resp) => {
                                //请求成功回调函数
                                if(resp.data){
                                    //关闭弹窗
                                    this.dialogFormVisible = false;//设置嵌入对话框可见
                                    //提示
                                    this.$message({
                                        message: '修改成功',
                                        type: 'success'
                                    });
                                }
                            })
                        ;

                    } else {
                        this.$message.error('添加用户失败,请重试');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            closeDialog(done){//关闭弹窗触发事件
                this.getUsers();
                this.resetForm("ruleForm");
                done();
            }

        },

        data() {
            return {
                tableData: [],  //表格数据
                pageSize : 4,  //每页条数
                currPage : 1,  //当前页数
                totalSize : 1, //总条数
                dialogFormVisible: false,

                ruleForm: {
                    id : '',
                    address: '',
                    name: '',
                    sex: '',
                },
                rules: {
                    name: [
                        {required: true, message: '请输入用户名称', trigger: 'blur'},
                        {min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur'}
                    ],
                    pwd: [
                        { required: true, message: '请输入密码',validator: validatePass, trigger: 'blur'}
                    ],
                    checkPass: [
                        { required: true, message: '请再次输入密码',validator: validatePass2, trigger: 'blur'}
                    ],
                    sex: [
                        {required: true, message: '请选择性别', trigger: 'change'}
                    ],
                    address: [
                        {required: true, message: '请输入地址', trigger: 'change'}
                    ],
                }
            }
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
                } else if (value !== this.ruleForm.pwd) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };

        },
        created(){
            this.getUsers();
        }


    }
</script>

<style scoped>

</style>