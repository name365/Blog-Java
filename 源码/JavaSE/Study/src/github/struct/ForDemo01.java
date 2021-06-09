package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:19
 */
public class ForDemo01 {
    public static void main(String[] args) {
        int a = 1;  // 初始化条件
        while(a<=100){  // 条件判断
            System.out.println(a);  // 循环体
            a+=2;   // 迭代
        }

        System.out.println("while循环结束!");

        // 初始化 条件判断 迭代
        for(int i=1;i<=100;i++){
            System.out.println(i);
        }

        System.out.println("for循环结束!");
    }
}
