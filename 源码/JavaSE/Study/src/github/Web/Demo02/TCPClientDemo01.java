package github.Web.Demo02;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author subeiLY
 * @create 2021-06-11 11:08
 */
// 客户端
public class TCPClientDemo01 {
    public static void main(String[] args) {
        Socket accept = null;
        OutputStream os = null;

        try {
            // 1.要知道服务器的地址
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            int port = 9999;
            // 2.创建一个socket链接
            accept = new Socket(inetAddress,port);
            // 3.发送信息IO流
            os = accept.getOutputStream();
            os.write("就这吧，什么鬼？".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            // 关闭资源
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(accept != null){
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
