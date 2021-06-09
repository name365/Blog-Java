package github.GUI.Demo01;

import java.awt.*;

/**
 * @author subeiLY
 * @create 2021-06-06 13:42
 */
public class TestFrame {
    public static void main(String[] args) {
        // Frame,JDK,   看源码！
        Frame frame = new Frame("我的第一个Java图形界面！");

        // 需要设置可见性
        frame.setVisible(true);

        // 设置窗口大小
        frame.setSize(600,600);
        // 设置背景颜色
        Color color = new Color(122, 123, 222);
        frame.setBackground(color);

        // 设置默认弹出的初始位置
        frame.setLocation(90,90);

        // 设置大小固定
        frame.setResizable(false);
    }
}
