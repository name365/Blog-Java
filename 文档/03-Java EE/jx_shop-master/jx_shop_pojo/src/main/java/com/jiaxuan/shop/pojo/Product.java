package com.jiaxuan.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: jx
 * Date: 2019-03-10 10:41
 * Description: 商品实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{

    /*商品id*/
    private Integer id;
    /*商品名称*/
    private String name;
    /*商品价格*/
    private Double price;
    /*商品简介*/
    private String info;
    /*商品的图片*/
    private String image;
    /*商品类型*/
    private ProductType productType;

}
