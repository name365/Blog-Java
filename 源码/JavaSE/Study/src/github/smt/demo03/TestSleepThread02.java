package github.smt.demo03;

import github.smt.demo01.TestThread04;

/**
 * @author subeiLY
 * @create 2021-06-13 22:03
 */

// 模拟网络延迟:放大问题的发生性
public class TestSleepThread02 implements Runnable{
    // 票数
    private int ticketNums = 10;

    public static void main(String[] args) {
        TestThread04 thread04 = new TestThread04();
        new Thread(thread04,"张三").start();
        new Thread(thread04,"李四").start();
        new Thread(thread04,"王五").start();
        new Thread(thread04,"赵六").start();

    }

    @Override
    public void run() {
        while (true){
            if(ticketNums <= 0){
                break;
            }
            // 捕获异常
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---》拿到了第" + ticketNums-- + "张票");
        }
    }
}
