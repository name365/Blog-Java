package github.Web.Demo04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author subeiLY
 * @create 2021-06-11 18:22
 */
public class TalkReceive implements Runnable{
    DatagramSocket socket = null;

    private int port;

    public TalkReceive(int port) {
        this.port = port;
        try {
            socket =  new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //开放端口

        // 接收包
        while (true){
            try {
                byte[] container = new byte[1024];
                DatagramPacket packet = new DatagramPacket(container,0,container.length);

                // 接收
                socket.receive(packet);


                byte[] data = packet.getData();
                String receiveData = new String(data);
                System.out.println(Thread.currentThread().getName() + ":" + receiveData);


                if (receiveData.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }
}

