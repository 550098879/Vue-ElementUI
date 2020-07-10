<template>
    <!--    只允许有一个根节点-->
    <div>
        <h3>{{msg}}</h3>

        <table id="user" border="1" cellspacing="0">
            <tr>
                <td>id</td>
                <td>name</td>
                <td>pwd</td>
                <td>sex</td>
            </tr>
            <tr v-for="(user,index) in users">
                <td v-text="user.id"></td>
                <td v-text="user.name"></td>
                <td v-text="user.pwd"></td>
                <td v-text="user.sex"></td>
            </tr>
        </table>
    </div>
</template>

<script>
//入口函数
    export default {
        name: "User",
        //自定义假数据
        data() {
            return {
                msg:'Hello Vue',
                users: '',


            }
        },

        //页面加载完成后执行的方法
        created() {
            //发送axios请求
            //let that = this;
            axios.get("http://localhost:8181/user/findAll")
            .then((response) => {  //将this传递到回调函数中
                console.log("data:",response.data);
                //that.users = response.data;
                this.users = response.data;
            });


        }

    }
</script>

<style>

    #user{
        width: 300px;
    }


</style>