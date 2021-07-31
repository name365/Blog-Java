package com.github.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author subeiLY
 * @create 2021-07-26 21:00
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        如何让浏览器3秒自动刷新一次;
        resp.setHeader("refresh","3");

//        在内存中创建一个图片
        BufferedImage image = new BufferedImage(90,40,BufferedImage.TYPE_INT_RGB);
//        得到图片,笔
        Graphics2D g = (Graphics2D) image.getGraphics();
//        设置图片的背景颜色
        g.setColor(Color.white);
        g.fillRect(0,0,90,40);
//        给图片写数据
        g.setColor(Color.RED);
        g.setFont(new Font(null,Font.BOLD,20));
        g.drawString(makeNum(),8,30);

//        告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
//        网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

//        把图片写给浏览器
        ImageIO.write(image,"jpg", resp.getOutputStream());

    }

//    生成随机数
    private String makeNum(){
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7-num.length() ; i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
