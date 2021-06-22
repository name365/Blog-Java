package github.Web.Demo04;

/**
 * @author subeiLY
 * @create 2021-06-11 18:23
 */
public class StudentClient {
    public static void main(String[] args) {
        new Thread(new TalkSend(7777,"localhost",8900)).start();
        new Thread(new TalkReceive(9999),"老师").start();

    }
}
