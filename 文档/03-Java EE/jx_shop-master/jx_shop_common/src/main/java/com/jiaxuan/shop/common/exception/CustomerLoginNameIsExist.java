package com.jiaxuan.shop.common.exception;

/**
 * Author: jx
 * Date: 2019-03-13 17:20
 * Description: 用户账户名校验自定义异常
 */
public class CustomerLoginNameIsExist extends RuntimeException{
    public CustomerLoginNameIsExist() {
        super();
    }

    public CustomerLoginNameIsExist(String message) {
        super(message);
    }

    public CustomerLoginNameIsExist(String message, Throwable cause) {
        super(message, cause);
    }
}
