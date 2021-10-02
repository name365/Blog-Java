package com.jiaxuan.front.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiaxuan.shop.common.utils.HttpClientUtils;
import com.jiaxuan.shop.common.utils.RedisUtil;
import com.jiaxuan.shop.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Author: jx
 * Date: 2019-03-12 20:51
 * Description: 短信接口
 */
@Controller
@RequestMapping("/front/sms")
public class SmsController {

    @Value("${sms.url}")
    private String url;

    @Value("${sms.key}")
    private String key;

    @Value("${sms.tplId}")
    private String tplId;

    @Value("${sms.tplValue}")
    private String tplValue;

    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public ResponseResult sendVerifyCode(String phone , HttpSession session){
        try {
            //生成 6 位随机验证码
            Random random = new Random();
            int smsCode = random.nextInt(899999) + 100000;
            //将随机验证码存放到session中
            //session.setAttribute("smsVerifyCode",smsCode);

            //将验证码放到redis缓存中,设置验证码有效期为2分钟
            //TODO:session.getId() session的id是唯一的
            RedisUtil.set(session.getId(),smsCode+"",2*60);

            //封装发送的内容
            Map<String,String> params = new HashMap<>();
            params.put("mobile",phone);
            params.put("tpl_id",tplId);
            params.put("tpl_value",tplValue+smsCode);
            params.put("key",key);
            //发送短信
            String result = HttpClientUtils.doPost(url, params);

            Map<String,Object> resultMap = new HashMap<>();

            Map map = JSONObject.parseObject(result, resultMap.getClass());

            if ((Integer)(map.get("error_code")) == 0) {
                //状态码返回0，表示成功
                return ResponseResult.success("验证码发送成功");
            }
        }catch (Exception e){
            return ResponseResult.fail("验证码发送失败");
        }
        return ResponseResult.fail("验证码发送失败");
    }

    @RequestMapping("CheckSmsCode")
    @ResponseBody
    public Map<String,Object> CheckSmsCode(String verifyCode,HttpSession session){
        String smsCode = RedisUtil.get(session.getId());

        Map<String,Object> map = new HashMap<>();

        if (verifyCode.equals(smsCode)) {
            map.put("valid",true);
        }else{
            map.put("valid",false);
            map.put("message","输入的验证码不正确");
        }
        return map;
    }

}
