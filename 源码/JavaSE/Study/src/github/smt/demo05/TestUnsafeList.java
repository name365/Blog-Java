package github.smt.demo05;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全的集合
 * @author subeiLY
 * @create 2021-06-16 21:02
 */
public class TestUnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() ->{
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
