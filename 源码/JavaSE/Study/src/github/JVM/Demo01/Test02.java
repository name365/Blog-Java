package github.JVM.Demo01;

/**
 * @author subeiLY
 * @create 2021-06-20 15:56
 */
public class Test02 {
    public static void main(String[] args) {
//        new Test02().a();
        new Thread(()->{ },"your thread name").start();
    }

    public void a(){
        b();
    }

    public void b(){
        a();
    }
}
