package github.GUI.Demo01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author subeiLY
 * @create 2021-06-06 15:53
 */
public class TestLowLayout {
    public static void main(String[] args) {
        Frame frame = new Frame();
        // 组件--按钮
        Button button = new Button("button");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");

        // 设置为流式布局
        frame.setLayout(new FlowLayout());
//        frame.setLayout(new FlowLayout(FlowLayout.LEFT)); // 靠左
//        frame.setLayout(new FlowLayout(FlowLayout.RIGHT)); // 靠右

        frame.setSize(200,200);

        // 把按钮添加上去
        frame.add(button);
        frame.add(button2);
        frame.add(button3);

        frame.setVisible(true);

        // 监听事件，监听窗口关闭事件
        // 适配器模式
        frame.addWindowListener(new WindowAdapter() {
            // 窗口点击关闭时要做的事
            @Override
            public void windowClosing(WindowEvent e) {
                // 结束程序
                System.exit(0);
            }
        });
    }
}
