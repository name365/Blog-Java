package github.demo01;

/**
 * @author subeiLY
 * @create 2021-05-25 16:03
 */
public class Demo02 {
    public static void main(String[] args) {
        long a = 123123123123123L;
        int b = 123;
        short c = 10;
        byte d = 8;

        System.out.println(a+b+c+d);
        System.out.println(b+c+d);
        System.out.println(c+d);
        System.out.println((double)c+d);
    }
}
