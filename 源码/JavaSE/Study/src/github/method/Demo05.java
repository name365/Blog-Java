package github.method;

import java.util.Scanner;

/**
 * @author subeiLY
 * @create 2021-05-27 12:02
 */
public class Demo05 {

    // 5! 5*4*3*2*1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数:");
        int number = scanner.nextInt();

        int test = test(number);

        System.out.println(number + "的阶乘:" + test);
    }

    public static int test(int n){
        if(n==1){
            return 1;
        }else{
            return n*test(n-1);
        }
    }
}
