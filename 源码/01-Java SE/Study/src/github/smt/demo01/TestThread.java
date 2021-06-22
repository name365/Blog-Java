package github.smt.demo01;

/**
 * @author subeiLY
 * @create 2021-06-12 13:29
 */
// 创建线程方式一：继承Thread类，重写run()方法，调用start开启线程
/*
注意：线程开启不一定立即执行，由CPU调度使用

 */
public class TestThread extends Thread{
    // run()方法线程体
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在阅读代码-----" + i);
        }
    }

    // main线程
    public static void main(String[] args) {
        // 创建一个线程对象
        TestThread thread = new TestThread();
        // 调用start()开启线程
        thread.start();

        for (int i = 0; i < 200; i++) {
            System.out.println("多线程学习中……" + i);
        }
    }
}
