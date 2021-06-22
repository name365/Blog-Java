package github.method;

import java.util.Scanner;

/**
 * @author subeiLY
 * @create 2021-05-27 15:40
 */
public class HomeWork04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sum = 0;
        while(true){
            System.out.println("请输入第一个整数:");
            double a = scanner.nextDouble();
            System.out.println("请输入第二个整数:");
            double b = scanner.nextDouble();
            System.out.println("请输入要运算的字符(+:表示加法,-:表示减法,*:表示乘法,/:表示除法)");
            String temp = scanner.next();

            switch (temp){
                case "+":
                    sum = a + b;
                    System.out.println("结果是:" + sum);
                    continue;
                case "-":
                    sum = a - b;
                    System.out.println("结果是:" + sum);
                    continue;
                case "*":
                    sum = a * b;
                    System.out.println("结果是:" + sum);
                    continue;
                case "/":
                    sum = a / b;
                    System.out.println("结果是:" + sum);
                    continue;
                default:
                    System.out.println("请输入正确的运算符号！！");
            }
        }
    }
}
