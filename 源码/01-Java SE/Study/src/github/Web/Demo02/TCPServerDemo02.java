package github.Web.Demo02;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author subeiLY
 * @create 2021-06-11 13:13
 */
// 服务器
public class TCPServerDemo02 {
    public static void main(String[] args) throws Exception{
        // 1.创建一个服务器地址
        ServerSocket socket = new ServerSocket(9000);
        // 2.监听客户连接
        Socket accept = socket.accept();    // 阻塞式监听，能等待用户连进来
        // 3.获取输入流
        InputStream is = accept.getInputStream();
        // 4.确定存放文件的位置
        FileOutputStream fileOutputStream = new FileOutputStream("resort.jpg");

        // 5.写入文件
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = is.read(buffer)) != -1){
            fileOutputStream.write(buffer,0,len);
        }

        // 6.通知客户端收集完毕
        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("服务器已经收集成功，请断开连接！".getBytes());

        // 7.关闭流
        outputStream.close();
        fileOutputStream.close();
        is.close();
        accept.close();
        socket.close();

    }
}
