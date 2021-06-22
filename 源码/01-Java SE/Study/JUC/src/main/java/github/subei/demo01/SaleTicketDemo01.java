package github.subei.demo01;

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
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 多线程操作
        // 并发: 多线程操作同一个资源类,把资源类放入线程
        Ticket ticket = new Ticket();

//        @FunctionalInterface   // 函数式接口 ,jdk1.8之后使用lambda表达式
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

// 资源类 OOP
// 属性 + 方法
class Ticket{
    private int number = 60;

    // 卖票的方式
    // synchronized 本质:队列、锁
    public synchronized void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName() + "卖出了第" + number + "张票,还有" + number + "张票。");
            number--;
        }
    }
}

