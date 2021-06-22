package github.exception;

/**
 * @author subeiLY
 * @create 2021-06-05 8:28
 */
public class Test2 {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;

        // Ctrl + Alt + T
        try {
            System.out.println(a/b);
        } catch (Exception e) {
            e.printStackTrace();    // 打印错误的栈信息
        } finally {
        }
    }
}
