package com.jiaxuan.shop.common.exception;

/**
 * Author: jx
 * Date: 2019-03-23 12:04
 * Description:<描述>
 */
public class ShippingException extends RuntimeException {
    public ShippingException() {
        super();
    }

    public ShippingException(String message) {
        super(message);
    }

    public ShippingException(String message, Throwable cause) {
        super(message, cause);
    }
}
