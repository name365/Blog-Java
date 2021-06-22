package github.liuc;

import java.util.Scanner;

/**
 * @author subeiLY
 * @create 2021-05-26 14:17
 */
public class Demo05 {
    public static void main(String[] args) {
        // 求输入的数字的总和及平均数
        Scanner scanner = new Scanner(System.in);

        // 和
        double sum = 0;
        // 计算输入了多少个数字
        int m = 0;

        // 通过循环判断是否输入，并统计求和
        while(scanner.hasNextDouble()){
            double x = scanner.nextDouble();
            System.out.println("你输入了第" + m + "个数据，当前结果为sum=" + sum);
            m = m +1;
            sum = sum + x;
        }

        System.out.println(m + "个数的和为:" + sum);
        System.out.println(m + "个数的平均数是:" + (sum/m));

        scanner.close();
    }
}
