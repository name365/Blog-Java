package github.subei.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基本的卖票例子
 * @author subeiLY
 * @create 2021-06-22 15:39
 */
/*
真正的多线程开发——公司中的开发
线程就是一个单独的资源类，没有任何附属操作。
1.属性、方法
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        // 多线程操作
        // 并发: 多线程操作同一个资源类,把资源类放入线程
        Ticket02 ticket = new Ticket02();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"C").start();
    }
}

/*
lock三部曲
1.Lock lock=new ReentrantLock();
2.lock.lock() 加锁
3.finally=> 解锁：lock.unlock();
 */
class Ticket02{
    private int number = 40;

    Lock lock = new ReentrantLock();

    // 卖票的方式
    // 使用lock锁
    public void sale(){
        // 加锁
        lock.lock();
        try{
            // 业务代码
            if(number>0){
                System.out.println(Thread.currentThread().getName() + "卖出了第" + number + "张票,还有" + number + "张票。");
                number--;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 解锁
            lock.unlock();
        }
    }
}

