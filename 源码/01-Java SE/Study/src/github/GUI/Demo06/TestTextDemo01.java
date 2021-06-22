package github.GUI.Demo06;

import javax.swing.*;
import java.awt.*;

/**
 * @author subeiLY
 * @create 2021-06-10 14:13
 */
public class TestTextDemo01 extends JFrame{
    public TestTextDemo01(){
        Container container = this.getContentPane();

        JTextField textField = new JTextField("Hello");
        JTextField textField1 = new JTextField("World", 30);

        container.add(textField,BorderLayout.SOUTH);
        container.add(textField1,BorderLayout.NORTH);

        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new TestTextDemo01();
    }
}
