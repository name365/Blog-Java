package com.github.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.github.pojo.Role;
import com.github.pojo.User;
import com.github.service.role.RoleService;
import com.github.service.role.RoleServiceImpl;
import com.github.service.user.UserService;
import com.github.service.user.UserServiceImpl;
import com.github.util.Constants;
import com.github.util.PageSupport;
import com.mysql.jdbc.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        System.out.println("method----> " + method);

        if(method != null && method.equals("savepwd")){
            this.updatePwd(request, response);
        } else if(method.equals("pwdmodify") && method!=null){
            this.pwdModify(request,response);
        } else if(method != null && method.equals("query")) {
            this.query(request,response);
        } else if(method != null && method.equals("add")){
            this.add(request, response);
        } else if(method != null && method.equals("getrolelist")){
            this.getRoleList(request, response);
        } else if(method != null && method.equals("ucexist")){
            this.userCodeExist(request, response);
        } else if(method != null && method.equals("deluser")){
            this.delUser(request, response);
        } else if(method != null && method.equals("view")){
            this.getUserById(request, response,"userview.jsp");
        } else if(method != null && method.equals("modify")){
            this.getUserById(request, response,"usermodify.jsp");
        } else if(method != null && method.equals("modifyexe")){
            this.modify(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        System.out.println("method----> " + method);

        if(method != null && method.equals("savepwd")){
            this.updatePwd(request, response);
        } else if(method.equals("pwdmodify") && method!=null){
            this.pwdModify(request,response);
        } else if(method != null && method.equals("query")) {
            this.query(request,response);
        } else if(method != null && method.equals("add")){
            this.add(request, response);
        } else if(method != null && method.equals("getrolelist")){
            this.getRoleList(request, response);
        } else if(method != null && method.equals("ucexist")){
            this.userCodeExist(request, response);
        } else if(method != null && method.equals("deluser")){
            this.delUser(request, response);
        } else if(method != null && method.equals("view")){
            this.getUserById(request, response,"userview.jsp");
        } else if(method != null && method.equals("modify")){
            this.getUserById(request, response,"usermodify.jsp");
        } else if(method != null && method.equals("modifyexe")){
            this.modify(request, response);
        }
    }

    // 修改密码
    public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从Session中取ID
        Object o = request.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = request.getParameter("newpassword");

        System.out.println("UserServlet3:"+newpassword);

        boolean flag = false;

        if(o != null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User)o).getId(),newpassword);
            if(flag){
                request.setAttribute(Constants.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
                // 密码修改成功，session注销
                request.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
            }
        }else{
            request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
        }
        request.getRequestDispatcher("pwdmodify.jsp").forward(request, response);
    }

    // 验证旧密码,session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 从Session中取ID
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        Map<String, String> resultMap = new HashMap<String, String>();

        // session过期
        if(null == o ){
            resultMap.put("result", "sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){
            // 旧密码输入为空
            resultMap.put("result", "error");
        }else{
            String sessionPwd = ((User)o).getUserPassword();
            if(oldpassword.equals(sessionPwd)){
                resultMap.put("result", "true");
            }else{
                // 旧密码输入不正确
                resultMap.put("result", "false");
            }
        }

        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        // JSONArray 阿里巴巴的JSON工具类
        outWriter.write(JSONArray.toJSONString(resultMap));
        outWriter.flush();
        outWriter.close();

    }

    // 用户管理
    public void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 查询用户列表
        String queryUserName = request.getParameter("queryname");
        String temp = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");
        int queryUserRole = 0;
        UserService userService = new UserServiceImpl();
        List<User> userList = null;

        // 设置页面容量
        int pageSize = Constants.pageSize;

        // 当前页码
        int currentPageNo = 1;
        /**
         * http://localhost:8090/SMBMS/userlist.do
         * ----queryUserName --NULL
         * http://localhost:8090/SMBMS/userlist.do?queryname=
         * --queryUserName ---""
         */
        System.out.println("queryUserName servlet-------->"+queryUserName);
        System.out.println("queryUserRole servlet-------->"+queryUserRole);
        System.out.println("query pageIndex----------> " + pageIndex);

        if(queryUserName == null){
            queryUserName = "";
        }
        if(temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);
        }

        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                response.sendRedirect("error.jsp");
            }
        }

        // 获取用户总数
        int totalCount	= userService.getUserCount(queryUserName,queryUserRole);

        // 总页数
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

        // 控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }


        userList = userService.getUserList(queryUserName,queryUserRole,currentPageNo, pageSize);
        request.setAttribute("userList", userList);
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        request.setAttribute("roleList", roleList);
        request.setAttribute("queryUserName", queryUserName);
        request.setAttribute("queryUserRole", queryUserRole);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
    }

    // 增加用户
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("add()================");
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userRole = request.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setAddress(address);
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setGender(Integer.valueOf(gender));
        user.setPhone(phone);
        user.setUserRole(Integer.valueOf(userRole));
        user.setCreationDate(new Date());
        user.setCreatedBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());

        UserService userService = new UserServiceImpl();
        if(userService.add(user)){
            response.sendRedirect(request.getContextPath()+"/jsp/user.do?method=query");
        }else{
            request.getRequestDispatcher("useradd.jsp").forward(request, response);
        }

    }

    // 删除用户
    public void delUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("uid");
        Integer delId = 0;
        try{
            delId = Integer.parseInt(id);
        }catch (Exception e) {
            delId = 0;
        }
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(delId <= 0){
            resultMap.put("delResult", "notexist");
        }else{
            UserService userService = new UserServiceImpl();
            if(userService.deleteUserById(delId)){
                resultMap.put("delResult", "true");
            }else{
                resultMap.put("delResult", "false");
            }
        }

        // 把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    // 修改用户信息
    public void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("uid");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userRole = request.getParameter("userRole");

        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setUserName(userName);
        user.setGender(Integer.valueOf(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        user.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date());

        UserService userService = new UserServiceImpl();
        if(userService.modify(user)){
            response.sendRedirect(request.getContextPath()+"/jsp/user.do?method=query");
        }else{
            request.getRequestDispatcher("usermodify.jsp").forward(request, response);
        }

    }

    // 通过userId查询user
    public void getUserById(HttpServletRequest request, HttpServletResponse response,String url)
            throws ServletException, IOException {

        String id = request.getParameter("uid");
        if(!StringUtils.isNullOrEmpty(id)){
            // 调用后台方法得到user对象
            UserService userService = new UserServiceImpl();
            User user = userService.getUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // 根据userCode查询出User
    private void userCodeExist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 判断用户账号是否可用
        String userCode = request.getParameter("userCode");

        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(StringUtils.isNullOrEmpty(userCode)){
            // userCode == null || userCode.equals("")
            resultMap.put("userCode", "exist");
        }else{
            UserService userService = new UserServiceImpl();
            User user = userService.selectUserCodeExist(userCode);
            if(null != user){
                resultMap.put("userCode","exist");
            }else{
                resultMap.put("userCode", "notexist");
            }
        }

        // 把resultMap转为json字符串以json的形式输出
        // 配置上下文的输出类型
        response.setContentType("application/json");
        // 从response对象中获取往外输出的writer对象
        PrintWriter outPrintWriter = response.getWriter();
        // 把resultMap转为json字符串 输出
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush(); // 刷新
        outPrintWriter.close(); // 关闭流
    }

    private void getRoleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        // 把roleList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(roleList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

}