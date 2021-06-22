package github.subei.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @author subeiLY
 * @create 2021-06-22 10:51
 */
public class demo01 {
    public static void main(String[] args) throws InterruptedException {
        // 获取CPU核数，CPU密集型，IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
        Thread.State.values();

        TimeUnit.DAYS.sleep(1); 	// 休眠1天
        TimeUnit.SECONDS.sleep(1); 	// 休眠1s

    }
}
