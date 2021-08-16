package com.github.test;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @Package: com.github.test
 * @ClassName: MailDemo01
 * @Author: subei
 * @CreateTime: 2021/8/16 16:26
 * @Description: 一封简单的邮件
 */
public class MailDemo01 {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");
        // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");
        // 需要验证用户名密码

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 使用JavaMail发送邮件的5个步骤

        // 1.创建定义整个应用程序所需的环境信息的 Session 对象
        // 使用QQ邮箱的时候才需要，其他邮箱不需要这一段代码
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            // 获取和SMTP服务器的连接对象
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮件用户名、授权码
                return new PasswordAuthentication("2943357594@qq.com", "ndvlgicjptjgdefh");
            }
        });

        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);

        // 2.通过session得到transport对象
        Transport ts = session.getTransport();
        // 通过这一次和SMTP服务器的连接对象获取发送邮件的传输对象

        // 3.使用邮箱的用户名和授权码连上SMTP邮件服务器，即登陆
        ts.connect("smtp.qq.com", "2943357594@qq.com", "ndvlgicjptjgdefh");

        // 4.创建邮件对象MimeMessage——点击网页上的写信
        // 创建一个邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人——在网页上填写发件人
        message.setFrom(new InternetAddress("2943357594@qq.com"));
        // 设置发件人
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发——在网页上填写收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("2943357594@qq.com"));
        // 设置收件人
        // 邮件的标题——在网页上填写邮件标题
        message.setSubject("复杂邮件发送实现");
        // 设置邮件主题

        System.out.println("=============================复杂邮件的邮件内容设置==================================");

        // 图片
        MimeBodyPart body1 = new MimeBodyPart();
        body1.setDataHandler(new DataHandler(new FileDataSource("G:\\Photo\\dm\\p2.jpg")));
        // 图片设置ID
        body1.setContentID("some.png");

        // 文本
        MimeBodyPart body2 = new MimeBodyPart();
        body2.setContent("请注意，这是文本附件<img src='cid:test.png' width='100px' height='100px'>","text/html;charset=utf-8");

        // 附件
        MimeBodyPart body3 = new MimeBodyPart();
        body3.setDataHandler(new DataHandler(new FileDataSource("F:\\java\\Github\\Blog-Java\\文档\\01-Java SE\\Java全栈.xmind")));
        // 附件设置名字
        body3.setFileName("全栈.xmid");

        MimeBodyPart body4 = new MimeBodyPart();
        body4.setDataHandler(new DataHandler(new FileDataSource("G:\\考研备考\\专业课\\笔记\\计算机组成原理\\计算机组成原理.md")));
        // 附件设置名字
        body4.setFileName("计组.md");

        // 拼装邮件正文内容
        MimeMultipart multipart1 = new MimeMultipart();
        multipart1.addBodyPart(body1);
        multipart1.addBodyPart(body2);
        // 1.文本和图片内嵌成功！
        multipart1.setSubType("related");

        // new MimeBodyPart().setContent(multipart1);
        // 将拼装好的正文内容设置为主体
        MimeBodyPart contentText =  new MimeBodyPart();
        contentText.setContent(multipart1);

        // 拼接附件
        MimeMultipart allFile =new MimeMultipart();
        // 附件
        allFile.addBodyPart(body3);
        // 附件
        allFile.addBodyPart(body4);
        // 正文
        allFile.addBodyPart(contentText);
        // 正文和附件都存在邮件中，所有类型设置为mixed；
        allFile.setSubType("mixed");

        // 设置到消息中，保存修改
        message.setContent(allFile);
        // 将MimeMultipart放入消息对象中
        message.saveChanges();
        // 保存上面的修改

        System.out.println("===============================================================");

        // 5.发送邮件——在网页上点击发送按钮
        ts.sendMessage(message, message.getAllRecipients());

        // 6.关闭连接对象，即关闭服务器上的连接资源
        ts.close();
    }
}
