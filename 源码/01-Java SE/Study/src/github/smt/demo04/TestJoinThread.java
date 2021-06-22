package github.smt.demo04;

/**
 * 插队
 * @author subeiLY
 * @create 2021-06-14 17:15
 */
public class TestJoinThread implements Runnable{
    public static void main(String[] args) throws InterruptedException{
        // 启动线程
        TestJoinThread joinThread = new TestJoinThread();
        Thread thread = new Thread(joinThread);
        thread.start();

        // 主线程
        for (int i = 0; i < 500; i++) {
            if(i==200){
                thread.join();  // 插队
            }
            System.out.println("main" + i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("线程VIP" + i);
        }
    }
}
