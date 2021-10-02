/* 
 * 系统登录模块
 */

// 登录接口
export function login() {
  const loginData = {
    "code": 200,
    "msg": null,
    "data": {
      "authorities": [],
      "details": {
        "remoteAddress": "0:0:0:0:0:0:0:1",
        "sessionId": "E9E774A8EB4405B25692D84B4521CB45"
      },
      "authenticated": false,
      "principal": "admin",
      "credentials": "admin",
      "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0NzkzMTYxOCwiY3JlYXRlZCI6MTU0Nzg4ODQxODgyMiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6InN5czptZW51OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5Ijoic3lzOmRpY3Q6ZWRpdCJ9LHsiYXV0aG9yaXR5Ijoic3lzOmRpY3Q6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJzeXM6Y29uZmlnOmFkZCJ9LHsiYXV0aG9yaXR5Ijoic3lzOm1lbnU6YWRkIn0seyJhdXRob3JpdHkiOiJzeXM6dXNlcjphZGQifSx7ImF1dGhvcml0eSI6InN5czpkZXB0OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5Ijoic3lzOnJvbGU6ZWRpdCJ9LHsiYXV0aG9yaXR5Ijoic3lzOnJvbGU6dmlldyJ9LHsiYXV0aG9yaXR5Ijoic3lzOmRpY3Q6dmlldyJ9LHsiYXV0aG9yaXR5Ijoic3lzOnVzZXI6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJzeXM6ZGVwdDp2aWV3In0seyJhdXRob3JpdHkiOiJzeXM6bWVudTp2aWV3In0seyJhdXRob3JpdHkiOiJzeXM6ZGljdDphZGQifSx7ImF1dGhvcml0eSI6InN5czpyb2xlOmFkZCJ9LHsiYXV0aG9yaXR5Ijoic3lzOnVzZXI6dmlldyJ9LHsiYXV0aG9yaXR5Ijoic3lzOmRlcHQ6ZWRpdCJ9LHsiYXV0aG9yaXR5Ijoic3lzOmNvbmZpZzplZGl0In0seyJhdXRob3JpdHkiOiJzeXM6bG9naW5sb2c6dmlldyJ9LHsiYXV0aG9yaXR5Ijoic3lzOnVzZXI6ZWRpdCJ9LHsiYXV0aG9yaXR5Ijoic3lzOmNvbmZpZzp2aWV3In0seyJhdXRob3JpdHkiOiJzeXM6Y29uZmlnOmRlbGV0ZSJ9LHsiYXV0aG9yaXR5Ijoic3lzOmRlcHQ6YWRkIn0seyJhdXRob3JpdHkiOiJzeXM6cm9sZTpkZWxldGUifSx7ImF1dGhvcml0eSI6InN5czptZW51OmVkaXQifV19.Lw2qb2BJHwmiVMHS_vbaLf7vnTT6frr7vTS2-nJ1Lo0uOduqK6nPtBtgEka-fH7ow-s5n7OH1WZkUvH0PN6oyA",
      "name": "admin"
    }
  }
  return {
    url: 'login',
    type: 'post',
    data: loginData
  }
}
// 登出接口
export function logout() {
  const logoutData = {
    "code": 200,
    "msg": null,
    "data": {
    }
  }
  return {
    url: 'logout',
    type: 'get',
    data: logoutData
  }
}