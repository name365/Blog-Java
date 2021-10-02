<template>
  <div class="personal-panel">
    <div class="personal-desc" :style="{'background':this.$store.state.app.themeColor}">
        <div class="avatar-container">
          <img class="avatar" :src="require('@/assets/user.png')" />
        </div>  
        <div class="name-role">
          <span class="sender">{{ user.nickName }} - {{ user.roleNames }}</span>  
        </div>  
        <div class="registe-info">
          <span class="registe-info">
            <li class="fa fa-clock-o"></li>
            {{ this.dateFormat(user.createTime) }}
          </span>
        </div>  
    </div>
    <div class="personal-relation">
        <span class="relation-item">followers</span>  
        <span class="relation-item">watches</span>  
        <span class="relation-item">friends</span>
    </div>
    <div class="main-operation">
        <span class="main-operation-item" @click="openPersonCenter">
          <el-button size="small" icon="fa fa-male" > 个人中心</el-button>
        </span>    
        <span class="main-operation-item" @click="openupdatePasswordDialog">
          <el-button size="small" icon="fa fa-key"> 修改密码</el-button>
        </span>    
    </div>
    <div class="other-operation">
        <div class="other-operation-item" @click="clearCache">
          <li class="fa fa-eraser"></li>
          清除缓存
        </div>    
        <div class="other-operation-item" @click="openOnlinePage">
          <li class="fa fa-user"></li>
          在线人数 {{onlineUser}}
        </div>    
        <div class="other-operation-item">
          <li class="fa fa-bell"></li>
          访问次数 {{accessTimes}}
        </div>    
        <div class="other-operation-item">
          <li class="fa fa-undo"></li>
          {{$t("common.backupRestore")}}
        </div>    
    </div>
    <div class="personal-footer" @click="logout">
      <li class="fa fa-sign-out"></li>
      {{$t("common.logout")}}
    </div>
    <!--修改密码界面-->
    <el-dialog title="修改密码" width="40%" :visible.sync="updatePwdDialogVisible" :close-on-click-modal="false" :modal="false">
      <el-form :model="updatePwdDataForm" label-width="100px" :rules="updatePwdDataFormRules" ref="updatePwdDataForm" :size="size">
        <el-form-item label="原密码" prop="password">
          <el-input v-model="updatePwdDataForm.password" type="password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="updatePwdDataForm.newPassword" type="password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="comfirmPassword">
          <el-input v-model="updatePwdDataForm.comfirmPassword" type="password" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="updatePwdDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="updatePassword" :loading="updatePwdLoading">{{$t('action.comfirm')}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { format } from "@/utils/datetime"
