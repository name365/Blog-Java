package github.exception;

/**
 * @author subeiLY
 * @create 2021-06-05 6:01
 */
public class Test {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;

        try{ // try监控区域
            System.out.println(a/b);
        }catch (ArithmeticException e){ // catch捕获异常
            System.out.println("程序出现异常！变量b不能为0");
        }finally { // 处理善后工作
            System.out.println("finally");
        }
    }

    public void test(int a,int b) throws ArithmeticException{
        if(b==0){
            throw new ArithmeticException();
        }
    }
}
