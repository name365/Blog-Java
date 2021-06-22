package github.GUI.Demo01;

import java.awt.*;

/**
 * @author subeiLY
 * @create 2021-06-06 13:53
 */
public class TestFrame2 {
    public static void main(String[] args) {
        // 展示多个窗口
        new MyFrame(100,100,400,400,Color.PINK);
        new MyFrame(500,100,400,400,Color.green);
        new MyFrame(100,500,400,400,Color.red);
        new MyFrame(500,500,400,400,Color.orange);


    }
}
class MyFrame extends Frame{
    static int id = 0;  // 可能存在多个窗口，需要一个计数器

    public MyFrame(int x,int y,int w,int h,Color color){
        super("MyFrame" + (++id));
        setBackground(color);
        setBounds(x,y,w,h);
        setVisible(true);
    }

}