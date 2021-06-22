package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:15
 */
public class DoWhileDemo01 {
    public static void main(String[] args) {
        // 计算1+2+3+...+100=？

        int i = 0;
        int sum = 0;

        do{
            sum = sum + i;
            i++;
        }while(i<=100);

        System.out.println(sum);
    }
}
