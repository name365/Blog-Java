package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:47
 */
public class BreakDemo01 {
    public static void main(String[] args) {
        int i = 0;
        while(i<100){
            i++;
            System.out.println(i);
            if(i==30){
                break;
            }
        }

        System.out.println("123");
    }
}
