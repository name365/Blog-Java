package com.jiaxuan.shop.controller;

import com.jiaxuan.shop.common.utils.CodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: jx
 * Date: 2019-03-12 9:34
 * Description: 生成验证接口
 */
@Controller
@RequestMapping("/admin/code")
public class VerifyCodeController {

    /**
     *功能描述: 获取验证码图片
     * @Author jx
     * @Date 9:53 2019/03/12
     * @Param [request, response]
     * @return void
     */
    @RequestMapping("getCodeImage")
    public void getCodeImage(HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> map = CodeUtil.generateCodeAndPic();

        //创建一个session，将生成的随机验证码放到session中
        HttpSession session = request.getSession();
        session.setAttribute("code",map.get("code").toString());

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("image/jpeg");

        try {
            //将生成的验证图标写入到输出流中，显示验证码
            ImageIO.write((RenderedImage) map.get("codePic"),"jpeg",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("checkRandCode")
    @ResponseBody
    public Map<String,Object> checkRandCode(String randCode, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        String code = (String) session.getAttribute("code");
        //匹配验证码，不区分大小写
        if (code.equalsIgnoreCase(randCode)) {
            map.put("valid",true);
        }else{
            map.put("valid",false);
        }
        return map;
    }

}
