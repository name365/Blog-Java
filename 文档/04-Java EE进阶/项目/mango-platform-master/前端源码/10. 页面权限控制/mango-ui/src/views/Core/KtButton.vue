<template>
  <el-button :size="size" :type="type" :icon="icon"
    :loading="loading" :disabled="!hasPerms(perms)" @click="handleClick">
    {{label}}
  </el-button>
</template>

<script>
import { hasPermission } from '@/permission/index.js'
export default {
  name: 'KtButton',
  props: {
    label: {  // 按钮显示文本
      type: String,
      default: 'Button'
    },
    icon: {  // 按钮显示图标
      type: String,
      default: ''
    },
    size: {  // 按钮尺寸
      type: String,
      default: 'mini'
    },
    type: {  // 按钮类型
      type: String,
      default: null
    },
    loading: {  // 按钮加载标识
      type: Boolean,
      default: false
    },
    disabled: {  // 按钮是否禁用
      type: Boolean,
      default: false
    },
    perms: {  // 按钮权限标识，外部使用者传入
      type: String,
      default: null
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
    }, 
    hasPerms: function (perms) {
      // 根据权限标识和外部指示状态进行权限判断
      return hasPermission(perms) & !this.disabled
    }
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>