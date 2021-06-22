package github.smt.demo05;

/**
 * 安全买票
 * @author subeiLY
 * @create 2021-06-16 19:40
 */
public class TestUnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"张三").start();
        new Thread(buyTicket,"李四").start();
        new Thread(buyTicket,"王五").start();
    }
}

class BuyTicket implements Runnable{
    // 票
    private int ticketNums = 10;
    boolean flag = true;

    @Override
    public void run() {
        // 买票
        while(flag){
            try{
                buy();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // synchronized 同步方法
    // 买票
    private synchronized void buy() {
        // 判断是否有票
        if(ticketNums <= 0){
            flag = false;
            return;
        }
        // 延迟
        try{
            Thread.sleep(1);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // 买票
        System.out.println(Thread.currentThread().getName()
            + "拿到" + ticketNums--);
    }
}