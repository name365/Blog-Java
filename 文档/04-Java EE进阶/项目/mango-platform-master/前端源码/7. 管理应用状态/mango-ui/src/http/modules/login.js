import axios from '../axios'

/* 
 * 系统登录模块
 */

// 登录
export const login = data => {
    return axios({
        url: 'login',
        method: 'post',
        data
    })
}

// 登出
export const logout = () => {
    return axios({
        url: 'logout',
        method: 'get'
    })
}
