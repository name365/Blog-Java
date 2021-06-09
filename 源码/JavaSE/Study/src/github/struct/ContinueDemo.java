package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:49
 */
public class ContinueDemo {
    public static void main(String[] args) {
        int i = 0;
        while(i<100){
            i++;
            if(i%10==0){
                System.out.println();
                continue;
            }
            System.out.println(i);
        }
    }
}
