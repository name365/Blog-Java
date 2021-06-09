package github.array;

/**
 * @author subeiLY
 * @create 2021-05-27 16:08
 */
public class ArrayDemo01 {
    // 变量的类型  变量的名字  = 变量的值

    public static void main(String[] args) {
        int[] numbers;  // 1.定义

        numbers = new int[10];  // 2.创建一个数组
        // 这里面可以存放10个int型数字

        // 3.给数组赋值
        numbers[0]=1;
        numbers[1]=2;
        numbers[2]=3;
        numbers[3]=4;
        numbers[4]=5;
        numbers[5]=6;
        numbers[6]=7;
        numbers[7]=8;
        numbers[8]=9;
        numbers[9]=10;

        // 计算所有数字的和
        int sum = 0;
        // 获取数组的长度: arrays.length
        for(int i = 0;i<numbers.length;i++){
            sum = sum + numbers[i];
        }

        System.out.println(sum);
    }

}
