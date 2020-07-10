<template>
  <div id="app">
    <el-container style="height: 600px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
<!--        <el-menu :default-openeds="['1', '3']" :default-active="'1-1'">-->
<!--          <el-submenu index="1">-->
<!--            <template slot="title"><i class="el-icon-message"></i>导航一</template>-->
<!--            <el-menu-item-group>-->
<!--              <template slot="title">分组一</template>-->
<!--              <el-menu-item index="1-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="1-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--            <el-menu-item-group title="分组2">-->
<!--              <el-menu-item index="1-3">选项3</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--            <el-submenu index="1-4">-->
<!--              <template slot="title">-->
<!--                <i class="el-icon-setting"></i> 选项4-->
<!--              </template>-->
<!--              <el-menu-item index="1-4-1">选项4-1</el-menu-item>-->
<!--              <el-menu-item index="1-4-2">选项4-2</el-menu-item>-->
<!--            </el-submenu>-->
<!--          </el-submenu>-->
<!--        </el-menu>-->

        <!--动态读取左侧菜单项-->
        <el-menu :default-openeds="['0']" :default-active="'0-0'">
          <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''">
            <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
            <el-menu-item v-for="(item2,index2) in item.children" :index="index+'-'+index2">{{item2.name}}</el-menu-item>
          </el-submenu>
        </el-menu>

      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>王小虎</span>
        </el-header>

        <el-main>
          <!--路由过来的组件显示-->
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>

  </div>
</template>

<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item)
      }
    }
  };
</script>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
