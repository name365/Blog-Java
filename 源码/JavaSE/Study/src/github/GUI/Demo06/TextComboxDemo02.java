package github.GUI.Demo06;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author subeiLY
 * @create 2021-06-10 14:06
 */
public class TextComboxDemo02 extends JFrame{
    public TextComboxDemo02(){
        Container container = this.getContentPane();
        // 生成列表的内容
//        String[] contents = {"1","2","3"};

        Vector vector = new Vector();

        // 列表中放入需要的内容
        JList jList = new JList(vector);

        vector.add("赵公明");
        vector.add("吕岳");
        vector.add("吴刚");

        container.add(jList);

        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new TextComboxDemo02();
    }
}
