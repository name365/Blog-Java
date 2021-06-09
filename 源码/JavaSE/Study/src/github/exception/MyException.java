package github.exception;

/**
 * @author subeiLY
 * @create 2021-06-05 8:46
 */
// 自定义异常
public class MyException extends Exception{
    // 传递数字>10;
    private int detail;

    public MyException(int a){
        this.detail = a;
    }

    // toString
    @Override
    public String toString(){
        return "MyException{" + detail + "}";
    }
}
