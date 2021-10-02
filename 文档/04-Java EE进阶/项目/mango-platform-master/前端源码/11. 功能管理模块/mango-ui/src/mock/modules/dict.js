/* 
 * 字典管理模块
 */

// 保存
export function save() {
  return {
    url: 'dict/save',
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
    url: 'dict/delete',
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
  let findPageData = {
    "code": 200,
    "msg": null,
    "data": {
      "pageNum": 1,
      "pageSize": 8,
      "totalSize": 2,
      "totalPages": 1,
      "content": [
        {
          "id": 1,
          "createBy": "admin",
          "createTime": "2018-09-23T11:52:54.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "value": "male",
          "label": "男",
          "type": "sex",
          "description": "性别",
          "sort": 0,
          "remarks": "性别",
          "delFlag": 0
        },
        {
          "id": 2,
          "createBy": "admin",
          "createTime": "2018-09-23T11:53:17.000+0000",
          "lastUpdateBy": null,
          "lastUpdateTime": null,
          "value": "female",
          "label": "女",
          "type": "sex",
          "description": "性别",
          "sort": 1,
          "remarks": "性别",
          "delFlag": 0
        }
      ]
    }
  }
  return {
    url: 'dict/findPage',
    type: 'post',
    data: findPageData
  }
}