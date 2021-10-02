<template>
	<div class="menu-bar-container">
    <!-- logo -->
    <div class="logo" style="background:#14889A" :class="collapse?'menu-bar-collapse-width':'menu-bar-width'"
        @click="$router.push('/')">
        <img v-if="collapse"  src="@/assets/logo.png"/> <div>{{collapse?'':appName}}</div>
    </div>
	</div>
</template>

<script>
import { mapState } from 'vuex'
export default {
    computed: {
    ...mapState({
      appName: state=>state.app.appName,
      collapse: state=>state.app.collapse,
    })
  },
  methods: {
  }
}
</script>

<style scoped lang="scss">
.menu-bar-container {
  position: fixed;
  top: 0px;
  left: 0;
  bottom: 0;
  z-index: 1020;
  .logo {
    position:absolute;
    top: 0px;
    height: 60px;   
    line-height: 60px;
    background: #545c64;
    cursor:pointer;
    img {
        width: 40px;
        height: 40px;
        border-radius: 0px;
        margin: 10px 10px 10px 10px;
        float: left;
    }
    div {
      font-size: 22px;
      color: white;
      text-align: left;
      padding-left: 20px;
    }
  }
  .menu-bar-width {
    width: 200px;
  }
  .menu-bar-collapse-width {
    width: 65px;
  }
}

</style>