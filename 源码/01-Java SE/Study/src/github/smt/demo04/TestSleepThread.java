package github.smt.demo04;

/**
 * @author subeiLY
 * @create 2021-06-13 22:14
 */
public class TestSleepThread {
    public static void main(String[] args) {
        try {
            tenDown();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // 模拟倒计时
    public static void tenDown() throws InterruptedException{
        int num = 10;
        while(true){
            Thread.sleep(1000);
            System.out.println(num--);
            if(num<=0){
                break;
            }
        }
    }
}
