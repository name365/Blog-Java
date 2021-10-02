package com.jiaxuan.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxuan.shop.params.SystemUserParam;
import com.jiaxuan.shop.common.constant.PaginationConstant;
import com.jiaxuan.shop.common.utils.CommonUtils;
import com.jiaxuan.shop.common.utils.ResponseResult;
import com.jiaxuan.shop.pojo.Role;
import com.jiaxuan.shop.pojo.SystemUser;
import com.jiaxuan.shop.service.RoleService;
import com.jiaxuan.shop.service.SystemUserService;
import com.jiaxuan.shop.vo.SystemUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: jx
 * Date: 2019-03-09 9:36
 * Description: 后台系统用户登录
 */
@Controller
@RequestMapping("/admin/system_user/manager")
@Slf4j
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private RoleService roleService;

    //TODO： 需要实现登录
    @RequestMapping("login")
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
    public ResponseResult systemUserLogout(HttpSession session){
        session.invalidate();
        return ResponseResult.success();
    }

    /**
     * 功能描述: 获取所有系统用户类表，分页查询
     *
     * @return java.lang.String
     * @Author jx
     * @Date 21:35 2019/03/11
     * @Param [pageNum, model]
     */
    @RequestMapping("getAllSystemUsers")
    public String getAllSystemUsers(Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<SystemUser> systemUserList = systemUserService.findAllSytemUsers();

        PageInfo<SystemUser> pageInfo = new PageInfo<>(systemUserList);

        model.addAttribute("pageInfo", pageInfo);
        return "systemUserManager";
    }

    /**
     * 功能描述: 通过多个条件查询系统用户的列表
     * @return java.lang.String
     * @Author jx
     * @Date 21:36 2019/03/11
     * @Param [systemUserParam, pageNum, model]
     */
    @RequestMapping("findSystemUserByParams")
    public String findSystemUserByParams(SystemUserParam systemUserParam, Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<SystemUser> systemUserList = systemUserService.findSystemUsersByParams(systemUserParam);
        //数据封装分页
        PageInfo<SystemUser> pageInfo = new PageInfo<>(systemUserList);

        //实现数据的回显，将数据放到 model中
        model.addAttribute("params", systemUserParam);
        model.addAttribute("pageInfo", pageInfo);

        return "systemUserManager";
    }

    /**
     * 功能描述: 页面初始化，把角色的数据加载到页面中
     *
     * @return java.util.List<Role>
     * @Author jx
     * @Date 11:45 2019/03/11
     * @Param []
     */
    @ModelAttribute("roles")
    public List<Role> loadRoles() {
        List<Role> roles = roleService.findAllRoles();
        return roles;
    }

    /**
     * 功能描述: 添加一个系统用户
     *
     * @return ResponseResult
     * @Author jx
     * @Date 21:37 2019/03/11
     * @Param [systemUserVo]
     */
    @RequestMapping("addSystemUser")
    @ResponseBody
    public ResponseResult addSystemUser(SystemUserVo systemUserVo) {

        try {
            String md5Pwd = CommonUtils.MD5(systemUserVo.getPassword());
            systemUserVo.setPassword(md5Pwd);
        } catch (Exception e) {
            log.info("加密失败");
            return ResponseResult.fail("用户添加失败");
        }

        int rows = systemUserService.addSystemUser(systemUserVo);
        if (rows >= 1) {
            return ResponseResult.success("用户添加成功");
        } else {
            return ResponseResult.fail("用户添加失败");
        }
    }

    /**
     * 功能描述: 修改系统用户的状态，启用或禁用账户
     *
     * @return ResponseResult
     * @Author jx
     * @Date 21:37 2019/03/11
     * @Param [id]
     */
    @RequestMapping("modifySystemUserStatus")
    @ResponseBody
    public ResponseResult modifySystemUserStatus(int id) {
        int rows = systemUserService.modifySystemUserStatus(id);
        if (rows >= 1) {
            return ResponseResult.success("操作成功");
        } else {
            return ResponseResult.success("操作失败");
        }
    }

    /**
     * 功能描述: 查询某个系统用户信息
     *
     * @return ResponseResult
     * @Author jx
     * @Date 21:38 2019/03/11
     * @Param [id]
     */
    @RequestMapping("findSystemUser")
    @ResponseBody
    public ResponseResult findSystemUser(int id) {
        SystemUser systemUser = systemUserService.findSystemUserById(id);
        if (systemUser != null) {
            return ResponseResult.success(systemUser);
        } else {
            return ResponseResult.fail("该系统用户不存在");
        }
    }

    /**
     * 功能描述: 更新系统用户信息
     *
     * @return java.lang.String
     * @Author jx
     * @Date 21:38 2019/03/11
     * @Param [systemUserVo, pageNum, model]
     */
    @RequestMapping("modifySystemUser")
    public String modifySystemUser(SystemUserVo systemUserVo, Integer pageNum, Model model) {
        int rows = systemUserService.modifySystemUser(systemUserVo);
        if (rows >= 1) {
            model.addAttribute("successMsg", "修改成功");
        } else {
            model.addAttribute("failMsg", "修改失败");
        }

        return "forward:getAllSystemUsers?pageNum=" + pageNum;
    }

    /**
     * 功能描述: 校验登录账户名是否可用
     *
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author jx
     * @Date 21:38 2019/03/11
     * @Param [loginName]
     */
    @RequestMapping("checkSystemUserLoginName")
    @ResponseBody
    public Map<String, Object> checkSystemUserLoginName(String loginName) {
        SystemUser systemUser = systemUserService.findSystemUserByLoginName(loginName);
        Map<String, Object> map = new HashMap<>();
        if (systemUser != null) {
            map.put("valid", false);
            map.put("message", "该账号已经存在");
        } else {
            map.put("valid", true);
        }
        return map;
    }

    /**
     *功能描述: session 超时
     * @Author jx
     * @Date 11:26 2019/04/07
     * @Param [attributes]
     * @return java.lang.String
     */
    @RequestMapping("sessionTimeOut")
    public String sessionTimeOut(RedirectAttributes attributes){
        attributes.addFlashAttribute("sessionTimeOut","session超时");
        return "redirect:/admin/system_user/manager/login";
    }
}
