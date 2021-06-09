package github.GUI.Demo01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author subeiLY
 * @create 2021-06-06 20:06
 */
public class HWork {
    public static void main(String[] args) {
        Frame frame = new Frame("HWork");

        // 设置窗口大小
        frame.setSize(600,600);
        // 设置默认弹出的初始位置
        frame.setLocation(400,400);
        // 设置背景颜色
        frame.setBackground(new Color(255, 244, 0));
        // 表格格式间隔
        frame.setLayout(new GridLayout(2,1));

        // 布局的概念
        Panel p1 = new Panel(new BorderLayout());
        Panel p2 = new Panel(new GridLayout(2,1));
        Panel p3 = new Panel(new BorderLayout());
        Panel p4 = new Panel(new GridLayout(2,2));

        p1.add(new Button("btn1"),BorderLayout.WEST);
        p1.add(new Button("btn2"),BorderLayout.EAST);

        p2.add(new Button("btn3"));
        p2.add(new Button("btn4"));
        p1.add(p2,BorderLayout.CENTER);

        p3.add(new Button("BTN5"),BorderLayout.WEST);
        p3.add(new Button("BTN6"),BorderLayout.EAST);

        p4.add(new Button("btn7"));
        p4.add(new Button("btn8"));
        p4.add(new Button("btn9"));
        p4.add(new Button("btn10"));
        p3.add(p4,BorderLayout.CENTER);

        frame.add(p1);
        frame.add(p3);
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
