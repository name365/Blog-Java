package com.jiaxuan.front.shop.controller;

import com.jiaxuan.shop.common.exception.CustomerLoginNameIsExist;
import com.jiaxuan.shop.common.exception.PhoneNotRegistException;
import com.jiaxuan.shop.common.utils.CommonUtils;
import com.jiaxuan.shop.common.utils.RedisUtil;
import com.jiaxuan.shop.common.utils.ResponseResult;
import com.jiaxuan.shop.pojo.Customer;
import com.jiaxuan.shop.service.CustomerService;
import com.jiaxuan.shop.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: jx
 * Date: 2019-03-12 17:24
 * Description: 客户相关的接口
 */
@Controller
@RequestMapping("/front/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     *功能描述: 通过账户名密码登录
     * @Author jx
     * @Date 9:45 2019/03/14
     * @Param [loginName, password, session, model]
     * @return ResponseResult
     */
    @RequestMapping("loginByAccount")
    @ResponseBody
    public ResponseResult loginByAccount(String loginName, String password, HttpSession session, Model model){

        try {
            password = CommonUtils.MD5(password);
            Customer customer = customerService.login(loginName, password);
            //将密码设置为空，存到session中
            customer.setPassword(null);
            session.setAttribute("customer",customer);
            return ResponseResult.success(customer);
        }catch (Exception e){
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     *功能描述: 退出登录
     * @Author jx
     * @Date 19:40 2019/03/12
     * @Param [session]
     * @return ResponseResult
     */
    @RequestMapping("logout")
    @ResponseBody
    public ResponseResult logout(HttpSession session){
        //设置session失效
        session.invalidate();
        return ResponseResult.success();
    }

    /**
     *功能描述: 通过短信快捷登录
     * @Author jx
     * @Date 9:45 2019/03/14
     * @Param [phone, verifyCode, session]
     * @return ResponseResult
     */
    @RequestMapping("loginBySms")
    @ResponseBody
    public ResponseResult loginBySms(String phone,int verifyCode,HttpSession session){
        try {
            //判断手机号是否注册
            Customer customer = customerService.findByPhone(phone);

            //判断验证码是否存在
            //Object code = session.getAttribute("smsVerifyCode");
            //TODO:/*从redis中获取验证码*/
            String code = RedisUtil.get(session.getId());
            if (!ObjectUtils.isEmpty(code)) {
                //判断验证码是否正确
                if (Integer.parseInt(code) == verifyCode) {
                    /*将用户信息放到session中*/
                    customer.setPassword(null);
                    session.setAttribute("customer",customer);
                    //返回数据给前端渲染
                    return ResponseResult.success(customer);
                }else {
                   return ResponseResult.fail("验证码不正确");
                }
            }else{
                return ResponseResult.fail("验证码不存在或已过期，请重新输入");
            }
        }catch (PhoneNotRegistException e){
            return  ResponseResult.fail(e.getMessage());
        }
    }

    /**
     *功能描述: 用户注册
     * @Author jx
     * @Date 9:46 2019/03/14
     * @Param [customerVo, session]
     * @return ResponseResult
     */
    @RequestMapping("regist")
    @ResponseBody
    public ResponseResult regist(CustomerVo customerVo,HttpSession session){
        try {
            String md5Pwd = CommonUtils.MD5(customerVo.getPassword());
            customerVo.setPassword(md5Pwd);
        } catch (Exception e) {
            return ResponseResult.fail("注册失败");
        }
        Customer customer = customerService.regist(customerVo);
        if (customer != null) {
            customer.setPassword(null);
            session.setAttribute("customer",customer);
            return ResponseResult.success("注册成功",customer);
        }else {
            return ResponseResult.fail("注册失败");
        }
    }

    /**
     *功能描述: 校验原始密码是否存在
     * @Author jx
     * @Date 14:47 2019/03/13
     * @Param [password, session]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("checkPassword")
    @ResponseBody
    public Map<String,Object> checkPassword(String password,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Customer customer = (Customer) session.getAttribute("customer");
        try{
            password = CommonUtils.MD5(password);
            Customer result = customerService.login(customer.getLoginName(),password);
            if (result != null) {
                map.put("valid",true);
            }
        }catch (Exception e){
            map.put("valid",false);
            map.put("message","原始密码不正确");
        }
        return map;
    }

    /**
     *功能描述: 修改密码
     * @Author jx
     * @Date 14:47 2019/03/13
     * @Param []
     * @return ResponseResult
     */
    @RequestMapping("modifyPassword")
    @ResponseBody
    public ResponseResult modifyPassword(String password, String newpassword ,HttpSession session){

        Customer customer = (Customer) session.getAttribute("customer");
        try {
            newpassword = CommonUtils.MD5(newpassword);
            customer.setPassword(newpassword);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("密码修改失败");
        }

        //设置session失效
        if (customerService.modifyCustomerPassword(customer)) {
            session.invalidate();
            customer.setPassword(null);
            return ResponseResult.success("密码修改成功,请重新登录",customer);
        }else{
            //TODO:这里有点啰嗦，看后面有什么好的方式
            //如果失败了，换回原来的密码
            customer.setPassword(null);
            session.setAttribute("customer",customer);
            return ResponseResult.fail("密码修改失败");
        }
    }

    /**
     *功能描述: 校验用户登录名是否存在，登录名是唯一的
     * @Author jx
     * @Date 17:13 2019/03/13
     * @Param [loginName]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("checkLoginName")
    @ResponseBody
    public Map<String,Object> checkLoginName(String loginName){
        Map<String,Object> map = new HashMap<>();
        try {
            Boolean isvariable = customerService.findByLoginName(loginName);
            if(isvariable){
                map.put("valid",true);
            }
        }catch (CustomerLoginNameIsExist e){
            map.put("valid",false);
            map.put("message",e.getMessage());
        }
        return map;
    }



    //TODO:个人中心模块
    /**
     *功能描述: 展示用户个人中心
     * @Author jx
     * @Date 9:46 2019/03/14
     * @Param []
     * @return java.lang.String
     */
    @RequestMapping("customerCenter")
    public String customerCenter(HttpSession session,Model model){
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return "main";
        }
        Customer user = customerService.findCustomerId(customer.getId());
        user.setPassword(null);
        model.addAttribute("user",user);
        return "center";
    }

    /**
     *功能描述: session 超时
     * @Author jx
     * @Date 10:14 2019/04/02
     * @Param [attributes]
     * @return java.lang.String
     */
    @RequestMapping("sessionTimeOut")
    public String sessionTimeOut(RedirectAttributes attributes){
        attributes.addFlashAttribute("sessionTimeOut","session超时");
        return "redirect:/front/product/searchAllProducts";
    }

    /**
     *功能描述: 个人中心修改信息
     * @Author jx
     * @Date 10:14 2019/04/02
     * @Param [customerId, mobile, address, session]
     * @return ResponseResult
     */
    @RequestMapping("modifyCenterCustomer")
    @ResponseBody
    public ResponseResult modifyCenterCustomer(Integer customerId,
                                               String mobile,String address,HttpSession session){
        Object customer = session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("请先登录");
        }

        if (customerService.modifyCenterCustomer(customerId,mobile,address)) {
            return ResponseResult.success("信息修改成功");
        }
        return ResponseResult.fail("信息修改失败");
    }

}
