<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-openeds="['1']">
          <el-submenu index="1">

            <template slot="title">
              <i class="el-icon-caret-right"></i>用户管理
            </template>

            <el-menu-item-group>
              <el-menu-item index="1-1">
                <!--name:传组件名， params：传递参数， 需要对象： v-bind-->
                <router-link :to="{name:'UserProfile',params:{id:1,name:'subei'}}">个人信息</router-link>
<!--                <router-link to="/user/profile">个人信息</router-link>-->
              </el-menu-item>

              <el-menu-item index="1-2">
                <router-link to="/user/list">用户列表</router-link>
              </el-menu-item>

              <el-menu-item index="1-3">
                <router-link to="/goHome">回到首页</router-link>
              </el-menu-item>

            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-caret-right"></i>内容管理
            </template>
            <el-menu-item-group>
              <el-menu-item index="2-1">人员管理</el-menu-item>
              <el-menu-item index="2-2">财务列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>

            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <el-main>
          <router-view/>
        </el-main>
      </el-container>

    </el-container>
  </div>
</template>

<script>
export default {
  name: "Main"
}
</script>

<style scoped lang="scss">
.el-header {
  background-color: #1cbeff;
  color: #f82d2d;
  line-height: 60px;
}

.el-aside {
  color: #f82d2d;
}
</style>
