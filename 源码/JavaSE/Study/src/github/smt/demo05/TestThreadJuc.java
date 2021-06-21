package github.smt.demo05;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试JUC安全类型的集合
 * @author subeiLY
 * @create 2021-06-16 21:53
 */
public class TestThreadJuc {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
