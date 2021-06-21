package github.smt.demo06;

/**
 * 测试:生产者消费者模型2-->信号灯法,标志位解决
 * @author subeiLY
 * @create 2021-06-17 15:38
 */
public class TestThreadPC02 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

// 生产者 --> 演员
class Player extends Thread{
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                this.tv.play("熊出没播放中……");
            }else{
                this.tv.play("开心消消乐……");
            }
        }
    }
}

// 消费者 --> 观众
class Watcher extends Thread{
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

// 产品 --> 节目
class TV{
    /*
    演员表演，观众等待 T
    观众观看，演员等待 F
     */
    String voice; // 表演的节目
    boolean flag = true;

    // 表演
    public synchronized void play(String voice){
        if (!flag){
            try{
                this.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("演员表演了:" + voice);
        // 通知观众观看
        this.notifyAll();   // 通知唤醒
        this.voice = voice;
        this.flag = !this.flag;
    }

    // 观看
    public synchronized void watch(){
        if(flag){
            try{
                this.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("观看了:" + voice);
        // 通知演员表演
        this.notify();
        this.flag = !this.flag;
    }
}