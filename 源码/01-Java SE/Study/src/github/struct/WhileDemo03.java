package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:09
 */
public class WhileDemo03 {
    public static void main(String[] args) {
        // 计算1+2+3+...+100=？

        int i = 0;
        int sum = 0;

        while(i<=100){
            sum = sum + i;
            i++;
        }

        System.out.println(sum);
    }
}
