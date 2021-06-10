package github.GUI.Demo06;

import javax.swing.*;
import java.awt.*;

/**
 * @author subeiLY
 * @create 2021-06-10 13:55
 */
public class TextComboxDemo01 extends JFrame {
    public TextComboxDemo01(){
        Container container = this.getContentPane();
        JComboBox box = new JComboBox();

        box.addItem(null);
        box.addItem("正在热映");
        box.addItem("已下架");
        box.addItem("即将上架");

        container.add(box);

        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new TextComboxDemo01();
    }
}
