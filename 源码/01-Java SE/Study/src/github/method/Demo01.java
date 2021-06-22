package github.method;

/**
 * @author subeiLY
 * @create 2021-05-27 9:28
 */
public class Demo01 {

    // main方法
    public static void main(String[] args) {
        int sum = add(1, 2);
        System.out.println(sum);

        int sum2 = add2(1, 2, 3);
        System.out.println(sum2);
    }

    // 加法
    public static int add(int a,int b){
        return a+b;
    }

    public static int add2(int a,int b,int c){
        return a+b+c;
    }
}
