package github.oop.Demo05;

/**
 * @author subeiLY
 * @create 2021-06-03 13:06
 */
// 一个项目应该只有一个main方法
public class Application {
    public static void main(String[] args) {
        // 方法的调用只和左边，定义的数据类型有关
        A a = new A();
        a.test();

        // 父类的引用指向了子类
        B b = new A();
        b.test();
    }
}
