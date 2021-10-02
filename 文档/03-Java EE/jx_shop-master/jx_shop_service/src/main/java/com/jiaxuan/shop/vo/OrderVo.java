package com.jiaxuan.shop.vo;

import com.jiaxuan.shop.pojo.OrderItem;
import com.jiaxuan.shop.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-24 16:44
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo extends Order{
    /*订单中包含的类目*/
    private List<OrderItem> orderItemList;
}
