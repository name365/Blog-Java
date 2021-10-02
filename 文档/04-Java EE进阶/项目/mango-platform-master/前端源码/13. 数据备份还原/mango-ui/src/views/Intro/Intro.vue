<template>
  <div class="page-container" style="width:99%;margin-top:15px;">
    <el-carousel :interval="3000" type="card" height="450px" class="carousel">
      <el-carousel-item class="carousel-item-intro">
        <h2>项目介绍</h2>
        <ul>
          <li>基于 Spring Boot、Spring Cloud、Vue、Element 的 Java EE 快速开发平台</li>
          <li>旨在提供一套简洁易用的解决方案，帮助用户有效降低项目开发难度和成本</li>
          <li>博客提供项目开发过程同步系列教程文章，手把手的教你如何开发同类系统</li>
        </ul>
        <div><img src="@/assets/logo.png" style="width:120px;height:120px;padding-top:15px;" /></div>
      </el-carousel-item>
      <el-carousel-item class="carousel-item-func">
        <h2>功能计划</h2>
        <ul>
          <li>✔ 系统登录：系统用户登录，系统登录认证（token方式）</li>
          <li>✔ 用户管理：新建用户，修改用户，删除用户，查询用户</li>
          <li>✔ 机构管理：新建机构，修改机构，删除机构，查询机构</li>
          <li>✔ 角色管理：新建角色，修改角色，删除角色，查询角色</li>
          <li>✔ 菜单管理：新建菜单，修改菜单，删除菜单，查询菜单</li>
          <li>✔ 系统日志：记录用户操作日志，查看系统执行日志记录</li>
          <li>✔ 数据监控：定制Druid信息，提供简洁有效的SQL监控</li>
          <li>✔ 聚合文档：定制在线文档，提供简洁美观的API文档</li>
          <li>✔ 备份还原：系统备份还原，一键恢复系统初始化数据</li>
          <li>✔ 主题切换：支持主题切换，自定主题颜色，一键换肤</li>
          <li>✔ 服务治理：集成Spring Cloud，实现全方位服务治理</li>
          <li>✔ 服务监控：集成Spring Boot Admin，实现微服务监控</li>
          <li>...</li>
        </ul>
      </el-carousel-item>
      <el-carousel-item class="carousel-item-env">
        <h2>开发环境</h2>
        <ul>
          <li>IDE : eclipse 4.6.x。     JDK : JDK 1.8.x。</li>
          <li>Maven : Maven 3.5.x。   MySQL: MySQL 5.7.x。</li>
          <li>IDE : VS Code 1.27。    Webpack：webpack 3.2.x。</li>
          <li>NODE: Node 8.9.x。      NPM : NPM 6.4.x。</li>
        </ul>
        <h2>技术选型</h2>
        <ul>
          <li>核心框架：Spring Boot 2.x。  服务治理：Spring Cloud。</li>
          <li>视图框架：Spring MVC 5.x。   持久层框架：MyBatis 3.x。</li>
          <li>数据库连接池：Druid 1.x。    安全框架：Shiro 1.4.x。</li>
          <li>前端框架：Vue.js 2.x。       页面组件：Element 2.x。</li>
          <li>状态管理：Vuex.js 2.x。      后台交互：axios 0.18.x。</li>
          <li>...</li>
        </ul>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
  export default {
    components:{
    },
    methods :{
    }
  }
</script>

<style>
  .carousel {
    padding-left: 20px;
    padding-right: 20px;
    margin-right: 20px;
  }
  .carousel h2 {
    color: #475669;
    font-size: 22px;
    opacity: 1.75;
    line-height: 100px;
    margin: 0;
  }
  .carousel ul {
    color: #475669;
    font-size: 15px;
    opacity: 1.75;
    line-height: 40px;
    margin: 0;
  }
  .carousel-item-intro h2 {
    color: #ffffff;
    font-size: 22px;
    opacity: 1.75;
    line-height: 80px;
    margin: 0;
  }
  .carousel-item-intro ul {
    color: #ffffff;
    font-size: 15px;
    opacity: 1.75;
    line-height: 65px;
    padding: 5px;
    margin: 0;
  }
   .carousel-item-func h2 {
    color: #3f393b;
    font-size: 22px;
    opacity: 1.75;
    line-height: 50px;
    margin: 0;
  }
  .carousel-item-func ul {
    color: #3f393b;
    font-size: 15px;
    opacity: 1.75;
    line-height: 30px;
    text-align: left;
    padding-left: 90px;
    margin: 0;
  }
  .carousel-item-env h2 {
    color: #475669;
    font-size: 22px;
    opacity: 1.75;
    line-height: 50px;
    margin: 0;
  }
  .carousel-item-env ul {
    color: #475669;
    font-size: 15px;
    opacity: 1.75;
    line-height: 35px;
    text-align: left;
    padding-left: 110px;
    margin: 0;
  }
  .carousel-item-intro {
    background-color: #19aaaf73;
    -webkit-border-radius: 25px;
    border-radius: 25px;
    -moz-border-radius: 15px;
    background-clip: padding-box;
    box-shadow: 0 0 25px #a3b3b965;
  }
  .carousel-item-func {
    background-color: #19aaaf73;
    -webkit-border-radius: 25px;
    border-radius: 25px;
    -moz-border-radius: 15px;
    background-clip: padding-box;
    box-shadow: 0 0 25px #a3b3b965;
  }
  .carousel-item-env {
    background-color: #19aaaf73;
    -webkit-border-radius: 25px;
    border-radius: 25px;
    -moz-border-radius: 15px;
    background-clip: padding-box;
    box-shadow: 0 0 25px #a3b3b965;
  }
  .carousel-item-intro {
    background-color: #b95e5e;
  }
  .carousel-item-func {
    background-color: #52c578;
  }
  .carousel-item-env {
    background-color: #41a7b9;
  }
</style>