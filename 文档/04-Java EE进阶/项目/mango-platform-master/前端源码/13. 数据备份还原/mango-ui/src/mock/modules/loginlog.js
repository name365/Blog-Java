/* 
 * 登录日志模块
 */

// 分页查询
export function findPage(params) {
  let findPageData = {
    "code": 200,
    "msg": null,
    "data": {
      "pageNum": 1,
      "pageSize": 9,
      "totalSize": 88,
      "totalPages": 10,
      "content": [
        {
          "id": 1,
          "createBy": "admin",
          "createTime": "2018-09-23T11:54:16.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userName": "admin",
          "status": "login",
          "ip": "0:0:0:0:0:0:0:1"
        },
        {
          "id": 2,
          "createBy": "admin",
          "createTime": "2018-09-23T11:54:17.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userName": "admin",
          "status": "logout",
          "ip": "0:0:0:0:0:0:0:1"
        },
        {
          "id": 3,
          "createBy": "admin",
          "createTime": "2018-09-23T11:54:18.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userName": "admin",
          "status": "login",
          "ip": "0:0:0:0:0:0:0:1"
        },
        {
          "id": 4,
          "createBy": "admin",
          "createTime": "2018-09-23T11:54:20.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userName": "admin",
          "status": "logout",
          "ip": "0:0:0:0:0:0:0:1"
        },
        {
          "id": 5,
          "createBy": "admin",
          "createTime": "2018-09-23T11:54:20.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userName": "admin",
          "status": "login",
          "ip": "0:0:0:0:0:0:0:1"
        },
        {
          "id": 6,
          "createBy": "admin",
          "createTime": "2018-09-23T11:54:21.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userName": "admin",
          "status": "logout",
          "ip": "0:0:0:0:0:0:0:1"
        },
        {
          "id": 7,
          "createBy": "admin",
          "createTime": "2018-09-23T11:54:22.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userName": "admin",
          "status": "login",
          "ip": "0:0:0:0:0:0:0:1"
        },
        {
          "id": 8,
          "createBy": "admin",
          "createTime": "2018-09-23T11:54:23.000+0000",
          "lastUpdateBy": "admin",
          "lastUpdateTime": "2019-01-21T02:15:43.000+0000",
          "userName": "admin",
          "status": "login",
          "ip": "0:0:0:0:0:0:0:1"
        },
        {
          "id": 2798,
          "createBy": "admin",
          "createTime": "2019-01-21T02:15:43.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "userName": "admin",
          "status": "logout",
          "ip": "0:0:0:0:0:0:0:1"
        }
      ]
    }
  }
  return {
    url: 'loginlog/findPage',
    type: 'post',
    data: findPageData
  }
}