package com.github.config;

import com.github.pojo.User;
import com.github.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author subeiLY
 * @create 2022-01-31 20:45
 */
// 自定义Realm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    // 执行授权逻辑
    @Override
    protected AuthorizationInfo
    doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了=>授权逻辑PrincipalCollection");
        // 给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 添加资源的授权字符串
        // info.addStringPermission("user:add");
        // 获得当前对象
        Subject subject = SecurityUtils.getSubject();
        // 拿到User对象
        User currentUser = (User) subject.getPrincipal();
        // 设置权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    // 执行认证逻辑
    @Override
    protected AuthenticationInfo
    doGetAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
        System.out.println("执行了=>认证逻辑AuthenticationToken");
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        // 真实连接数据库
        User user =
                userService.queryUserByName(userToken.getUsername());
        if (user==null){
            // 用户名不存在
            return null; // shiro底层就会抛出 UnknownAccountException
        }
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("loginUser",user);

        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
