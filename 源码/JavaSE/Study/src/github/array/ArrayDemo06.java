package github.array;

import java.util.Arrays;

/**
 * @author subeiLY
 * @create 2021-05-29 18:24
 */
public class ArrayDemo06 {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,9090,543,21,3,23};

        System.out.println(a);  // 地址值：[I@14ae5a5

        // 打印数组元素Arrays.toString
        System.out.print("系统打印数组方法:");
        System.out.println(Arrays.toString(a));
        System.out.print("自定义打印数组方法:");
        printfArray(a);

        // 数组排序
        Arrays.sort(a);

        System.out.println(Arrays.toString(a));

        // 数组填充
        Arrays.fill(a,2,4,12);  // 数组2~4之间填充12
        System.out.println("数组填充:" + Arrays.toString(a));

        // 数组查找
        int i = Arrays.binarySearch(a, 23);
        System.out.println("查找的元素在数组中的位置:" + i);
    }

    public  static void printfArray(int[] a){
        for (int i = 0; i < a.length; i++) {
            if(i==0){
                System.out.print("[");
            }
            if(i==a.length-1){
                System.out.print(a[i] + "]");
            }else {
                System.out.print(a[i] + ", ");
            }
        }
        System.out.println();
    }
}
