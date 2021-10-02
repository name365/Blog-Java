<template>
  <div class="message-panel">
    <div class="message-header">您有 {{data.length}} 条消息</div>
    <div class="message-content">
      <div v-for="item in data" :key="item.key" class="message-item">
        <div class="message-avatar">
          <img class="avatar" :src="require('@/assets/user.png')" />
        </div>  
        <span class="sender">
          {{ item.sender }}
        </span>  
        <span class="time">
          <li class="fa fa-clock-o"></li> {{ item.time }}
        </span>  
        <div class="message-cotent">
          {{ item.content }}
        </div>  
      </div>
    </div>
    <div class="message-footer">查看所有消息</div>
  </div>
</template>

<script>
export default {
  name: 'MessagePanel',
  props: {
    data: {
      type: Array,
      default: () => [
        {
          key: "1",
          avatar:'@/assets/user.png',
          content:'你修改了用户密码',
          sender:'诸葛亮',
          time:'5分钟前'
        },
        {
          key: "2",
          avatar:'@/assets/user.png',
          content:'你修改了用户头像',
          sender:'武则天',
          time:'2小时前'
        },
        {
          key: "3",
          avatar:'@/assets/user.png',
          content:'今日25名新成员加入',
          sender:'王语嫣',
          time:'昨天'
        },
        {
          key: "4",
          avatar:'@/assets/user.png',
          content:'您发表了一篇新随笔',
          sender:'苍井空',
          time:'昨天'
        },
        {
          key: "5",
          avatar:'@/assets/user.png',
          content:'您发表了一篇新随笔',
          sender:'上官婉儿',
          time:'前天'
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
.message-panel {
  font-size: 15px;
  width: 300px;
  margin: -12px;
}
.message-header {
  padding-left: 10px;
  font-size: 14px;
  padding-top: 6px;
  padding-bottom: 6px;
}
.message-content {
  font-size: 15px;
}
.message-item {
  border-color: rgba(180, 190, 190, 0.8);
  border-top-width: 1px;
  border-top-style: solid;
  padding-top: 10px;
  padding-bottom: 10px;
}
.message-item:hover {
  cursor: pointer;
  background: #b1a6a61e;
}
.message-avatar {
  padding-left: 10px;
}
.avatar {
  margin-right: 10px;
}
.sender {
  font-size: 16px;
  font-weight:bold;
}
.time {
  font-size: 12px;
  float: right;
  padding-right: 10px;
}
.message-footer {
  font-size: 14px;
  text-align: center;
  padding-top: 10px;
  padding-bottom: 10px;
  border-color: rgba(180, 190, 190, 0.8);
  border-top-width: 1px;
  border-top-style: solid;
}
.message-footer:hover {
  cursor: pointer;
  background: #b1a6a61e;
}
.avatar {
  width: 40px;
  height: 40px;
  border-radius: 90px;
  float: left;
}
</style>