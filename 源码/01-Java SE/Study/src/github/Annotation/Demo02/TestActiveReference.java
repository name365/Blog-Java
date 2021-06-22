package github.Annotation.Demo02;

/**
 * 测试类什么时候会初始化
 * @author subeiLY
 * @create 2021-06-18 20:08
 */
public class TestActiveReference {
    static{
        System.out.println("Main类被加载！");
    }

    public static void main(String[] args) throws ClassNotFoundException {

//        1. 主动调用
        Son son = new Son();

//        反射也会产生主动引用
        Class.forName("github.Annotation.Demo02.Son");

//        不会产生类的引用的方法
        System.out.println(Son.b);

        Son[] array = new Son[5];

        System.out.println(Son.a);
    }
}

class Father{
    static final int b=2;
    static {
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static {
        System.out.println("子类被加载");
        m=100;
    }

    static int m=300;
    static final int a=1;
}