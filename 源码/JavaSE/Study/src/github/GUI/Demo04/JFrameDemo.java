package github.GUI.Demo04;

import javax.swing.*;
import java.awt.*;

/**
 * @author subeiLY
 * @create 2021-06-09 14:48
 */
public class JFrameDemo {

    // init();初始化

    public void init(){
        JFrame frame = new JFrame("这是一个JFrame窗口！");
        frame.setVisible(true);
        frame.setBounds(100,100,200,200);
        frame.setBackground(Color.orange);
        // 设置显示文字
        JLabel label = new JLabel("欢迎查阅！！！");

        frame.add(label);

        // 关闭事件
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JFrameDemo().init();
    }
}
