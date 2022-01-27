package com.github.controller;

import com.github.dao.DepartmentDao;
import com.github.dao.EmployeeDao;
import com.github.pojo.Department;
import com.github.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author subeiLY
 * @create 2022-01-24 20:27
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有产品，返回列表页面
     * @param model
     * @return
     */
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        // 结果返回前端
        model.addAttribute("emps",employees);
        return "emp/ecommerce_products";
    }

    /**
     *  添加进入
     * @param model
     * @return
     */
    // 添加页面
    @GetMapping("/add")
    public String add(Model model){
        // 查出所有的部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/ecommerce_products_edit";
    }

    /**
     *  添加返回
     * @param employee
     * @return
     */
    @PostMapping("/add")
    public String addEmp(Employee employee) {
        System.out.println(employee);
        // 添加员工
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     *  修改按钮
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String toUpdateAll(@PathVariable("id") int id, Model model) {
        // 查询指定id的员工,添加到emp中,用于前端接收
        Employee employee = employeeDao.getEmployee(id);
        model.addAttribute("emp", employee);
        // 查出所有的部门信息,添加到departments中,用于前端接收
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "/emp/update";
        // 返回到编辑员工页面
    }

    /**
     *  修改
     * @param employee
     * @return
     */
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        // 回到员工列表页面
        return "redirect:/emps";
    }

    /**
     *  删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    /**
     *  退出登录
     * @param session
     * @return
     */
    @RequestMapping("/user/loginOut")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }

}


