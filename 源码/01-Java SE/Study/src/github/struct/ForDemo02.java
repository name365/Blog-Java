package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:25
 */
public class ForDemo02 {
    public static void main(String[] args) {
        // 计算0到100之间的奇数和偶数的和

        int oddSum = 0;
        int evenSum = 0;

        for(int i = 0;i<=100;i++){
            if(i%2!=0){
                oddSum+=i;
            }else{
                evenSum+=i;
            }
        }

        System.out.println("奇数的和:" + oddSum);
        System.out.println("偶数的和:" + evenSum);
    }
}
