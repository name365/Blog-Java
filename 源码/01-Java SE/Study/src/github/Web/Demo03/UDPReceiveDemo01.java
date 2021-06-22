package github.Web.Demo03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author subeiLY
 * @create 2021-06-11 16:16
 */
public class UDPReceiveDemo01 {
    public static void main(String[] args) throws Exception{
        // 开放端口
        DatagramSocket socket = new DatagramSocket(6666);

        while (true) {

            // 准备接收包裹
            byte[] container = new byte[1024];
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            socket.receive(packet);


            byte[] data = packet.getData();
            String receiveData = new String(data);
            System.out.println(receiveData);


            if (receiveData.equals("bye")) {
                break;
            }
        }

        // 关闭socket
        socket.close();

    }
}