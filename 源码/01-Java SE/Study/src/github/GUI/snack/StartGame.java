package github.GUI.snack;

import javax.swing.*;

/**
 * @author subeiLY
 * @create 2021-06-10 14:32
 */
public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java-贪吃蛇小游戏");

        frame.setBounds(10,10,915,820); // 设置窗口的位置和大小
        frame.setResizable(false);  // 窗口大小不可调整,即固定窗口大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭事件，游戏可以关闭

        //正常游戏界面的绘制
        frame.add(new GamePanel());

        frame.setVisible(true); // 展示窗口
    }
}
