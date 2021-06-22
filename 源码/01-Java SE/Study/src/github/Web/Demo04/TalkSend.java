package github.Web.Demo04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author subeiLY
 * @create 2021-06-11 18:22
 */
public class TalkSend implements Runnable {
    DatagramSocket socket = null;
    BufferedReader reader = null;

    private int    formPort;
    private String hostname;
    private int    toPort;

    public TalkSend(int formPort, String hostname, int toPort) {
        this.formPort = formPort;
        this.hostname = hostname;
        this.toPort = toPort;

        try {
            socket = new DatagramSocket(formPort);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                String s = reader.readLine();
                byte[] buffer = s.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length,
                        new InetSocketAddress(hostname, toPort));

                // 发送包
                socket.send(packet);
                if (s.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 关闭socket
        socket.close();
    }
}

