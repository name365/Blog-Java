package github.oop.Demo01;

/**
 * @author subeiLY
 * @create 2021-06-03 12:30
 */
public class Demo03 {
    public static void main(String[] args) {
        // 实际参数和形式参数的类型要对应！
        int add = Demo03.add(1,3);
        System.out.println(add);
    }

    public static int add(int a,int b){
        return a+b;
    }
}
