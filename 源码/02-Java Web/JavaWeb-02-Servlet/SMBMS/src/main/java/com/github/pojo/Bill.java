package com.github.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Package: com.github.pojo
 * @ClassName: Bill
 * @Author: subei
 * @CreateTime: 2021/8/5 10:43
 * @Description:
 */
@Data
public class Bill {
    private Integer id; // 主键ID
    private String billCode;    // 账单编码
    private String productName; // 商品名称
    private String productDesc; // 商品描述
    private String productUnit; // 商品单位
    private Integer productCount;   // 商品数量
    private Integer totalPrice; // 商品总额
    private int isPayment;  // 是否支付
    private Integer createdBy;  // 创建者
    private Date creationDate;  //创建时间
    private Integer modifyBy;   // 更新者
    private Date modifyDate;    // 更新时间
    private Integer providerId; // 供应商ID
}
