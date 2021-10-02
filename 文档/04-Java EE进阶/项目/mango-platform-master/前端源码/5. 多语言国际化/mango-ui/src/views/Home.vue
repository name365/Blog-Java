<template>
  <div class="page">
    <h2>Home Page</h2>
    <li class="fa fa-home fa-lg"></li>
    <el-button type="primary" @click="testAxios()">测试Axios调用</el-button>
    <el-button type="primary" @click="getUser()">获取用户信息</el-button>
    <el-button type="primary" @click="getMenu()">获取菜单信息</el-button>
    <h3>{{$t('common.doc')}}</h3>
    <el-button type="success" @click="changeLanguage('zh_cn')">简体中文</el-button>
    <el-button type="success" @click="changeLanguage('en_us')">English</el-button>
  </div>
</template>

<script>
import axios from 'axios'
import mock from '@/mock/index.js'
export default {
  name: 'Home',
  methods: {
    // 语言切换
    changeLanguage(lang) {
      lang === '' ? 'zh_cn' : lang
      this.$i18n.locale = lang
      this.langVisible = false
    },
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