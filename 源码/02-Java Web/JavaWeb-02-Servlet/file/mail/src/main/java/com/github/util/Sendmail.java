package com.github.util;

/**
 * 多线程实现邮件发送
 * 使用多线程的原因就是提高用户的体验，一旦一个页面3s及以上的时间白屏就可能被用户关掉
 * 所以我们在用户提交表单之后，将费时的邮件发送工作使用一个子线程来完成，而主线程还是去完成它自己的事情
 * 这么做就可以提高用户体验，不然用户等待邮件发送的时间
 */

import com.github.pojo.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author subei
 * 多线程这种处理就可以称为异步处理
 */
public class Sendmail extends Thread{

    // 用于向客户发送邮件的邮箱
    private String from = "2943357594@qq.com";
    // 用于登陆SMTP服务器的用户名
    private String username = "2943357594@qq.com";
    // 授权码
    private String password = "bdpsozpijpredeij";
    // 发送邮件的地址
    private String host = "smtp.qq.com";

    private User user;
    public Sendmail(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Properties prop = new Properties();
            // 设置QQ邮件服务器
            prop.setProperty("mail.host", host);
            // 邮件发送协议
            prop.setProperty("mail.transport.protocol", "smtp");
            // 需要验证用户名密码
            prop.setProperty("mail.smtp.auth", "true");

            // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            // 1.创建定义整个应用程序所需的环境信息的 Session 对象
            // 使用QQ邮箱的时候才需要，其他邮箱不需要这一段代码
            // 获取和SMTP服务器的连接对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    // 发件人邮件用户名、授权码
                    return new PasswordAuthentication("2943357594@qq.com", "bdpsozpijpredeij");
                }
            });

            // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);

            // 2.通过session得到transport对象
            Transport ts = session.getTransport();

            // 3.使用邮箱的用户名和授权码连上SMTP邮件服务器，即登陆
            ts.connect(host, username, password);

            //4、创建邮件对象MimeMessage——点击网页上的写信
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("用户注册邮件！");
            message.setContent("<p><h2>恭喜注册成功！</h2></p>您的用户名为: <h4>"+user.getUsername()+
                    "</h4><p>您的密码:" + user.getPassword() +
                    "</p><p>请妥善保管您的密码，如有问题请及时联系网站客服，再次欢迎您的加入！！</p>", "text/html;charset=UTF-8");

            // 5.发送邮件——在网页上点击发送按钮
            ts.sendMessage(message, message.getAllRecipients());

            // 6.关闭连接对象，即关闭服务器上的连接资源
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}