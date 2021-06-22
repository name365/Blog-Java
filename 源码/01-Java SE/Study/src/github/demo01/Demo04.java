package github.demo01;

/**
 * @author subeiLY
 * @create 2021-05-25 20:00
 */
public class Demo04 {
    public static void main(String[] args) {
        // ++ -- 自增，自减  一元运算符
        int a = 3;

        int b = a++;    // 执行完这一行代码后，先给b赋值，再自增
        System.out.println(a);
        int c = ++a;    // 执行完这一行代码前，先自增，再给c赋值

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        // 幂运算 2^3
        double pow = Math.pow(2,3);
        System.out.println(pow);

    }
}
