<template>
  <div class="page">
    <h2>Home Page</h2>
    <li class="fa fa-home fa-lg"></li>
    <el-button type="primary" @click="testAxios()">测试Axios调用</el-button>
    <el-button type="primary" @click="getUser()">获取用户信息</el-button>
    <el-button type="primary" @click="getMenu()">获取菜单信息</el-button>
  </div>
</template>

<script>
import axios from 'axios'
import mock from '@/mock/index.js'
export default {
  name: 'Home',
  methods: {
    testAxios() {
      axios.get('http://localhost:8080').then(res => { alert(res.data) })
    },
    getUser() {
      axios.get('http://localhost:8080/user').then(res => { alert(JSON.stringify(res.data)) })
    },
    getMenu() {
      axios.get('http://localhost:8080/menu').then(res => { alert(JSON.stringify(res.data)) })
    }
  }
}
</script>