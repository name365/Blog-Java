package com.jiaxuan.shop.service;

import com.jiaxuan.shop.pojo.Shipping;
import com.jiaxuan.shop.vo.ShippingVo;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-23 10:44
 * Description: 收货地址业务类接口
 */
public interface ShippingService {

   Shipping findShippingByCustomerIdAndShippingId(Integer customerId,Integer shippingId);
   List<Shipping> findCustomerAllShippings(Integer customerId);
   int saveShipping(ShippingVo shippingVo,Integer customerId);
   Boolean modifyShipping(ShippingVo shippingVo,Integer customerId);
   Boolean removeShipping(Integer shippingId,Integer customerId);

}
