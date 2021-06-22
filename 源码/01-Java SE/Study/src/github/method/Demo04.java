package github.method;

/**
 * @author subeiLY
 * @create 2021-05-27 10:53
 */
public class Demo04 {

    public static void main(String[] args) {
        // 调用可变参数的方法
        printMax(34,3,3,2,56.5);
        printMax(new double[]{1,2,3});

    }

    public static void printMax(double... numbers){
        if(numbers.length==0){
            System.out.println("没有数据！");
            return;
        }

        double result = numbers[0];

        // 排序
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] > result){
                result = numbers[i];
            }
        }
        System.out.println("The max Value is " + result);
    }
}
