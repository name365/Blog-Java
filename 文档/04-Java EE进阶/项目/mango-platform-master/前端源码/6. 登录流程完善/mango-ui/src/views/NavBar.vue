<template>
	<div class="menu-bar-container">
    <!-- logo -->
    <div class="logo" style="background:#14889A" :class="'menu-bar-width'"
        @click="$router.push('/')">
        <img src="@/assets/logo.png"/> <div>Mango</div>
    </div>
	</div>
</template>

<script>
export default {
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
      font-size: 25px;
      color: white;
      text-align: left;
      padding-left: 20px;
    }
  }
  .menu-bar-width {
    width: 200px;
  }
}

</style>