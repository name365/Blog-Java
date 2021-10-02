package com.jiaxuan.shop.controller;

import com.jiaxuan.shop.common.utils.CommonUtils;
import com.jiaxuan.shop.common.utils.ResponseResult;
import com.jiaxuan.shop.pojo.SystemUser;
import com.jiaxuan.shop.service.RoleService;
import com.jiaxuan.shop.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin/system_user/springsession")
@Slf4j
public class UserSpringSessionController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private RoleService roleService;

    //TODO： 需要实现登录
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public String login(String loginName, String password, HttpSession session, Model model) {
        //实现登录
        try {
            password = CommonUtils.MD5(password);
            SystemUser systemUser = systemUserService.login(loginName, password);
            systemUser.setPassword(null);
            session.setAttribute("systemUser", systemUser);
            return "main";
        } catch (Exception e) {
            model.addAttribute("failMsg", e.getMessage());
            return "login";
        }
    }

    @RequestMapping("systemUserLogout")
    @ResponseBody
    public ResponseResult systemUserLogout(HttpSession session) {
        session.invalidate();
        return ResponseResult.success();

    }
}
