package github.GUI.snack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * @author subeiLY
 * @create 2021-06-10 14:43
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    // 定义蛇的数据结构
    int length; // 定义蛇的长度
    int[] snakeX = new int[600];  // 蛇的坐标x
    int[] snakeY = new int[500];  // 蛇的坐标y
    String fx = "R"; // 蛇的方向 ： R:右  L:左  U:上  D:下
    boolean isStart = false; // 游戏是否开始
    boolean isFail = false; // 游戏是否结束

    // 食物的坐标
    int foodx;
    int foody;
    Random random = new Random();

    // 定时器:第一个参数，就是定时执行时间,100毫秒执行一次
    Timer timer = new Timer(100, this);

    int score; // 游戏分数

    // 构造器
    public GamePanel(){
        init();
        // 获得焦点和键盘事件
        this.setFocusable(true); // 获取焦点事件
        this.addKeyListener(this); // 键盘监听事件
        timer.start();
    }

    // 初始化方法
    public void init(){
        length = 3; // 初始小蛇有三节,包括小脑袋
        // 初始化开始的蛇,给蛇定位,
        snakeX[0] = 100; snakeY[0] = 125;
        snakeX[1] = 75; snakeY[1] = 125;
        snakeX[2] = 50; snakeY[2] = 125;

        // 把食物随机分布到界面上
        foodx = 25 + 25 * random.nextInt(34);
        foody = 125 + 25 * random.nextInt(25);

        score = 0; //初始化游戏分数
    }

    // 绘制面板
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    // 清屏
        // 绘制静态面板
        this.setBackground(Color.white);
        Data.header.paintIcon(this,g,25,10);    // 头部广告栏画上去
        g.fillRect(25,125,850,625);  // 默认的游戏界面

        // 把小蛇画上去
        if (fx.equals("R")){ // 蛇的头通过方向变量来判断
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]); // 蛇的身体长度根据length来控制
        }

        // 游戏状态
        if (isStart==false){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));   // 设置字体
            g.drawString("按下空格开始游戏!",300,300);  // 文字提示
        }

        // 画食物
        Data.food.paintIcon(this,g,foodx,foody);

        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度: " + length,750,35);
        g.drawString("分数: " + score,750,50);

        // 游戏失败
        if(isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("失败, 按下空格重新开始",200,300);
        }
    }

    // 键盘监听事件
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); // 获取按下的键盘

        if (keyCode==KeyEvent.VK_SPACE){ // 如果是空格
            if (isFail){ // 如果游戏失败,从头再来！
                isFail = false;
                init(); // 重新初始化
            }else { // 否则，暂停游戏
                isStart = !isStart; // 取反
            }
            repaint();
        }

        // 小蛇移动
        if (keyCode==KeyEvent.VK_LEFT){
            fx = "L";
        }else if (keyCode==KeyEvent.VK_RIGHT){
            fx = "R";
        }else if (keyCode==KeyEvent.VK_UP){
            fx = "U";
        }else if (keyCode==KeyEvent.VK_DOWN){
            fx = "D";
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 定时执行时的操作
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isFail==false) {  // 如果游戏是开始状态，且没有结束，则小蛇移动
            // 右移:即让后一个移到前一个的位置即可 !
            for (int i = length - 1; i > 0; i--) { // 除了脑袋都往前移：身体移动
                snakeX[i] = snakeX[i - 1]; // 即第i节(后一节)的位置变为(i-1：前一节)节的位置！
                snakeY[i] = snakeY[i - 1];
            }
            // 通过方向控制，头部移动
            if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 850) snakeX[0] = 25;    // 边界判断
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0] < 25) snakeX[0] = 850;    // 边界判断
            } else if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 125) snakeY[0] = 725;    // 边界判断
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 725) snakeY[0] = 125;    // 边界判断
            }

            // 吃食物:当蛇的头和食物一样时，算吃到食物！
            if (snakeX[0]==foodx && snakeY[0]==foody){
                length++; // 1.长度加一
                // 2.重新生成食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 125 + 25 * random.nextInt(25);
            }

            // 结束判断，头和身体撞到了
            for (int i = 1; i < length; i++) {
                // 如果头和身体碰撞，那就说明游戏失败
                if (snakeX[i]==snakeX[0] && snakeY[i]==snakeY[0] ){
                    isFail = true;
                }
            }

            repaint();  //重画页面
        }
        timer.start();  // 定时器开启
    }
}
