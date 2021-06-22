package github.smt.demo01;

/**
 * 模拟龟兔赛跑
 * @author subeiLY
 * @create 2021-06-12 15:49
 */
public class TestThread05 implements Runnable{
    // 胜利者
    private static String winner;

    public static void main(String[] args) {
        TestThread05 thread05 = new TestThread05();
        new Thread(thread05,"兔子").start();
        new Thread(thread05,"乌龟").start();
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            // 模拟兔子休息
            if(Thread.currentThread().getName().equals("兔子") && i%10==0){
                try {
                    Thread.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            // 判断比赛是否结束
            boolean flag = gameOver(i);
            // 如果比赛结束，停止程序
            if(flag){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "---》跑了" + i + "步");
        }
    }

    // 判断比赛是否结束
    private boolean gameOver(int steps){
        if(winner != null){
            return true;
        }else{
            if(steps >= 100){   // 一共100步
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            }
        }
        return false;
    }
}
