package github.oop.Demo10;

/**
 * @author subeiLY
 * @create 2021-06-05 5:25
 */
public class Outer {
    private int id = 10;
    public void out(){
        System.out.println("这是外部类的方法！");
    }

    class Inner{
        public void in(){
            System.out.println("这是内部类的方法！");
        }

        // 获得外部类的私有属性
        public void getID(){
            System.out.println(id);
        }
    }
}
