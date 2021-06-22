package github.liuc;

import java.util.Scanner;

/**
 * @author subeiLY
 * @create 2021-05-26 10:42
 */
public class Demo01 {
    public static void main(String[] args) {
        // 创建一个扫描器对象，用于接收键盘数据
        Scanner scanner = new Scanner(System.in);

        System.out.println("使用next方法接收:");

        // 判断用户有没有输入字符串
        if(scanner.hasNext()){
            String str = scanner.next();
            System.out.println("输入的内容为:" + str);
        }

        // 凡是属于IO流的类如果不关闭会一直占用资源
        scanner.close();
    }
}
