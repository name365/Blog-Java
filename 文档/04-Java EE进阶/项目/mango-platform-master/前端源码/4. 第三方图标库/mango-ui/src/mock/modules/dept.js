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
    "data": {}
  }
  let content = []
  for(let i=0; i<3; i++) {
    let obj = {}
    obj.id   = i + 1
    obj.parentId   = 0
    obj.name = '机构部门  ' + obj.id
    obj.parentName = "顶级机构"
    obj.children = []
    content.push(obj)
  }
  for(let i=0; i<content.length; i++) {
    let parent = content[i]
    for(let j=0; j<5; j++) {
      let obj = {}
      obj.id = (i + 1) + "" + (j + 1)
      obj.parentId = parent.id
      obj.parentName = parent.name
      obj.name = '机构部门  ' + (i + 1) + "-" + (j + 1)
      parent.children.push(obj)
    }
  }
  findTreeData.data = content
  return {
    url: 'dept/findTree',
    type: 'get',
    data: findTreeData
  }
}
