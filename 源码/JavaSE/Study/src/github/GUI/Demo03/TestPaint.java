package github.GUI.Demo03;

import java.awt.*;

/**
 * @author subeiLY
 * @create 2021-06-08 15:39
 */
public class TestPaint {
    public static void main(String[] args) {
       new MyPaint().loadFrame();
    }
}

class MyPaint extends Frame{

    public void loadFrame(){
        setBounds(200,200,600,600);
        setVisible(true);
    }

    // 画笔
    @Override
    public void paint(Graphics g){
        // 画笔需要颜色
        g.setColor(Color.green);
//        g.drawOval(100,100,100,100);    // 画一个空心的圆
        g.fillOval(100,100,100,100);    // 画一个实心的圆

        g.setColor(Color.BLUE);
        g.fillRect(150,200,200,200);

        // 画笔用完，还原到最初的颜色
    }
}
