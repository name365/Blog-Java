package com.jiaxuan.shop.dao;

import com.jiaxuan.shop.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: jx
 * Date: 2019-03-19 16:52
 * Description: 购物车业务层
 */
public interface CartDao {
    
    int insertCart(Cart cart);

    List<Cart> selectAllCartByCustomerId(Integer customerId);

    Cart selectCartByCustomerIdAndCartId(@Param("customerId") Integer customerId,@Param("cartId") Integer cartId);

    Cart selectCartByCustomerIdAndProductId(@Param("customerId") Integer customerId,@Param("productId") Integer productId);

    Cart selectRedirectCartByCustomerIdAndProductId(@Param("customerId") Integer customerId,@Param("productId") Integer productId);

    int updateCartNumAndTotalPriceById(@Param("id") Integer id, @Param("productNum") Integer num,@Param("totalPrice") Double price);

    int deleteCartById(Integer id);

    int updateCartStatusByCustomerId(@Param("customerId") Integer id,@Param("status") Integer status);

    int updateCartStatusByCartIdAndCustomerId(@Param("cartId") Integer cartId,
                                              @Param("customerId") Integer id,
                                              @Param("status")Integer status);

    int updateCartStatusByCartIdAndCustomerIds(@Param("cartIds") Integer[] cartIds,
                                               @Param("customerId") Integer customerId,
                                               @Param("status") Integer status);

    int updateProductNumAndPriceByCartIdAndCustomerIdAndStatus(@Param("cartId") Integer cartId,
                                                       @Param("productNum") Integer productNum,
                                                       @Param("customerId") Integer id,
                                                       @Param("status") int status,
                                                       @Param("totalPrice")Double totalPrice);

    List<Cart> selectCartByCartIdsAndCustomerId(@Param("cartIds") Integer[] orderCartIds,
                                                @Param("customerId") Integer id,
                                                @Param("status") int status);

    List<Cart> selectRedirectCartByCartIdsAndCustomerId(@Param("cartIds") Integer[] orderCartIds,
                                                @Param("customerId") Integer id,
                                                @Param("status") int status);
}
