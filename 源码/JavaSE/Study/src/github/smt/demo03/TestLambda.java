package github.smt.demo03;

/**
 * 推导lambda表达式
 *
 * @author subeiLY
 * @create 2021-06-13 11:41
 */
public class TestLambda {
    // 3.静态内部类
//    static class Like implements ILike{
//        @Override
//        public void lambda(){
//            System.out.println("I like Lambda2");
//        }
//    }

    public static void main(String[] args) {
        // 4.局部内部类
//        class Like implements ILike{
//            @Override
//            public void lambda(){
//                System.out.println("I like Lambda3");
//            }
//        }

        // 创建接口对象
//        ILike like = new ILike() {
//            // 5.匿名内部类，没有类的名称，必须借助接口或父类
//            @Override
//            public void lambda() {
//                System.out.println("I like Lambda4");
//            }
//        };
//        like.lambda();

        // 6.简化
        ILike like = () ->{
            System.out.println("I like Lambda5");
        };
        like.lambda();
    }
}

// 1.定义一个函数式接口
interface ILike{
    void lambda();
}

// 2.实现类
//class Like implements ILike{
//    @Override
//    public void lambda(){
//        System.out.println("I like Lambda");
//    }
//}