<template>
  <div class="notice-panel">
    <div class="header">您有 {{data.length}} 条通知</div>
    <div class="notice-content">
      <div v-for="item in data" :key="item.key" class="notice-item">
        <span class="notice-icon">
          <li :class="item.icon"></li>
        </span>  
        <span class="notice-cotent">
          {{ item.content }}
        </span>  
      </div>
    </div>
    <div class="notice-footer">查看所有通知</div>
  </div>
</template>

<script>
export default {
  name: 'NoticePanel',
  props: {
    data: {
      type: Array,
      default: () => [
        {
          key: "1",
          icon:'fa fa-envelope-o',
          content:'你修改了用户密码'
        },
        {
          key: "2",
          icon:'fa fa-music',
          content:'你修改了用户头像'
        },
        {
          key: "3",
          icon:'fa fa-edit',
          content:'今日25名新成员加入'
        },
        {
          key: "4",
          icon:'fa fa-edit',
          content:'您发表了一篇新随笔'
        }]
    }
  },
  data() {
    return {
    }
  },
  methods: {
    handleClick: function () {
      // 按钮操作处理函数
      this.$emit('click', {})
    }
  },
  mounted() {
  }
}
</script>

<style scoped>
.notice-panel {
  font-size: 15px;
  width: 250px;
  margin: -12px;
}
.header {
  padding-left: 10px;
  font-size: 14px;
  padding-top: 6px;
  padding-bottom: 6px;
}
.notice-content {
  font-size: 15px;
}
.notice-item {
  border-color: rgba(180, 190, 190, 0.8);
  border-top-width: 1px;
  border-top-style: solid;
  padding-top: 10px;
  padding-bottom: 10px;
}
.notice-item:hover {
  cursor: pointer;
  background: #b1a6a61e;
}
.notice-icon {
  padding-left: 10px;
  padding-right: 5px;
}
.notice-footer {
  font-size: 14px;
  text-align: center;
  padding-top: 10px;
  padding-bottom: 10px;
  border-color: rgba(180, 190, 190, 0.8);
  border-top-width: 1px;
  border-top-style: solid;
}
.notice-footer:hover {
  cursor: pointer;
  background: #b1a6a61e
}
</style>