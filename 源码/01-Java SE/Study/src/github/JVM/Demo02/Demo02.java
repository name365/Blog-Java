package github.JVM.Demo02;

import java.util.Random;

/**
 * @author subeiLY
 * @create 2021-06-21 17:17
 */
public class Demo02 {
    public static void main(String[] args) {
        String str = "suneiLY";
        while (true) {
            str += str + new Random().nextInt(88888888)
                    + new Random().nextInt(999999999);
        }
    }
}
