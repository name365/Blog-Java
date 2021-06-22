package github.smt.demo02;

/**
 * 线程中的代理模式
 * @author subeiLY
 * @create 2021-06-13 11:24
 */
public class TestStaticProxy02 {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("因为爱情")).start();
        new WeddingCompany(new You()).happyMary();
    }
}
