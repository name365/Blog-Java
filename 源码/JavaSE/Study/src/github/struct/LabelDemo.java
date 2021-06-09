package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:55
 */
public class LabelDemo {
    public static void main(String[] args) {
        // 打印101~150之间的所有质数
        // 质数是指在大的自然数中，除1和它本身以外不再有其他因数的自然数。

        int count = 0;

        outer:for(int i = 101;i<150;i++){
            for(int j = 2;j<i/2;j++){
                if(i%j==0){
                    continue outer;
                }
            }
            System.out.print(i + " ");
        }

    }
}
