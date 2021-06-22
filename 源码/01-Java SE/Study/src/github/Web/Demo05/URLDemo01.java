package github.Web.Demo05;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author subeiLY
 * @create 2021-06-11 19:16
 */
public class URLDemo01 {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:8080/helloworld/insex" +
                ".jsp?username=subeily&password=123");

        System.out.println(url.getProtocol());  // 协议
        System.out.println(url.getHost());  // 主机IP
        System.out.println(url.getPort());  // 端口
        System.out.println(url.getPath());  // 路径
        System.out.println(url.getFile());  // 文件名
        System.out.println(url.getQuery()); // 参数

    }
}
