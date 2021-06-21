package github.smt.demo01;

/**
 * 多个线程同时操作同一个对象  买火车票案例
 * @author subeiLY
 * @create 2021-06-12 14:44
 */
// 发现问题:多个线程操作同一个资源的情况下,线程不安全,数据紊乱
public class TestThread04 implements Runnable{
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
