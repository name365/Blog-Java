package github.GUI.Demo02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author subeiLY
 * @create 2021-06-07 13:15
 */
public class TestTest01 {
    public static void main(String[] args) {
        // 启动！
        MyFrame frame = new MyFrame();

        windowClose(frame); // 关闭窗口
    }
    // 关闭窗体的事件
    private static void windowClose(Frame frame){
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

class MyFrame extends Frame {
    public MyFrame(){
        TextField textField = new TextField();
        add(textField);

        // 监听这个文本输入框输入的文字
        MyActionListener2 myActionListener2 = new MyActionListener2();
        textField.addActionListener(myActionListener2);

        // 设置替换编码
        textField.setEchoChar('*');

        setVisible(true);
        pack();
    }
}

class MyActionListener2 implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        TextField field = (TextField) e.getSource();// 获得一些资源
        System.out.println(field.getText());    // 获得输入框中的文本
        field.setText("");  // null

    }
}