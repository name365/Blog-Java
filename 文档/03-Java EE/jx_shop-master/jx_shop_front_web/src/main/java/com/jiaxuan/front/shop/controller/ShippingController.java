package com.jiaxuan.front.shop.controller;

import com.jiaxuan.shop.common.exception.ShippingException;
import com.jiaxuan.shop.common.utils.ResponseResult;
import com.jiaxuan.shop.pojo.Customer;
import com.jiaxuan.shop.pojo.Shipping;
import com.jiaxuan.shop.service.ShippingService;
import com.jiaxuan.shop.vo.ShippingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: jx
 * Date: 2019-03-23 10:37
 * Description: 收货地址相关的接口
 */
@Controller
@RequestMapping("/front/shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    /**
     * 功能描述: 添加收货地址
     *
     * @return ResponseResult
     * @Author jx
     * @Date 11:54 2019/03/23
     * @Param [shippingVo, session, model]
     */
    @RequestMapping("saveShipping")
    @ResponseBody
    public ResponseResult saveShipping(ShippingVo shippingVo, HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("请先登录");
        }
        try {
            int shippingId = shippingService.saveShipping(shippingVo, customer.getId());
            model.addAttribute("shippingId", shippingId);
            return ResponseResult.success("地址新增成功");
        } catch (ShippingException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 功能描述: 获取客户所有的收货地址
     *
     * @return ResponseResult
     * @Author jx
     * @Date 12:12 2019/03/23
     * @Param [session, model]
     */
    @RequestMapping("findAllShippings")
    @ResponseBody
    public ResponseResult findAllShippings(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("请先登录");
        }
        List<Shipping> shippingList = shippingService.findCustomerAllShippings(customer.getId());
        model.addAttribute("shippingList", shippingList);
        return ResponseResult.success();
    }

    /**TODO:对于三级联动地址，这里时候ajax异步然后后端来校验是否用户选了地址信息,需要考虑下前端如何校验好*/
    /**
     * 功能描述: 省份校验，校验是否已经选择过省份，默认值是省份
     *
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author jx
     * @Date 18:27 2019/03/23
     * @Param [receiverProvince]
     */
    @RequestMapping("checkProvince")
    @ResponseBody
    public Map<String, Object> checkProvince(String receiverProvince) {
        Map<String, Object> map = new HashMap<>();
        if (receiverProvince == null || "省份".equals(receiverProvince)) {
            map.put("valid", false);
            map.put("message", "请选择省份");
            return map;
        }
        map.put("valid", true);
        return map;
    }

    /**
     * 功能描述: 城市校验
     *
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author jx
     * @Date 18:32 2019/03/23
     * @Param [receiverCity]
     */
    @RequestMapping("checkCity")
    @ResponseBody
    public Map<String, Object> checkCity(String receiverCity) {
        Map<String, Object> map = new HashMap<>();
        if (receiverCity == null || "地级市".equals(receiverCity)) {
            map.put("valid", false);
            map.put("message", "请选择地级市");
            return map;
        }
        map.put("valid", true);
        return map;
    }

    /**
     * 功能描述: 区县校验
     *
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author jx
     * @Date 18:32 2019/03/23
     * @Param [receiverDistrict]
     */
    @RequestMapping("checkDistrict")
    @ResponseBody
    public Map<String, Object> checkDistrict(String receiverDistrict) {
        Map<String, Object> map = new HashMap<>();
        if (receiverDistrict == null || "县级市".equals(receiverDistrict)) {
            map.put("valid", false);
            map.put("message", "请选择县级市");
            return map;
        }
        map.put("valid", true);
        return map;
    }

    /**
     * 功能描述: 移除地址卡片
     *
     * @return ResponseResult
     * @Author jx
     * @Date 21:34 2019/03/23
     * @Param [shippingId, session]
     */
    @RequestMapping("removeShipping")
    @ResponseBody
    public ResponseResult removeShipping(Integer shippingId, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("请先登录");
        }
        if (shippingService.removeShipping(shippingId, customer.getId())) {
            return ResponseResult.success("改地址已经成功移除");
        }
        return ResponseResult.fail("地址移除失败");
    }

    /**
     *功能描述: 显示某一条地址详情
     * @Author jx
     * @Date 22:43 2019/03/23
     * @Param [shippingId, session]
     * @return ResponseResult
     */
    @RequestMapping("showOneShipping")
    @ResponseBody
    public ResponseResult showOneShipping(Integer shippingId, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("请先登录");
        }
        try {
            Shipping shipping = shippingService.findShippingByCustomerIdAndShippingId(customer.getId(), shippingId);
            return ResponseResult.success(shipping);
        } catch (ShippingException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     *功能描述: 修改地址
     * @Author jx
     * @Date 10:05 2019/03/24
     * @Param [shippingVo, session]
     * @return ResponseResult
     */
    @RequestMapping("modifyShipping")
    @ResponseBody
    public ResponseResult modifyShipping(ShippingVo shippingVo,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if (ObjectUtils.isEmpty(customer)) {
            return ResponseResult.fail("请先登录");
        }
        if (shippingService.modifyShipping(shippingVo,customer.getId())) {
            return ResponseResult.success("地址修改成功");
        }
        return ResponseResult.fail("地址修改失败");
    }

}
