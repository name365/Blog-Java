package github.GUI.Demo03;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author subeiLY
 * @create 2021-06-09 11:47
 */
public class TestWindow {
    public static void main(String[] args) {
        new WindowFrame();

    }
}

class WindowFrame extends Frame{
    public WindowFrame(){
        setBackground(Color.blue);
        setBounds(100,100,200,200);
        setVisible(true);
//        addWindowFocusListener(new MyWindowListener());

        this.addWindowListener(
                // 匿名内部类
                new WindowAdapter(){

                    // 关闭窗口
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.out.println("windowClosing");
                        System.exit(0);
                    }

                    // 激活窗口
                    @Override
                    public void windowActivated(WindowEvent e) {
                        WindowFrame source = (WindowFrame) e.getSource();
                        source.setTitle("被激活了！！！");

                        System.out.println("windowActivated");
                    }
                }
        );
    }
}
