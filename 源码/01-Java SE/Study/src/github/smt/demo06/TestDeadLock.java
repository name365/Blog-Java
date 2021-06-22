package github.smt.demo06;

/**
 * 死锁:多个线程互相抱着对方需要的资源,然后形成僵持
 * 解决:一个锁只锁一个对象
 * @author subeiLY
 * @create 2021-06-17 11:24
 */
public class TestDeadLock {
    public static void main(String[] args) {
        Makeup makeup = new Makeup(0, "黄焖鸡");
        Makeup makeup1 = new Makeup(1, "牛肉土豆粉");

        makeup.start();
        makeup1.start();

    }
}

// 鸭脖
class DuckNeck{}

// 土豆粉
class PotatoPowder{}

class Makeup extends Thread{
    // 需要的资源只有一份,用static保证只有一份
    static DuckNeck duckneck = new DuckNeck();
    static PotatoPowder potatoPowder = new PotatoPowder();
    int choice; // 选择
    String foodName;    // 食品名称

    public Makeup(int choice, String foodName) {
        this.choice = choice;
        this.foodName = foodName;
    }

    @Override
    public void run() {
        // 美食
        try{
            food();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void food() throws InterruptedException{
        if(choice == 0){
            synchronized (duckneck) {    // 获得鸭脖的锁
                System.out.println(this.foodName + "获得鸭脖的锁");
                Thread.sleep(1000);
            }
            synchronized (potatoPowder){    // 一秒后想获得 土豆粉的锁
                System.out.println(this.foodName + "获得土豆粉的锁");
            }
        } else {
            synchronized (duckneck) {    // 获得鸭脖的锁
                System.out.println(this.foodName + "获得鸭脖的锁");
                Thread.sleep(2000);
            }
            synchronized (potatoPowder){    // 一秒后想获得 土豆粉的锁
                System.out.println(this.foodName + "获得土豆粉的锁");
            }
        }
    }
}