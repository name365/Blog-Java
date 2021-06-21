package github.smt.demo05;

/**
 * @author subeiLY
 * @create 2021-06-16 11:34
 */
public class TestThreadPriority {
    public static void main(String[] args) {
        // 主线程默认优先级
        System.out.println(Thread.currentThread().getName()
                + "-->" + Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();
        Thread thread1 = new Thread(myPriority);
        Thread thread2 = new Thread(myPriority);
        Thread thread3 = new Thread(myPriority);
        Thread thread4 = new Thread(myPriority);
        Thread thread5 = new Thread(myPriority);

        // 先设置优先级，再启动
        thread1.start();

        thread2.setPriority(1);
        thread2.start();

        thread3.setPriority(4);
        thread3.start();

        // MAX_PRIORITY=10
        thread4.setPriority(Thread.MAX_PRIORITY);
        thread4.start();

        thread5.setPriority(8);
        thread5.start();

    }
}

class MyPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + "-->" + Thread.currentThread().getPriority());
    }
}