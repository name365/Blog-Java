package github.struct;

/**
 * @author subeiLY
 * @create 2021-05-26 15:16
 */
public class DoWhileDemo02 {
    public static void main(String[] args) {
        int a = 0;
        while(a<0){
            System.out.println(a);
            a++;
        }
        System.out.println("==================");
        do{
            System.out.println(a);
            a++;
        }while(a<0);
    }
}
