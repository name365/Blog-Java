package com.jiaxuan.shop.service.impl;

import com.jiaxuan.shop.common.constant.CustomerConstant;
import com.jiaxuan.shop.common.exception.CustomerLoginNameIsExist;
import com.jiaxuan.shop.common.exception.CustomerNotFoundException;
import com.jiaxuan.shop.common.exception.LoginErrorException;
import com.jiaxuan.shop.common.exception.PhoneNotRegistException;
import com.jiaxuan.shop.dao.CustomerDao;
import com.jiaxuan.shop.params.CustomerParam;
import com.jiaxuan.shop.pojo.Customer;
import com.jiaxuan.shop.service.CustomerService;
import com.jiaxuan.shop.vo.CustomerVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-12 17:33
 * Description:<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;

    /**
     *功能描述: 用户登录
     * @Author jx
     * @Date 11:21 2019/03/13
     * @Param [loginName, password]
     * @return Customer
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Customer login(String loginName, String password) throws LoginErrorException {
        Customer customer = customerDao.selectByLoginNameAndPassword(loginName, password, CustomerConstant.CUSTOMER_IS_VALID);
        if (customer == null) {
            throw new LoginErrorException("登录失败，用户名或密码错误");
        }
        return customer;
    }

    /**
     *功能描述: 手机验证码快速登录
     * @Author jx
     * @Date 11:20 2019/03/13
     * @Param [phone]
     * @return Customer
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Customer findByPhone(String phone) throws PhoneNotRegistException {
        Customer customer = customerDao.selectByPhone(phone);
        if (customer == null) {
            throw new PhoneNotRegistException("该手机号码尚未注册");
        }
        return customer;
    }

    /**
     *功能描述: 用户注册
     * @Author jx
     * @Date 11:20 2019/03/13
     * @Param [customerVo]
     * @return java.lang.Boolean
     */
    @Override
    public Customer regist(CustomerVo customerVo) {
        Customer customer = new Customer();

        BeanUtils.copyProperties(customerVo,customer);
        //刚开始注册，默认账户激活状态
        customer.setIsValid(CustomerConstant.CUSTOMER_IS_VALID);
        customer.setRegistDate(new Date());

        int rows = customerDao.insertCustomer(customer);
        if (rows >= 1) {
            return customer;
        }
        return null;
    }

    /**
     *功能描述: 用户修改密码
     * @Author jx
     * @Date 15:01 2019/03/13
     * @Param [newpassword]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean modifyCustomerPassword(Customer customer) {
        int rows = customerDao.updateCustomerPassword(customer);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 根据用户登录名查找用户,可用返回true
     * @Author jx
     * @Date 17:14 2019/03/13
     * @Param [loginName]
     * @return Customer
     */
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public Boolean findByLoginName(String loginName)throws CustomerLoginNameIsExist {
        Customer customer = customerDao.selectByLoginName(loginName);
        if (customer == null) {
            return true;
        }
        throw new CustomerLoginNameIsExist("该用户名已经存在");
    }

    /**
     *功能描述: 获取所有的用户列表
     * @Author jx
     * @Date 21:00 2019/03/13
     * @Param []
     * @return java.util.List<Customer>
     */
    @Override
    public List<Customer> findAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Customer> findCustomersByParams(CustomerParam customerParam) {
        return customerDao.selectCustomersByParams(customerParam);
    }

    /**
     *功能描述: 根据 id 用户信息
     * @Author jx
     * @Date 10:47 2019/03/14
     * @Param [id]
     * @return Customer
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Customer findCustomerId(int id) {
        Customer customer = customerDao.selectCustomerById(id);
        if (ObjectUtils.isEmpty(customer)) {
            throw new CustomerNotFoundException("该用户不存在");
        }
        return customer;
    }

    /**
     *功能描述: 修改客户的信息
     * @Author jx
     * @Date 11:18 2019/03/14
     * @Param [customerVo]
     * @return java.lang.Boolean
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Boolean modifyCustomer(CustomerVo customerVo) {
        Customer customer = customerDao.selectCustomerById(customerVo.getId());
        BeanUtils.copyProperties(customerVo,customer);
        int rows = customerDao.updateCustomer(customer);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 修改客户的状态
     * @Author jx
     * @Date 13:46 2019/03/14
     * @Param [id]
     * @return java.lang.Boolean
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Boolean modifyCustomerStatus(int id) {
        Customer customer = customerDao.selectCustomerById(id);
        int isValid = customer.getIsValid();
        if (isValid == CustomerConstant.CUSTOMER_IS_VALID) {
            isValid = CustomerConstant.CUSTOMER_IS_INVALID;
        }else{
            isValid = CustomerConstant.CUSTOMER_IS_VALID;
        }

        int rows = customerDao.updateCustomerStatus(id, isValid);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Boolean modifyCenterCustomer(Integer customerId, String mobile, String address) {
        Customer customer = customerDao.selectCustomerById(customerId);
        if (customer != null) {
            customer.setAddress(address);
            customer.setPhone(mobile);
            int rows = customerDao.updateCustomer(customer);
            if (rows >= 1) {
                return true;
            }
        }
        return false;
    }
}
