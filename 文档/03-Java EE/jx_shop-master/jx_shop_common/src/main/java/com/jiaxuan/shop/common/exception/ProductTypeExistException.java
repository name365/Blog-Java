package com.jiaxuan.shop.common.exception;

/**
 * Author: jx
 * Date: 2019-03-09 19:07
 * Description:商品类型存在异常
 */

public class ProductTypeExistException extends RuntimeException {
    public ProductTypeExistException() {
        super();
    }

    public ProductTypeExistException(String message) {
        super(message);
    }

    public ProductTypeExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
