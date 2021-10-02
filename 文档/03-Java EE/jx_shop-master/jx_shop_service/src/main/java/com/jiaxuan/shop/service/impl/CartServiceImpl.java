package com.jiaxuan.shop.service.impl;

import com.jiaxuan.shop.common.constant.CartConstant;
import com.jiaxuan.shop.common.exception.OrderCartNotFoundException;
import com.jiaxuan.shop.dao.CartDao;
import com.jiaxuan.shop.dao.ProductDao;
import com.jiaxuan.shop.pojo.Cart;
import com.jiaxuan.shop.pojo.Product;
import com.jiaxuan.shop.service.CartService;
import com.jiaxuan.shop.vo.CartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-19 16:07
 * Description: 购物车业务实现类
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;

    /**
     *功能描述: 添加商品到购物车
     * @Author jx
     * @Date 17:09 2019/03/19
     * @Param [cartVo]
     * @return java.lang.Boolean
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Boolean saveToCart(CartVo cartVo) {

        Cart cartResult = cartDao.selectCartByCustomerIdAndProductId(cartVo.getCustomerId(), cartVo.getProductId());
        //先查询一下购物车中是否有此商品，没有则插入保存，有的话就更新购物车的商品数量就可以了
        if (cartResult == null) {
            Cart cart = new Cart();
            Product product = productDao.selectProductById(cartVo.getProductId());
            //计算总价
            Double totalPrice = product.getPrice() * cartVo.getProductNum();

            BeanUtils.copyProperties(cartVo,cart);
            cart.setTotalPrice(totalPrice);
            cart.setProduct(product);
            //设置状态，默认是有效的
            cart.setStatus(CartConstant.CART_PRODUCT_STATUS_VALID);
            cart.setCreateTime(new Date());

            int rows = cartDao.insertCart(cart);
            if (rows >= 1) {
                return true;
            }else{
                return false;
            }
        }

        //更新购物车的商品数量
        int productSums = cartResult.getProductNum() + cartVo.getProductNum();
        Product pd = productDao.selectProductById(cartVo.getProductId());

        //更新商品总价格
        Double priceSum = pd.getPrice() * productSums;

        int rows = cartDao.updateCartNumAndTotalPriceById(cartResult.getId(), productSums,priceSum);
        if (rows >= 1) {
            return true;
        }
        return false;
    }
    /**
     *功能描述: 根据客户 id 查找他所有购物车信息
     * @Author jx
     * @Date 19:54 2019/03/19
     * @Param [customerId]
     * @return java.util.List<Cart>
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Cart> findCustomerAllCarts(Integer customerId) {
        return cartDao.selectAllCartByCustomerId(customerId);
    }

    /**
     *功能描述: 清空用户的购物车
     * @Author jx
     * @Date 19:22 2019/03/20
     * @Param [id]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean modifyCartStatus(Integer id) {
        int rows = cartDao.updateCartStatusByCustomerId(id, CartConstant.CART_PRODUCT_STATUS_ISVALID);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 从购物车中移除某一商品
     * @Author jx
     * @Date 10:42 2019/03/21
     * @Param [cartId, id]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean modifyCartStatusByCartIdAndCustomerId(Integer cartId, Integer id) {
        int rows = cartDao.updateCartStatusByCartIdAndCustomerId(cartId, id, CartConstant.CART_PRODUCT_STATUS_ISVALID);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 从购物车中移除选中的商品
     * @Author jx
     * @Date 13:01 2019/03/21
     * @Param [cartIds, customerId]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean modifyCartStatusByCartIdAndCustomerIds(Integer[] cartIds, Integer customerId) {
        int rows = cartDao.updateCartStatusByCartIdAndCustomerIds(cartIds, customerId, CartConstant.CART_PRODUCT_STATUS_ISVALID);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 购物车页面修改商品数量 以及总价格更新
     * @Author jx
     * @Date 15:25 2019/03/22
     * @Param [cartId, productNum, id]
     * @return java.lang.Boolean
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Boolean modifyNumAndPriceByCartIdAndCustomerIdAndStatus(Integer cartId, Integer productNum, Integer id) {

        //拿到该商品信息，计算修改数量后的总价格
        Cart cart = cartDao.selectCartByCustomerIdAndCartId(id, cartId);
        Double totalPrice = (cart.getProduct().getPrice()) * productNum;

        int rows = cartDao.updateProductNumAndPriceByCartIdAndCustomerIdAndStatus(cartId, productNum,
                id, CartConstant.CART_PRODUCT_STATUS_VALID,totalPrice);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 根据客户选中购物车多个物品项进行查询
     * @Author jx
     * @Date 15:25 2019/03/22
     * @Param [orderCartIds, id]
     * @return java.util.List<Cart>
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Cart> findCartByCartIdsAndCustomerId(Integer[] orderCartIds, Integer id) throws OrderCartNotFoundException {

        List<Cart> cartList = cartDao.selectCartByCartIdsAndCustomerId(orderCartIds, id, CartConstant.CART_PRODUCT_STATUS_VALID);

        if (cartList.size() == 0) {
            throw new OrderCartNotFoundException("购物车信息不存在");
        }
        return cartList;
    }

    @Override
    public List<Cart> findRedirectCartByCartIdsAndCustomerId(Integer[] orderCartIds, Integer id) {
        List<Cart> cartList = cartDao.selectRedirectCartByCartIdsAndCustomerId(orderCartIds, id, CartConstant.CART_PRODUCT_REDIRECT_TO_CART);

        if (cartList.size() == 0) {
            throw new OrderCartNotFoundException("购物车信息不存在");
        }
        return cartList;
    }

    /**
     *功能描述: 直接购买，产生一个购物车，返回购物车id
     * @Author jx
     * @Date 10:42 2019/04/03
     * @Param [cart]
     * @return int
     */
    @Override
    public int redirectToCart(CartVo cartVo) {

        Cart cartResult = cartDao.selectRedirectCartByCustomerIdAndProductId(cartVo.getCustomerId(), cartVo.getProductId());
        //先查询一下购物车中是否有此商品，没有则插入保存，有的话就更新购物车的商品数量就可以了
        if (cartResult == null) {
            Cart cart = new Cart();
            Product product = productDao.selectProductById(cartVo.getProductId());
            //计算总价
            Double totalPrice = product.getPrice() * cartVo.getProductNum();

            BeanUtils.copyProperties(cartVo,cart);
            cart.setTotalPrice(totalPrice);
            cart.setProduct(product);
            //直接购买放入购物车
            cart.setStatus(CartConstant.CART_PRODUCT_REDIRECT_TO_CART);
            cart.setCreateTime(new Date());

            int rows = cartDao.insertCart(cart);
            if (rows >= 1) {
                return cart.getId();
            }else{
                return 0;
            }
        }

        //直接购买，这里没有叠加，更新购物车的商品数量
        int productSums = cartVo.getProductNum();
        Product pd = productDao.selectProductById(cartVo.getProductId());

        //更新商品总价格
        Double priceSum = pd.getPrice() * productSums;

        int rows = cartDao.updateCartNumAndTotalPriceById(cartResult.getId(), productSums,priceSum);
        if (rows >= 1) {
            return cartResult.getId();
        }
        return 0;
    }
}
