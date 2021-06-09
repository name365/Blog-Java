package github.GUI.Demo03;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author subeiLY
 * @create 2021-06-08 18:47
 */
// 测试鼠标监听
public class TestMouseListener {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame("画图");

        windowClose(frame); // 关闭窗口
    }
    // 关闭窗体的事件
    private static void windowClose(Frame frame){
        frame.addWindowListener(new WindowAdapter() {
            // 窗口点击关闭时要做的事
            @Override
            public void windowClosing(WindowEvent e) {
                // 结束程序
                System.exit(0);
            }
        });
    }
}

// 自己的类
class MyFrame extends Frame{
    ArrayList ponits;
    // 需要监听鼠标当前位置，需要集合来存储这个点
    public MyFrame(String title){
        super(title);
        setBounds(200,200,400,300);
        // 存鼠标点击的点
        ponits = new ArrayList<>();

        setVisible(true);
        // 正对这个窗口
        this.addMouseListener(new MyMouseLintener());

    }

    @Override
    public void paint(Graphics g) {
        // 画画，监听鼠标的事件
        Iterator iterator = ponits.iterator();
        while(iterator.hasNext()){
            Point point = (Point) iterator.next();
            g.setColor(Color.orange);
            g.fillOval(point.x,point.y,10,10);


        }
    }

    // 添加一个点到界面上
    public void addPoint(Point point){
        ponits.add(point);
    }


    // 适配器模式
    private class MyMouseLintener extends MouseAdapter {

        // 鼠标按压
        @Override
        public void mousePressed(MouseEvent e) {
            MyFrame myFrame = (MyFrame) e.getSource();
            myFrame.addPoint(new Point(e.getX(),e.getY()));

            // 每次点击都需要重画一次
            myFrame.repaint();  // 刷新
        }
    }
}

