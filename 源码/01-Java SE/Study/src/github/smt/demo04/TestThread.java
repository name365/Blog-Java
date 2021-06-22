package github.smt.demo04;

/**
 * 测试礼让线程
 * @author subeiLY
 * @create 2021-06-14 16:56
 */
public class TestThread {
    public static void main(String[] args) {
        MyYeild myYeild = new MyYeild();
        new Thread(myYeild,"a").start();
        new Thread(myYeild,"b").start();
    }
}

class MyYeild implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield(); // 礼让
        System.out.println(Thread.currentThread().getName() + "线程停止执行");
    }
}