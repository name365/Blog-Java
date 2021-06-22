package github.oop.Demo10;

/**
 * @author subeiLY
 * @create 2021-06-03 13:06
 */
// 一个项目应该只有一个main方法
public class Application {
    public static void main(String[] args) {

        Outer outer = new Outer();
        // 通过这个外部类来实例化内部类
        Outer.Inner inner = outer.new Inner();
        inner.getID();

    }
}
