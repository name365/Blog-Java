package com.jiaxuan.shop.common.exception;

/**
 * Author: jx
 * Date: 2019-03-22 14:51
 * Description:<描述>
 */

public class OrderCartNotFoundException extends RuntimeException {

    public OrderCartNotFoundException() {
        super();
    }

    public OrderCartNotFoundException(String message) {
        super(message);
    }

    public OrderCartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
