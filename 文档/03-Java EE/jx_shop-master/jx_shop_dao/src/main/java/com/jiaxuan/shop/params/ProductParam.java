package com.jiaxuan.shop.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: jx
 * Date: 2019-03-12 14:18
 * Description: 前台商品查询条件封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductParam implements Serializable {

    /*商品名称*/
    private String name;
    /*最低价格*/
    private Double minPrice;
    /*最高价格*/
    private Double maxPrice;
    /*商品类型的id*/
    private Integer productTypeId;

}
