package github.smt.demo03;

/**
 * @author subeiLY
 * @create 2021-06-13 17:11
 */

/**
 * 测试stop
 * 1.建议线程正常停止-->利用次数,不建议死循环
 * 2.建议使用标志位-->设置一个标志位
 * 3.不要使用stop或者destroy等过时或者JDK不建议使用的方法
 */

public class TestStopThread implements Runnable{
    // 1.设置一个标志位
    private boolean flag = true;

    public static void main(String[] args) {
        TestStopThread stop = new TestStopThread();
        new Thread(stop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main...." + i);
            if(i==900){
                // 调用stop()切换标志位,让线程终止
                stop.stop();
                System.out.println("线程该停止了");
            }
        }
    }

    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("run……Thread" + i++);
        }
    }

    // 2.设置一个公开的方法停止线程，转换标志位
    public void stop(){
        this.flag = false;
    }
}
