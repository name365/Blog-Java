/* 
 * 用户管理模块
 */

// 保存
export function save() {
  return {
    url: 'user/save',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 批量删除
export function batchDelete() {
  return {
    url: 'user/delete',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 分页查询
export function findPage(params) {
  let findPageData = {"code":200,"msg":null,"data":{"pageNum":1,"pageSize":9,"totalSize":12,"totalPages":2,"content":[{"id":1,"createBy":"admin","createTime":"2018-08-14T03:11:11.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2018-08-14T03:11:11.000+0000","name":"admin","nickName":"超管","avatar":null,"password":"bd1718f058d8a02468134432b8656a86","salt":"YzcmCZNvbXocrsz9dm8e","email":"admin@qq.com","mobile":"13612345678","status":1,"deptId":4,"delFlag":0,"deptName":"上海分公司","roleNames":"超级管理员","userRoles":[{"id":1,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":1,"roleId":1}]},{"id":22,"createBy":"admin","createTime":"2018-09-23T11:43:00.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2019-01-10T03:41:13.000+0000","name":"liubei","nickName":"刘备","avatar":null,"password":"fd80ebd493a655608dc893a9f897d845","salt":"YzcmCZNvbXocrsz9dm8e","email":"test@qq.com","mobile":"13889700023","status":1,"deptId":7,"delFlag":0,"deptName":"技术部","roleNames":"项目经理","userRoles":[{"id":80,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":22,"roleId":2}]},{"id":23,"createBy":"admin","createTime":"2018-09-23T11:43:44.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2018-09-23T11:43:52.000+0000","name":"zhaoyun","nickName":"赵云","avatar":null,"password":"fd80ebd493a655608dc893a9f897d845","salt":"YzcmCZNvbXocrsz9dm8e","email":"test@qq.com","mobile":"13889700023","status":1,"deptId":7,"delFlag":0,"deptName":"技术部","roleNames":"开发人员","userRoles":[{"id":81,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":23,"roleId":3}]},{"id":24,"createBy":"admin","createTime":"2018-09-23T11:44:23.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2018-09-23T11:44:29.000+0000","name":"zhugeliang","nickName":"诸葛亮","avatar":null,"password":"fd80ebd493a655608dc893a9f897d845","salt":"YzcmCZNvbXocrsz9dm8e","email":"test@qq.com","mobile":"13889700023","status":7,"deptId":11,"delFlag":0,"deptName":"市场部","roleNames":"测试人员","userRoles":[{"id":82,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":24,"roleId":4}]},{"id":25,"createBy":"admin","createTime":"2018-09-23T11:45:32.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2019-01-10T09:59:14.000+0000","name":"caocao","nickName":"曹操","avatar":null,"password":"fd80ebd493a655608dc893a9f897d845","salt":"YzcmCZNvbXocrsz9dm8e","email":"test@qq.com","mobile":"13889700023","status":1,"deptId":8,"delFlag":0,"deptName":"技术部","roleNames":"项目经理","userRoles":[{"id":74,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":25,"roleId":8},{"id":75,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":25,"roleId":2}]},{"id":26,"createBy":"admin","createTime":"2018-09-23T11:45:48.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2018-09-23T11:45:57.000+0000","name":"dianwei","nickName":"典韦","avatar":null,"password":"fd80ebd493a655608dc893a9f897d845","salt":"YzcmCZNvbXocrsz9dm8e","email":"test@qq.com","mobile":"13889700023","status":1,"deptId":10,"delFlag":0,"deptName":"市场部","roleNames":"开发人员","userRoles":[{"id":83,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":26,"roleId":3}]},{"id":27,"createBy":"admin","createTime":"2018-09-23T11:46:09.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2018-09-23T11:46:17.000+0000","name":"xiahoudun","nickName":"夏侯惇","avatar":null,"password":"fd80ebd493a655608dc893a9f897d845","salt":"YzcmCZNvbXocrsz9dm8e","email":"test@qq.com","mobile":"13889700023","status":1,"deptId":8,"delFlag":0,"deptName":"技术部","roleNames":"开发人员","userRoles":[{"id":87,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":27,"roleId":3}]},{"id":28,"createBy":"admin","createTime":"2018-09-23T11:46:38.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2018-11-04T07:33:17.000+0000","name":"xunyu","nickName":"荀彧","avatar":null,"password":"fd80ebd493a655608dc893a9f897d845","salt":"YzcmCZNvbXocrsz9dm8e","email":"test@qq.com","mobile":"13889700023","status":1,"deptId":10,"delFlag":0,"deptName":"市场部","roleNames":"测试人员","userRoles":[{"id":86,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":28,"roleId":4}]},{"id":29,"createBy":"admin","createTime":"2018-09-23T11:46:54.000+0000","lastUpdateBy":"admin","lastUpdateTime":"2018-09-23T11:47:03.000+0000","name":"sunquan","nickName":"孙权","avatar":null,"password":"fd80ebd493a655608dc893a9f897d845","salt":"YzcmCZNvbXocrsz9dm8e","email":"test@qq.com","mobile":"13889700023","status":1,"deptId":10,"delFlag":0,"deptName":"市场部","roleNames":"项目经理","userRoles":[{"id":85,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"userId":29,"roleId":2}]}]}}
  return {
    url: 'user/findPage',
    type: 'post',
    data: findPageData
  }
}
// 查找用户的菜单权限标识集合
export function findPermissions() {
  let permsData = {
    "code": 200,
    "msg": null,
    "data": [
      null,
      "sys:user:view",
      "sys:menu:delete",
      "sys:dept:edit",
      "sys:dict:edit",
      "sys:dict:delete",
      "sys:menu:add",
      "sys:user:add",
      "sys:log:view",
      "sys:dept:delete",
      "sys:role:edit",
      "sys:role:view",
      "sys:dict:view",
      "sys:user:edit",
      "sys:user:delete",
      "sys:dept:view",
      "sys:dept:add",
      "sys:role:delete",
      "sys:menu:view",
      "sys:menu:edit",
      "sys:dict:add",
      "sys:role:add"
    ]
  }
  return {
    url: 'user/findPermissions',
    type: 'get',
    data: permsData
  }
}
// 根据用户名查找
export function findByName(params) {
  let findPageData = {
    "code": 200,
    "msg": null,
    "data": {
      "id": 1,
      "createBy": "admin",
      "createTime": "2018-08-14T03:11:11.000+0000",
      "lastUpdateBy": "admin",
      "lastUpdateTime": "2018-08-14T03:11:11.000+0000",
      "name": "admin",
      "nickName": "超管",
      "avatar": null,
      "password": "bd1718f058d8a02468134432b8656a86",
      "salt": "YzcmCZNvbXocrsz9dm8e",
      "email": "admin@qq.com",
      "mobile": "13612345678",
      "status": 1,
      "deptId": 4,
      "delFlag": 0,
      "deptName": "上海分公司",
      "roleNames": "超级管理员",
      "userRoles": [
        {
          "id": 1,
          "createBy": null,
          "createTime": null,
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userId": 1,
          "roleId": 1
        }
      ]
    }
  }
  return {
    url: 'user/findByName',
    type: 'get',
    data: findPageData
  }
}
// 更新用户密码
export function updatePassword(params) {
  let findPageData = {"code":200,"msg":null,"data": 1}
  return {
    url: 'user/updatePassword',
    type: 'get',
    data: findPageData
  }
}