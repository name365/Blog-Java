package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:59
 */
public class TestDemo01 {
    // 打印三角形
    public static void main(String[] args) {
        for(int i=1;i<=5;i++){
            for(int j = 5;j>=i;j--){
                System.out.print(" ");
            }
            for(int j = 1;j<=i;j++){
                System.out.print("*");
            }
            for(int j = 1;j<i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}


/*
输出效果：
     *
    ***
   *****
  *******
 *********

 */