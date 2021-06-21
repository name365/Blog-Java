package github.smt.demo04;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 每一秒获取当前时间
 * @author subeiLY
 * @create 2021-06-13 22:16
 */
public class TestSleepThread02 {
    public static void main(String[] args) {
        // 获取当前系统时间
        Date date = new Date(System.currentTimeMillis());
        while(true){
            try{
                Thread.sleep(1000);
                // 更新系统时间
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
                date = new Date(System.currentTimeMillis());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
