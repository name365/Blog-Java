package com.jiaxuan.front.shop.controller;

import com.jiaxuan.shop.common.constant.WxPayConfig;
import com.jiaxuan.shop.common.utils.*;
import com.jiaxuan.shop.pojo.Customer;
import com.jiaxuan.shop.pojo.Order;
import com.jiaxuan.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Author: jx
 * Date: 2019-04-03 14:17
 * Description: 微信支付相关接口
 */
@RequestMapping("/front/wxPay")
@Controller
@Slf4j
public class WxPayController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "goWxPay")
    @ResponseBody
    public ResponseResult goWxPay(String orderNumber, HttpSession session,
                                  HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) session.getAttribute("customer");
        //先拿到订单
        Order order = orderService.findOrderIdByOrderNoAndCustomerId(orderNumber, customer.getId());
        if (order == null) {
            log.info("订单不存在");
        }
        //微信支付需要传ip
        String ip = IpUtils.getIpAddr(request);

        String payUrl = null;
        Map<String, String> payResultMap = null;
        try {
            //payUrl = orderService.getWxPayUrl(order, ip);
            //log.info("payUrl= {}", payUrl);
            payResultMap = orderService.getWxPayResultMap(order, ip);
            log.info("payResultMap= {}", payResultMap);
            //if (payUrl == null) {
            if (payResultMap == null) {
                log.info("微信支付codeUrl为空");
                return ResponseResult.fail("微信支付请求失败");
            }
            if ("ORDERPAID".equalsIgnoreCase(payResultMap.get("err_code"))) {
                log.info("该订单已支付，请勿重复提交");
                return ResponseResult.fail("该订单已支付，请勿重复提交");
            } else {
                try {

                    payUrl = payResultMap.get("code_url");
                    //BufferedImage bufImg = null;
                    ////将获取微信后台返回的支付codeURL 转化为 二维码
                    //Map<EncodeHintType, Object> hints = new HashMap<>();
                    ////设置纠错等级
                    //hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    ////设置编码类型
                    //hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                    //
                    //BitMatrix bitMatrix = new MultiFormatWriter().encode(payUrl, BarcodeFormat.QR_CODE,
                    //        400, 400, hints);
                    ////OutputStream out = response.getOutputStream();
                    ////MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
                    //MatrixToImageConfig config = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
                    //bufImg = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

                    //获取支付二维码图片
                    BufferedImage bufImg = ZxingUtil.createImage(payUrl,400,400);
                    //将二维码图片存放到session中
                    request.getSession().setAttribute("image", bufImg);

                } catch (Exception e) {
                    log.info("生成二维码失败");
                    e.printStackTrace();
                    return ResponseResult.fail("生成二维码失败");
                }

            }
        } catch (Exception e) {
            log.info("获取支付codeUrl失败");
            e.printStackTrace();
            return ResponseResult.fail("获取支付codeUrl失败");
        }
        return ResponseResult.success();
    }

    /**
     * 功能描述: 微信支付回调接口
     *
     * @return void
     * @Author jx
     * @Date 15:47 2019/04/03
     * @Param [request, response]
     */
    @RequestMapping("callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws Exception {

        InputStream inputStream = request.getInputStream();
        //BufferedReader 是包装设计模式，性能更高
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();
        inputStream.close();

        Map<String, String> map = WxPayUtils.xmlToMap(sb.toString());

        //转化为 sortedMap
        SortedMap<String, String> sortedMap = WxPayUtils.getSortedMap(map);

        //判断签名是否正确
        if (WxPayUtils.isCorrectSign(sortedMap, WxPayConfig.wxpay_key)) {
            //--------------------------
            //TODO:处理业务开始
            //--------------------------

            String resXml = "";

            //判断请求微信支付的接口是否成功，此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
            if ("SUCCESS".equals(sortedMap.get("return_code"))) {
                //判断业务返回结果是否成功，成功则表示支付成功
                if ("SUCCESS".equals(sortedMap.get("result_code"))) {

                    String mch_id = sortedMap.get("mch_id");
                    String openid = sortedMap.get("openid");
                    String is_subscribe = sortedMap.get("is_subscribe");
                    String out_trade_no = sortedMap.get("out_trade_no");
                    String total_fee = sortedMap.get("total_fee");
                    String transaction_id = sortedMap.get("transaction_id");

                    log.info("===========微信回调信息==========");
                    log.info("支付成功");
                    log.info("mch_id:" + mch_id);
                    log.info("openid:" + openid);
                    log.info("is_subscribe:" + is_subscribe);
                    log.info("out_trade_no:" + out_trade_no);
                    log.info("total_fee:" + total_fee);
                    log.info("transaction_id:" + transaction_id);
                    log.info("==================================");

                    String outTradeNo = sortedMap.get("out_trade_no");
                    Order order = orderService.findOrderByOutTradeNo(outTradeNo);
                    if (order.getStatus() == 0) {//判断逻辑看业务逻辑，订单未支付
                        Boolean status = orderService.modifyOrderStatusByOrderNo(outTradeNo);
                        if (status) {//需要通知微信订单处理成功
                            log.info("订单状态已成功更新");
                            RedisUtil.set("transactionId",transaction_id);
                        }
                    } else {
                        log.info("该笔订单已完成支付，无需更新");
                    }
                    //TODO:通知微信，异步确认成功，必须响应，要不然微信后台会一直通知后台处理支付，超过8次则认为订单交易失败

                    SortedMap<String, String> respMap = new TreeMap<>();
                    respMap.put("return_code", "SUCCESS");
                    respMap.put("return_msg", "OK");
                    String respMapXml = WxPayUtils.mapToXml(respMap);
                    log.info("支付成功响应微信后台：{}", respMapXml);

                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

                    response.setContentType("text/xml;charset=UTF-8");
                    response.getWriter().println(resXml);

                } else {
                    //TODO:支付失败的情况
                    log.info("支付失败，错误信息： {} {} ", sortedMap.get("result_code"), sortedMap.get("err_code"));

                    SortedMap<String, String> respMap = new TreeMap<>();
                    respMap.put("return_code", "FAIL");
                    respMap.put("return_msg", "NO-GOOD");
                    String respMapXml = WxPayUtils.mapToXml(respMap);
                    log.info("支付失败响应微信后台：{}", respMapXml);

                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    response.setContentType("text/xml;charset=UTF-8");
                    response.getWriter().println(resXml);
                }
            } else {
                log.info("统一下单请求失败，错误信息： {} {} ", sortedMap.get("return_code"), sortedMap.get("return_msg"));
            }
        } else {
            log.info("签名验证失败");
        }
    }

    /**
     * 功能描述: 显示微信支付二维码
     *
     * @return void
     * @Author jx
     * @Date 16:57 2019/04/04
     * @Param [response, request]
     */
    @RequestMapping("showPayQRCode")
    public void showPayQRCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        BufferedImage image = (BufferedImage) request.getSession().getAttribute("image");
        if (!ObjectUtils.isEmpty(image)) {
            ImageIO.write(image, "png", response.getOutputStream());
        }
    }

    /**
     *功能描述: 检测结算订单的状态
     * @Author jx
     * @Date 18:04 2019/04/04
     * @Param [orderNumber, session]
     * @return ResponseResult
     */
    @RequestMapping("CheckPayStatus")
    @ResponseBody
    public ResponseResult CheckPayStatus(String orderNumber, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if (!ObjectUtils.isEmpty(customer)) {
            Order order = orderService.findOrderIdByOrderNoAndCustomerId(orderNumber, customer.getId());
            if (order.getStatus() == 0) {
                return ResponseResult.fail("未完成支付");
            }else if(order.getStatus() == 1){
                return ResponseResult.success("已完成支付");
            }
        }
        return ResponseResult.fail("订单信息不存在");
    }

    /**
     *功能描述: 微信支付成功跳转
     * @Author jx
     * @Date 18:13 2019/04/04
     * @Param [orderNumber, session]
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("ShowPayStatus")
    public ModelAndView ShowPayStatus(String orderNumber, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        String transactionId = RedisUtil.get("transactionId");
        ModelAndView mv = null;
        if (ObjectUtils.isEmpty(customer)) {
            mv = new ModelAndView("main");
        }

        Order order = orderService.findOrderIdByOrderNoAndCustomerId(orderNumber, customer.getId());

        mv = new ModelAndView("pay-finished");
        mv.addObject("orderNumber", order.getOrderNumber());
        mv.addObject("trade_no", transactionId);
        mv.addObject("price", order.getPrice());
        mv.addObject("productName", "jx水果");

        return mv;
    }
}
