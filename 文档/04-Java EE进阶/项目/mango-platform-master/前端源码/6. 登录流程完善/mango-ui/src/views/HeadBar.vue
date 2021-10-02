<template> 
  <div class="headbar" style="background:#14889A" :class="'position-left'">
    <!-- 工具栏 -->
    <span class="toolbar">
      <el-menu class="el-menu-demo" background-color="#14889A" text-color="#14889A" active-text-color="#14889A" mode="horizontal">
        <el-menu-item index="1">
          <!-- 用户信息 -->
          <span class="user-info"><img :src="user.avatar" />{{user.name}}</span>
        </el-menu-item>
      </el-menu>
    </span>
  </div>
</template>

<script>
import mock from "@/mock/index"
export default {
  data() {
    return {
      user: {
        name: "Louis",
        avatar: "",
        role: "超级管理员",
        registeInfo: "注册时间：2018-12-20 "
      },
      activeIndex: '1',
      langVisible: false
    }
  },
  methods: {
    selectNavBar(key, keyPath) {
      console.log(key, keyPath)
    }
  },
  mounted() {
    var user = sessionStorage.getItem("user")
    if (user) {
      this.user.name = user
      this.user.avatar = require("@/assets/user.png")
    }
  }
}
</script>

<style scoped lang="scss">
.headbar {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 1030;
  height: 60px;
  line-height: 60px;
  border-color: rgba(180, 190, 190, 0.8);
  border-left-width: 1px;
  border-left-style: solid;
}
.navbar {
  float: left;
}
.toolbar {
  float: right;
}
.user-info {
  font-size: 20px;
  color: #fff;
  cursor: pointer;
  img {
    width: 40px;
    height: 40px;
    border-radius: 10px;
    margin: 10px 0px 10px 10px;
    float: right;
  }
}
.position-left {
  left: 200px;
}
</style>