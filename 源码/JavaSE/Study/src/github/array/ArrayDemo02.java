package github.array;

/**
 * @author subeiLY
 * @create 2021-05-27 17:00
 */
public class ArrayDemo02 {
    public static void main(String[] args) {
        // 静态初始化 : 创建 + 赋值
        int[] a = {1,2,3,4,5,6,7,8};
        System.out.println(a[0]);

        // 动态初始化
        int[] b = new int[2];
        b[0]=10;

        System.out.println(b[0]);
        System.out.println(b[1]);
        System.out.println(b[2]);
        System.out.println(b[3]);
    }
}
