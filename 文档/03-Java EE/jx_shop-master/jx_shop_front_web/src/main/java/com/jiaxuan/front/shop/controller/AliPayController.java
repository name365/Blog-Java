package com.jiaxuan.front.shop.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jiaxuan.front.shop.config.AliPayConfig;
import com.jiaxuan.shop.pojo.Customer;
import com.jiaxuan.shop.pojo.Order;
import com.jiaxuan.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author: jx
 * Date: 2019-04-02 14:37
 * Description: 支付接口
 */
@RequestMapping("/front/pay")
@Controller
@Slf4j
public class AliPayController {

    @Autowired
    private OrderService orderService;

    /**
     *功能描述: 订单支付，调支付支付页面
     * @Author jx
     * @Date 16:25 2019/04/02
     * @Param [orderNumber, session]
     * @return java.lang.String
     */
    @RequestMapping(value = "goAliPay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAliPay(String orderNumber, HttpSession session) throws AlipayApiException {
        Customer customer = (Customer) session.getAttribute("customer");
        //先拿到订单
        Order order = orderService.findOrderIdByOrderNoAndCustomerId(orderNumber, customer.getId());
        if (order == null) {
            return "订单不存在";
        }

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = order.getOrderNumber();
        //付款金额，必填
        Double total_amount = order.getPrice();
        //订单名称，必填
        String subject = "stha助农服务系统";
        //商品描述，可空
        String body = "用户订购商品个数：" + order.getProductNumber();

        //获得初始化的AlipayClient,只需要初始化一次
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl, AliPayConfig.app_id, AliPayConfig.merchant_private_key, "json", AliPayConfig.charset, AliPayConfig.alipay_public_key, AliPayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AliPayConfig.return_url);
        alipayRequest.setNotifyUrl(AliPayConfig.notify_url);


        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }

    /**
     *功能描述: 支付宝同步通知页面
     * @Author jx
     * @Date 16:26 2019/04/02
     * @Param [request, response, session]
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "aliPayReturnNotice",method = RequestMethod.GET)
    public ModelAndView aliPayReturnNoticeSyn(HttpServletRequest request, HttpServletRequest response, HttpSession session) throws Exception {
        log.info("支付成功, 进入同步通知接口...");

        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
            log.info("parameters= {}",params);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type); //调用SDK验证签名
        } catch (Exception e) {
            log.info("SDK验证签名出现异常");
        }

        //支付成功后回调的页面
        ModelAndView mv = new ModelAndView("pay-finished");

        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            Customer customer = (Customer) session.getAttribute("customer");
            Order order = orderService.findOrderIdByOrderNoAndCustomerId(out_trade_no, customer.getId());
            if (order == null) {
                //校验一下订单号是够被串改
                ModelAndView mv1 = new ModelAndView("pay");
                mv1.addObject("failMsg","非法的订单");
                return mv1;
            }else{
                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
                orderService.modifyOrderStatusByCustomerIdAndOrderNo(customer.getId(), out_trade_no);

                log.info("********************** 支付成功(支付宝同步通知) **********************");
                log.info("* 订单号: {}", out_trade_no);
                log.info("* 支付宝交易号: {}", trade_no);
                log.info("* 实付金额: {}", total_amount);
                log.info("* 购买产品: {}", "jx水果");
                log.info("***************************************************************");

                mv.addObject("orderNumber", out_trade_no);
                mv.addObject("trade_no", trade_no);
                mv.addObject("price", total_amount);
                mv.addObject("productName", "stha助农服务系统");
            }
        } else {
            log.info("支付失败, 验签失败...");
            ModelAndView mv3 = new ModelAndView("pay");
            mv3.addObject("failMsg","支付失败，验签失败");
            return mv3;
        }
        return mv;
    }

    /**
     * @Description: 支付宝异步 通知页面
     */
    @RequestMapping(value = "aliPayNotifyNotice", method = RequestMethod.POST)
    @ResponseBody
    public String aliPayNotifyNoticeAsy(HttpServletRequest request,HttpSession session) throws Exception {

        log.info("支付成功, 进入异步通知接口...");

        //获取支付宝 POST 过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
            log.info("参数集合： {}",params);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
        if (signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_FINISHED")) {
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                log.info("out_trade_no: {}, trade_no: {}, trade_status: {}, total_amount: {}",
                        out_trade_no,trade_no,trade_status,total_amount);
                // 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
                //TODO:注意:支付宝支付，异步通知，session会被关闭
                //Customer customer = (Customer) session.getAttribute("customer");
                if (orderService.modifyOrderStatusByOrderNo(out_trade_no)) {
                    log.info("支付异步通知，订单保存成功");
                }

                log.info("********************** 支付成功(支付宝异步通知) **********************");
                log.info("* 订单号: {}", out_trade_no);
                log.info("* 支付宝交易号: {}", trade_no);
                log.info("* 实付金额: {}", total_amount);
                log.info("* 购买产品: {}", "stha助农服务系统");
                log.info("***************************************************************");
                log.info("支付成功...");
            }
        } else {
            //验证失败,记录日志
            log.error(AlipaySignature.getSignCheckContentV1(params));
        }
        return "success";
    }
}
