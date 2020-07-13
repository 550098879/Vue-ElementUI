<template>
    <div>
        <el-form :model="ruleForm" status-icon :rules="rules" style="width: 60%"
                 ref="ruleForm" label-width="100px" class="demo-ruleForm">
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

            <el-form-item label="密码" prop="pwd">
                <el-input type="password" v-model="ruleForm.pwd" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input v-model.number="ruleForm.address"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
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
            return {
                ruleForm: {
                    pwd: '',
                    checkPass: '',
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
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //执行添加用户操作
                        console.log(this.ruleForm);
                        axios.post("http://localhost:8181/user/addUser",this.ruleForm)
                        .then((resp) => {
                            //请求成功回调函数
                            if(resp.data){
                               // 路由跳转
                                this.$alert(this.ruleForm.name+'  添加成功', '提示信息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        this.$router.push("/UserManage");
                                    }
                                });
                               //重置表单
                                this.resetForm('ruleForm');
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
            }
        }
    }
</script>

<style scoped>

</style>