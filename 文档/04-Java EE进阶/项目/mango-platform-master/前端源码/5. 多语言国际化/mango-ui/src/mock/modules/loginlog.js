/* 
 * 登录日志模块
 */

// 分页查询
export function findPage(params) {
  let findPageData = {
    "code": 200,
    "msg": null,
    "data": {}
  }
  let pageNum = 1
  let pageSize = 8
  if(params !== null) {
    // pageNum = params.pageNum
  }
  if(params !== null) {
    // pageSize = params.pageSize
  }
  let content = this.getContent(pageNum, pageSize)
  findPageData.data.pageNum = pageNum
  findPageData.data.pageSize = pageSize
  findPageData.data.totalSize = 50
  findPageData.data.content = content
  return {
    url: 'loginlog/findPage',
    type: 'post',
    data: findPageData
  }
}
export function getContent(pageNum, pageSize) {
  let content = []
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.id = index
    obj.userName = 'admin' + index
    if(i%2 == 0) {
      obj.status = 'login'
    } else {
      obj.status = 'logout'
    }
    obj.ip = '127.0.0.1'
    obj.remark = 'remark loginlog' + index
    if(i % 2 === 0) {

    }
    obj.createBy= 'admin'
    obj.createTime= '2018-08-14 11:11:11'
    obj.createBy= 'admin'
    obj.createTime= '2018-09-14 12:12:12'
    content.push(obj)
  }
  return content
}
