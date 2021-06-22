package github.array;

import java.util.Arrays;

/**
 * @author subeiLY
 * @create 2021-05-29 21:17
 */
public class ArrayDemo07 {
    public static void main(String[] args) {
        // 调用排序
        int[] array = {12,63,1,95,44,62,78};
        System.out.println("排序前:" + Arrays.toString(array));
        int[] sort = sort(array);   // 调用自定义冒泡排序函数
        System.out.println("排序后:" + Arrays.toString(sort));

    }
    // 冒泡排序
    /*
    1.比较数组中，两个相邻的元素，如果第一个数比第二个数大，我们就交换他们的位置。
    2.每一次比较，都会产生出一个最大或最小的数。
    3.下一轮则可以少一次排序！
    4.依次循环，直到结束。
     */

    public static int[] sort(int[] array){
        // 临时变量
        int temp = 0;
        // 外层循环，判断要走多少次;
        for (int i = 0; i < array.length-1; i++) {

            boolean flag = false;   // 通过flag标识位减少没有意义的比较

            // 内层循环，比较大小，如果第一个数比第二个数大，交换位置
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
            if(flag==false){
                break;
            }
        }
        return array;
    }
}
