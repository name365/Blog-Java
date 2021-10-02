package com.jiaxuan.front.shop.controller;

import com.jiaxuan.shop.common.utils.ResponseResult;
import com.jiaxuan.shop.pojo.Cart;
import com.jiaxuan.shop.pojo.Customer;
import com.jiaxuan.shop.service.CartService;
import com.jiaxuan.shop.service.ProductService;
import com.jiaxuan.shop.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-13 0:24
 * Description:<描述>
 */
@Controller
@RequestMapping("/front/cart")
@Slf4j
public class CarController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    /**
     * 功能描述: 清空购物车后展示此页面
     *
     * @return java.lang.String
     * @Author jx
     * @Date 19:50 2019/03/20
     * @Param []
     */
    @RequestMapping("showEmptyCart")
    public String showEmptyCart() {
        return "emptyCart";
    }

    /**
     * 功能描述: 购物车展示
     *
     * @return java.lang.String
     * @Author jx
     * @Date 21:14 2019/03/19
     * @Param [session, model]
     */
    @RequestMapping("myCarts")
    public String myCars(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            List<Cart> cartList = cartService.findCustomerAllCarts(customer.getId());
            model.addAttribute("cartList", cartList);
        }
        return "car";
    }

    /**
     * 功能描述: 添加商品到购物车
     *
     * @return ResponseResult
     * @Author jx
     * @Date 16:02 2019/03/19
     * @Param [id, session]
     */
    @RequestMapping("addToCart")
    @ResponseBody
    public ResponseResult addToCart(Integer id, Integer textBox, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            //用户没有登录，则提示让他登录
            return ResponseResult.deny("还请客官先登录哦~");
        } else {

            CartVo cartVo = new CartVo();
            cartVo.setCustomerId(customer.getId());
            cartVo.setProductId(id);
            cartVo.setProductNum(textBox);

            if (cartService.saveToCart(cartVo)) {
                //此session用于标志购物车非空
                //session.setAttribute("emptyCart",0);
                return ResponseResult.success("商品成功加入购物车");
            } else {
                return ResponseResult.fail("商品加入购物车失败");
            }
        }
    }

    /**
     * 功能描述: 清空购物车操作
     *
     * @return ResponseResult
     * @Author jx
     * @Date 19:24 2019/03/20
     * @Param [session]
     */
    @RequestMapping("clearAllProductFromCart")
    @ResponseBody
    public ResponseResult clearAllProductFromCart(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (!ObjectUtils.isEmpty(customer)) {
            if (cartService.modifyCartStatus(customer.getId())) {
                //此session用于标志购物车为空
                //session.setAttribute("emptyCart",null);
                return ResponseResult.success("购物车已清空");
            }
        } else {
            return ResponseResult.fail("请您先登录");
        }
        return ResponseResult.fail("商品移除失败");
    }

    /**
     * 功能描述: 从购物车中移除某一商品
     *
     * @return ResponseResult
     * @Author jx
     * @Date 10:49 2019/03/21
     * @Param [cartId, session]
     */
    @RequestMapping("removeOneProduct")
    @ResponseBody
    public ResponseResult removeOneProduct(Integer cartId, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("请您先登录");
        }
        if (cartService.modifyCartStatusByCartIdAndCustomerId(cartId, customer.getId())) {
            return ResponseResult.success("该商品移除成功");
        }
        return ResponseResult.fail();
    }

    /**
     * 功能描述: 从购物车中移除选中的商品
     *
     * @return ResponseResult
     * @Author jx
     * @Date 11:49 2019/03/21
     * @Param [cartIds, session]
     */
    @RequestMapping("removeMoreProductFromCart")
    @ResponseBody
    public ResponseResult removeMoreProductFromCart(Integer[] cartIds, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("请您先登录");
        }
        if (cartService.modifyCartStatusByCartIdAndCustomerIds(cartIds,customer.getId())) {
            return ResponseResult.success("商品移除成功");
        }
        return ResponseResult.fail("商品移除失败");
    }

    /**
     *功能描述: 购物车页面修改商品的数量
     * @Author jx
     * @Date 17:15 2019/03/21
     * @Param [cartId, productNum, session]
     * @return ResponseResult
     */
    @RequestMapping("inputModifyProductNum")
    @ResponseBody
    public ResponseResult inputModifyProductNum(Integer cartId, Integer productNum, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("客官，还请先登录");
        }
        if (cartService.modifyNumAndPriceByCartIdAndCustomerIdAndStatus(cartId,productNum,customer.getId())) {
            return ResponseResult.success("商品数量已修改");
        }
        return ResponseResult.fail("商品数量修改失败");
    }

    /**
     *功能描述: 临时将前端发送过来的数据存到 session中去
     * @Author jx
     * @Date 11:48 2019/03/22
     * @Param [count, price, orderCartIds, session]
     * @return ResponseResult
     */
    @RequestMapping("addTempOrderItem")
    @ResponseBody
    public ResponseResult addTempOrderItem(Integer count, String price, Integer[]orderCartIds, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("客官还请先登录");
        }
        session.setAttribute("count",count);

        String[] strings = price.split("¥");

        double newPrice = Double.parseDouble(strings[1]);

        session.setAttribute("price",newPrice);
        session.setAttribute("orderCartIds",orderCartIds);
        return ResponseResult.success(session);
    }
}
