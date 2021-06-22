package github.Web.Demo03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author subeiLY
 * @create 2021-06-11 16:16
 */
public class UDPSenderDemo01 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket(8888);

        // 准备数据：控制台读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String data = reader.readLine();
            byte[] buffer = data.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, new InetSocketAddress("localhost", 6666));

            // 发送包
            socket.send(packet);
            if(data.equals("bye")){
                break;
            }
        }

        // 关闭socket
        socket.close();

    }
}