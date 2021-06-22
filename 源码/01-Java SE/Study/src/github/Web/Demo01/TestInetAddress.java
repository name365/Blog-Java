package github.Web.Demo01;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author subeiLY
 * @create 2021-06-11 10:09
 */
public class TestInetAddress {
    public static void main(String[] args) {
        try{
            // 查询本机地址
            InetAddress inetAddress1 = InetAddress.getByName("127.0.0.1");
            System.out.println(inetAddress1);
            InetAddress inetAddress2 = InetAddress.getByName("localhost");
            System.out.println(inetAddress2);
            InetAddress inetAddress3 = InetAddress.getLocalHost();
            System.out.println(inetAddress3);

            // 查询网站IP地址
            InetAddress inetAddress = InetAddress.getByName("www.taobao.com");
            System.out.println(inetAddress);

            // 常用方法
//            System.out.println(inetAddress.getAddress());   // 返回的是一个字节数组  无用
            System.out.println(inetAddress.getCanonicalHostName());    // 规范的名字
            System.out.println(inetAddress.getHostAddress());  // IP
            System.out.println(inetAddress.getHostName()); // 域名，或者自己电脑的名字

        }catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
