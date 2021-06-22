package github.smt.demo05;

/**
 * 测试守护线程
 * @author subeiLY
 * @create 2021-06-16 11:43
 */
public class TestDemoThread {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        // 默认false表示是用户线程，正常线程都是用户线程
        thread.setDaemon(true);
        // 耶稣守护线程启动
        thread.start();
        // 你 用户线程启动
        new Thread(you).start();
    }
}

class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("耶稣都救不了你！");
        }
    }
}

class You implements Runnable{

    @Override
    public void run() {
        System.out.println("------你好，世界！------");
        for (int i = 0; i < 36500; i++) {
            System.out.println("每天都很开心！");
        }
        System.out.println("------再见，世界！------");
    }
}
