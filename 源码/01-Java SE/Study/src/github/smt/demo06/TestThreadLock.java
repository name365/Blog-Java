package github.smt.demo06;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试Lock锁
 * @author subeiLY
 * @create 2021-06-17 15:09
 */
public class TestThreadLock {
    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        new Thread(testLock).start();
        new Thread(testLock).start();
        new Thread(testLock).start();
    }
}

class TestLock implements Runnable{
    int tickerNums = 10;
    // 定义Lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            // 加锁
            try{
                lock.lock();
                if(tickerNums <= 0){
                    break;
                }
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(tickerNums--);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                // 解锁
                lock.unlock();
            }
        }
    }
}
