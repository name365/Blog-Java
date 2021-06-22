package github.GUI.Demo05;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author subeiLY
 * @create 2021-06-09 17:03
 */
public class JButtonDemo01 extends JFrame {

    public JButtonDemo01(){
        Container container = this.getContentPane();
        // 将一个图片变为图标
        URL resource = JButtonDemo01.class.getResource("tpg.jpg");
        Icon icon = new ImageIcon(resource);

        // 将图标放在按钮上
        JButton button = new JButton();
        button.setIcon(icon);
        button.setToolTipText("图片按钮");

        // add
        container.add(button);

        this.setVisible(true);
        this.setSize(500,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new JButtonDemo01();
    }
}
