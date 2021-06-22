package github.GUI.Demo05;

import javax.swing.*;
import java.awt.*;

/**
 * @author subeiLY
 * @create 2021-06-09 16:47
 */
public class JPaneDemo extends JFrame {
    public JPaneDemo(){
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2,1,10,10));

        JPanel panel = new JPanel(new GridLayout(1, 3));

        panel.add(new JButton("1"));
        panel.add(new JButton("1"));
        panel.add(new JButton("1"));

        container.add(panel);

        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new JPaneDemo();
    }
}
