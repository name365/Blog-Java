/* 
 * 机构管理模块
 */

// 保存
export function save() {
  return {
    url: 'dept/save',
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
    url: 'dept/delete',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
export function findDeptTree(params) {
  // 查询机构树
  let findTreeData = {
    "code": 200,
    "msg": null,
    "data": [
      {
        "id": 1,
        "createBy": "admin",
        "createTime": "2018-09-23T11:35:22.000+0000",
        "lastUpdateBy": null,
        "lastUpdateTime": null,
        "name": "轻尘集团",
        "parentId": null,
        "orderNum": 0,
        "delFlag": 0,
        "children": [
          {
            "id": 5,
            "createBy": "admin",
            "createTime": "2018-09-23T11:37:17.000+0000",
            "lastUpdateBy": null,
            "lastUpdateTime": null,
            "name": "北京分公司",
            "parentId": 1,
            "orderNum": 1,
            "delFlag": 0,
            "children": [
              {
                "id": 7,
                "createBy": "admin",
                "createTime": "2018-09-23T11:38:00.000+0000",
                "lastUpdateBy": null,
                "lastUpdateTime": null,
                "name": "技术部",
                "parentId": 5,
                "orderNum": 0,
                "delFlag": 0,
                "children": [],
                "parentName": "技术部",
                "level": 2
              },
              {
                "id": 10,
                "createBy": "admin",
                "createTime": "2018-09-23T11:38:45.000+0000",
                "lastUpdateBy": null,
                "lastUpdateTime": null,
                "name": "市场部",
                "parentId": 5,
                "orderNum": 0,
                "delFlag": 0,
                "children": [],
                "parentName": "市场部",
                "level": 2
              }
            ],
            "parentName": "北京分公司",
            "level": 1
          }
        ],
        "parentName": null,
        "level": 0
      },
      {
        "id": 2,
        "createBy": "admin",
        "createTime": "2018-09-23T11:35:55.000+0000",
        "lastUpdateBy": null,
        "lastUpdateTime": null,
        "name": "牧尘集团",
        "parentId": null,
        "orderNum": 1,
        "delFlag": 0,
        "children": [
          {
            "id": 4,
            "createBy": "admin",
            "createTime": "2018-09-23T11:37:03.000+0000",
            "lastUpdateBy": null,
            "lastUpdateTime": null,
            "name": "上海分公司",
            "parentId": 2,
            "orderNum": 0,
            "delFlag": 0,
            "children": [
              {
                "id": 8,
                "createBy": "admin",
                "createTime": "2018-09-23T11:38:10.000+0000",
                "lastUpdateBy": null,
                "lastUpdateTime": null,
                "name": "技术部",
                "parentId": 4,
                "orderNum": 0,
                "delFlag": 0,
                "children": [],
                "parentName": "技术部",
                "level": 2
              }
            ],
            "parentName": "上海分公司",
            "level": 1
          },
          {
            "id": 6,
            "createBy": "admin",
            "createTime": "2018-09-23T11:37:28.000+0000",
            "lastUpdateBy": null,
            "lastUpdateTime": null,
            "name": "北京分公司",
            "parentId": 2,
            "orderNum": 1,
            "delFlag": 0,
            "children": [
              {
                "id": 9,
                "createBy": "admin",
                "createTime": "2018-09-23T11:38:17.000+0000",
                "lastUpdateBy": null,
                "lastUpdateTime": null,
                "name": "技术部",
                "parentId": 6,
                "orderNum": 0,
                "delFlag": 0,
                "children": [],
                "parentName": "技术部",
                "level": 2
              },
              {
                "id": 11,
                "createBy": "admin",
                "createTime": "2018-09-23T11:39:01.000+0000",
                "lastUpdateBy": null,
                "lastUpdateTime": null,
                "name": "市场部",
                "parentId": 6,
                "orderNum": 0,
                "delFlag": 0,
                "children": [],
                "parentName": "市场部",
                "level": 2
              }
            ],
            "parentName": "北京分公司",
            "level": 1
          }
        ],
        "parentName": null,
        "level": 0
      },
      {
        "id": 3,
        "createBy": "admin",
        "createTime": "2018-09-23T11:36:24.000+0000",
        "lastUpdateBy": null,
        "lastUpdateTime": null,
        "name": "三国集团",
        "parentId": null,
        "orderNum": 2,
        "delFlag": 0,
        "children": [
          {
            "id": 12,
            "createBy": "admin",
            "createTime": "2018-09-23T11:40:42.000+0000",
            "lastUpdateBy": null,
            "lastUpdateTime": null,
            "name": "魏国",
            "parentId": 3,
            "orderNum": 0,
            "delFlag": 0,
            "children": [],
            "parentName": "魏国",
            "level": 1
          },
          {
            "id": 13,
            "createBy": "admin",
            "createTime": "2018-09-23T11:40:54.000+0000",
            "lastUpdateBy": null,
            "lastUpdateTime": null,
            "name": "蜀国",
            "parentId": 3,
            "orderNum": 1,
            "delFlag": 0,
            "children": [],
            "parentName": "蜀国",
            "level": 1
          },
          {
            "id": 14,
            "createBy": "admin",
            "createTime": "2018-09-23T11:41:04.000+0000",
            "lastUpdateBy": null,
            "lastUpdateTime": null,
            "name": "吴国",
            "parentId": 3,
            "orderNum": 2,
            "delFlag": 0,
            "children": [],
            "parentName": "吴国",
            "level": 1
          }
        ],
        "parentName": null,
        "level": 0
      }
    ]
  }
  return {
    url: 'dept/findTree',
    type: 'get',
    data: findTreeData
  }
}
