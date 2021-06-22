package github.base;

/**
 * @author subeiLY
 * @create 2021-05-25 9:56
 */
public class Demo05 {
    public static void main(String[] args) {
        // 操作比较大时，注意溢出
        // JDK7新特性，数字之间可以用下划线分割
        int money = 10_0000_0000;
        System.out.println(money);

        int years = 20;
        int total = money*years;    //  -1474836480  计算时溢出
        System.out.println(total);

        long total2 = money*years;
        System.out.println(total2);    // 默认是int，转换之前已经存在问题了!!!

        long total3 = money*(long)years;
        System.out.println(total3);     // 20000000000

    }
}
