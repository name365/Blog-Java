package github.GUI.Demo05;

import javax.swing.*;
import java.awt.*;

/**
 * @author subeiLY
 * @create 2021-06-09 16:54
 */
public class JScroPallDemo extends JFrame {

    public JScroPallDemo(){
        Container container = this.getContentPane();
        // 文本域
        JTextArea textArea = new JTextArea(20, 30);
        textArea.setText("欢迎查阅！！");

        // 面板
        JScrollPane scrollPane = new JScrollPane(textArea);
        container.add(scrollPane);

        this.setVisible(true);
        this.setBounds(200,200,300,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JScroPallDemo();
    }
}
