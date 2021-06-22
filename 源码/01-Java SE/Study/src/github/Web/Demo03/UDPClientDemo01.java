package github.Web.Demo03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author subeiLY
 * @create 2021-06-11 16:15
 */
// 不需要连接服务器
public class UDPClientDemo01 {
    public static void main(String[] args) throws Exception{
        // 1.建立一个Socket
        DatagramSocket socket = new DatagramSocket();
        // 2.建个包
        String msg = "服务器，你好！";
        InetAddress localhost = InetAddress.getByName("localhost");
        int port = 9090;

        // 数据，数据的长度起始，要发送给谁
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0,msg.getBytes().length,localhost,port);

        // 3.发送包
        socket.send(packet);

        // 4.关闭流
        socket.close();

    }
}
