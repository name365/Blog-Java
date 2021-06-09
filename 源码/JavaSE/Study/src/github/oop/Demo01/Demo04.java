package github.oop.Demo01;

/**
 * @author subeiLY
 * @create 2021-06-03 12:45
 */
public class Demo04 {
    // 值传递
    public static void main(String[] args) {
        int a = 1;
        System.out.println(a);

        Demo04.change(a);

        System.out.println(a);
    }

    // 返回值为空
    public static void change(int a) {
        a = 10;
    }
}