export default {
  name: 'PersonalPanel',
  components:{
  },
  props: {
    user: {
      type: Object,
      default: {
        nickName: "admin",
        avatar: "@/assets/user.png",
        role: "超级管理员",
        registeInfo: "注册时间：2018-12-25 "
      }
    }
  },
  data() {
    return {
      onlineUser: 0,
      accessTimes: 0,
      size: 'small',
      updatePwdDialogVisible: false,
      updatePwdLoading: false,
      updatePwdDataForm: {
				password: '',
				newPassword: '',
				comfirmPassword: ''
			},
      updatePwdDataFormRules: {
				password: [
					{ required: true, message: '请输入原密码', trigger: 'blur' }
        ],
        newPassword: [
					{ required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        comfirmPassword: [
					{ required: true, message: '请确认密码', trigger: 'blur' }
				]
			},
    }
  },
  methods: {
    // 打开个人中心
		openPersonCenter: function () {
			alert('待开发')
		},
    // 打开修改密码对话框
		openupdatePasswordDialog: function () {
			this.updatePwdDialogVisible = true
		},
    // 修改密码
		updatePassword: function () {
			this.$refs.updatePwdDataForm.validate((valid) => {
				if (valid) {
          if(this.updatePwdDataForm.newPassword != this.updatePwdDataForm.comfirmPassword) {
            this.$message({message: '新密码与确认新密码不一致', type: 'error'})
            return
          }
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.updatePwdLoading = true
						let params = {password:this.updatePwdDataForm.password, newPassword:this.updatePwdDataForm.newPassword}
						this.$api.user.updatePassword(params).then((res) => {
							this.updatePwdLoading = false
							if(res.code == 200) {
								this.$message({ message: '操作成功', type: 'success' })
                this.$refs['updatePwdDataForm'].resetFields()
                this.logoutApi()
							} else {
                this.$message({message: '操作失败, ' + res.msg, type: 'error'})
							}
              this.updatePwdDialogVisible = false
						})
					})
				}
			})
		},
    // 退出登录
    logout() {
      this.$confirm("确认退出吗?", "提示", {
        type: "warning"
      })
      .then(() => {
        this.logoutApi()
      })
      .catch(() => {})
    },
    // 清除缓存并退出登录
    clearCache() {
      this.$confirm("确认清除缓存并退出登录吗?", "提示", {
        type: "warning"
      })
      .then(() => {
        this.deleteCookie('token')// 清空Cookie里的token
        this.logoutApi()
      })
      .catch(() => {})
    },
    logoutApi() {
      sessionStorage.removeItem("user")
        this.$router.push("/login")
        this.$api.login.logout().then((res) => {
          }).catch(function(res) {
        })
    },
    // 清除Cookie
    deleteCookie(name){     
         var myDate = new Date()   
         myDate.setTime(-1000) // 设置过期时间     
         document.cookie = name+"=''; expires="+myDate.toGMTString();              
    },    
    // 获取在线用户数
		countOnlineUser() {
      let pageRequest = { pageNum: 1, pageSize: 10000000 }
			pageRequest.params = [{name:'status', value:'online'}]
			this.$api.loginlog.findPage(pageRequest).then((res) => {
				this.onlineUser = res.data.content.length
			})
		},
    // 获取访问次数
		countAccessTimes() {
      let pageRequest = { pageNum: 1, pageSize: 10000000 }
			pageRequest.params = [{name:'status', value:'login'}]
			this.$api.loginlog.findPage(pageRequest).then((res) => {
				this.accessTimes = res.data.content.length + 1
			})
		},
    openOnlinePage() {
      // 通过菜单URL跳转至指定路由
      this.$router.push('/sys/online')
    },
		// 时间格式化
    dateFormat(date){
      return format(date)
    }
  },
  mounted() {
    this.countOnlineUser()
    this.countAccessTimes()
  }
}
</script>

<style scoped>
.personal-panel {
  font-size: 14px;
  width: 280px;
  text-align: center;
  border-color: rgba(180, 190, 190, 0.2);
  border-width: 1px;
  border-style: solid;
  background: rgba(182, 172, 172, 0.1);
  margin: -14px;
}
.personal-desc {
  padding: 15px;
  color: #fff;
}
.avatar {
  width: 80px;
  height: 80px;
  border-radius: 90px;
}
.name-role {
  font-size: 16px;
  padding: 5px;
}
.personal-relation {
  font-size: 16px;
  padding: 12px;
  margin-right: 1px;
  background: rgba(200, 209, 204, 0.3);
}
.relation-item {
  padding: 12px;
}
.relation-item:hover {
  cursor: pointer;
  color: rgb(19, 138, 156);
}
.main-operation {
  padding: 8px;
  margin-right: 1px;
  /* background: rgba(175, 182, 179, 0.3); */
  border-color: rgba(201, 206, 206, 0.2);
  border-top-width: 1px;
  border-top-style: solid;
}
.main-operation-item {
  margin: 15px;
}
.other-operation {
  padding: 15px;
  margin-right: 1px;
  text-align: left;
  border-color: rgba(180, 190, 190, 0.2);
  border-top-width: 1px;
  border-top-style: solid;
}
.other-operation-item {
  padding: 12px;
}
.other-operation-item:hover {
  cursor: pointer;
  background: #9e94941e;
  color: rgb(19, 138, 156);
}
.personal-footer {
  margin-right: 1px;
  font-size: 14px;
  text-align: center;
  padding-top: 10px;
  padding-bottom: 10px;
  border-color: rgba(180, 190, 190, 0.2);
  border-top-width: 1px;
  border-top-style: solid;
}
.personal-footer:hover {
  cursor: pointer;
  color: rgb(19, 138, 156);
  background: #b1a6a61e;
}
</style>