package github.smt.demo03;

/**
 * @author subeiLY
 * @create 2021-06-13 14:43
 */
public class TestLambda02 {
    public static void main(String[] args) {
        // 1.lambda
        ILove love = (int a)->{
            System.out.println("I love you -->" + a);
        };
        // 2.lambda简化1.0
        love = (a) ->{
            System.out.println("I love you --> " + a);
        };
        // 3.lambda简化2.0
        love = a ->{
            System.out.println("I love you --> " + a);
        };
        // 4.lambda简化4.0
        love = a -> System.out.println("I love you --> " +a);

        /**
         * 总结：
         * {}简略的条件是只能有一行代码,多行{}就不能简略了
         * 前提是接口为函数式接口(只能有一个方法)
         * 多个参数也可以去掉参数类型,要去掉就都去掉,必须加上()
         */
        love.love(520);
    }
}

// 1.定义一个函数式接口
interface ILove{
    void love(int a);
}