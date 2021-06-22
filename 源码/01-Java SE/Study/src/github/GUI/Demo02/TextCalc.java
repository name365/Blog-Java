package github.GUI.Demo02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author subeiLY
 * @create 2021-06-07 16:53
 */
public class TextCalc {
    public static void main(String[] args) {
        new Calculator().loadFrame();
    }
}


// 计算器类
class Calculator extends Frame{
    // 属性
    TextField num1,num2,num3;

    // 方法
    public void loadFrame(){
        // 三个文本框
        num1 = new TextField(10);    // 字符数
        num2 = new TextField(10);    // 字符数
        num3 = new TextField(20);    // 字符数

        // 一个按钮
        Button button = new Button("=");
        // 一个标签
        Label label = new Label("+");

        button.addActionListener(new MyCalculatorListener());

        // 布局
        setLayout(new FlowLayout());

        add(num1);
        add(label);
        add(num2);
        add(button);
        add(num3);

        pack();
        setVisible(true);
    }

    // 监听器类
    // 内部类最大的好处，就是可以畅通无阻的访问外部属性和方法！
    private class MyCalculatorListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // 1.获得加数和倍加数
            int n1 = Integer.parseInt(num1.getText());
            int n2 = Integer.parseInt(num2.getText());

            // 2.将这个值 + 法运算后，放到第三个框
            num3.setText("" + (n1+n2));

            // 3.清楚前两个值
            num1.setText("");
            num2.setText("");
        }
    }
}


