package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:34
 */
public class ForDemo04 {
    public static void main(String[] args) {
        // 打印9*9乘法表
        for(int i = 1;i <= 9;i++){
            for(int j = 1;j <= i;j++){
                System.out.print(i + "*" + j + "=" + i*j + "\t");
            }
            System.out.println();
        }
    }
}
